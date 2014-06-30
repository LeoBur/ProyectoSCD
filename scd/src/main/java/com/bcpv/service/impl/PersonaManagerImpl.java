package com.bcpv.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bcpv.dao.PersonaDao;
import com.bcpv.model.Persona;
import com.bcpv.service.MailEngine;
import com.bcpv.service.PersonaManager;
import com.bcpv.service.PersonaService;
import com.bcpv.service.UserExistsException;

@Service("personaManager")
@WebService(serviceName = "PersonaService", endpointInterface = "com.bcpv.service.PersonaService")
public class PersonaManagerImpl extends GenericManagerImpl<Persona, Long> implements PersonaManager,PersonaService{
    private PasswordEncoder passwordEncoder;
    private PersonaDao personaDao;


    private MailEngine mailEngine;
    private SimpleMailMessage message;
    private PasswordTokenManager passwordTokenManager;

    private String passwordRecoveryTemplate = "passwordRecovery.vm";
    private String passwordUpdatedTemplate = "passwordUpdated.vm";

    @Autowired
    @Qualifier("passwordEncoder")
    public void setPasswordEncoder(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Autowired
    public void setPersonaDao(final PersonaDao personaDao) {
        this.dao = personaDao;
        this.personaDao = personaDao;
    }

    @Autowired(required = false)
    public void setMailEngine(final MailEngine mailEngine) {
        this.mailEngine = mailEngine;
    }

    @Autowired(required = false)
    public void setMailMessage(final SimpleMailMessage message) {
        this.message = message;
    }

    @Autowired(required = false)
    public void setPasswordTokenManager(final PasswordTokenManager passwordTokenManager) {
        this.passwordTokenManager = passwordTokenManager;
    }

    /**
     * Velocity template name to send users a password recovery mail (default
     * passwordRecovery.vm).
     *
     * @param passwordRecoveryTemplate the Velocity template to use (relative to classpath)
     * @see com.bcpv.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordRecoveryTemplate(final String passwordRecoveryTemplate) {
        this.passwordRecoveryTemplate = passwordRecoveryTemplate;
    }

    /**
     * Velocity template name to inform users their password was updated
     * (default passwordUpdated.vm).
     *
     * @param passwordUpdatedTemplate the Velocity template to use (relative to classpath)
     * @see com.bcpv.service.MailEngine#sendMessage(org.springframework.mail.SimpleMailMessage, String, java.util.Map)
     */
    public void setPasswordUpdatedTemplate(final String passwordUpdatedTemplate) {
        this.passwordUpdatedTemplate = passwordUpdatedTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Persona getPersona(final String userId) {
        return personaDao.get(new Long(userId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Persona> getPersonas() {
        return personaDao.getAllDistinct();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Persona savePersona(final Persona user) throws UserExistsException {

        if (user.getVersion() == null) {
            // if new user, lowercase userId
            user.setUsername(user.getUsername().toLowerCase());
        }

        // Get and prepare password management-related artifacts
        boolean passwordChanged = false;
        if (passwordEncoder != null) {
            // Check whether we have to encrypt (or re-encrypt) the password
            if (user.getVersion() == null) {
                // New user, always encrypt
                passwordChanged = true;
            } else {
                // Existing user, check password in DB
                final String currentPassword = personaDao.getUserPassword(user.getId());
                if (currentPassword == null) {
                    passwordChanged = true;
                } else {
                    if (!currentPassword.equals(user.getPassword())) {
                        passwordChanged = true;
                    }
                }
            }

            // If password was changed (or new user), encrypt it
            if (passwordChanged) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        } else {
            log.warn("PasswordEncoder not set, skipping password encryption...");
        }

        try {
            return personaDao.savePersona(user);
        } catch (final Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
            throw new UserExistsException("Persona '" + user.getUsername() + "' already exists!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePersona(final Persona user) {
        log.debug("removing user: " + user);
        personaDao.remove(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePersona(final String userId) {
        log.debug("removing user: " + userId);
        personaDao.remove(new Long(userId));
    }

    /**
     * {@inheritDoc}
     *
     * @param username the login name of the human
     * @return User the populated user object
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException thrown when username not found
     */
    @Override
    public Persona getPersonaByUsername(final String username) throws UsernameNotFoundException {
        return (Persona) personaDao.loadUserByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Persona> search(final String searchTerm) {
        return super.search(searchTerm, Persona.class);
    }

    @Override
    public String buildRecoveryPasswordUrl(final Persona user, final String urlTemplate) {
        final String token = generateRecoveryToken(user);
        final String username = user.getUsername();
        return StringUtils.replaceEach(urlTemplate,
                new String[]{"{username}", "{token}"},
                new String[]{username, token});
    }

    @Override
    public String generateRecoveryToken(final Persona user) {
        return passwordTokenManager.generateRecoveryToken(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRecoveryTokenValid(final String username, final String token) {
        return isRecoveryTokenValid(getPersonaByUsername(username), token);
    }

    @Override
    public boolean isRecoveryTokenValid(final Persona user, final String token) {
        return passwordTokenManager.isRecoveryTokenValid(user, token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendPasswordRecoveryEmail(final String username, final String urlTemplate) {
        log.debug("Sending password recovery token to user: " + username);

        final Persona user = getPersonaByUsername(username);
        final String url = buildRecoveryPasswordUrl(user, urlTemplate);

        sendPersonaEmail(user, passwordRecoveryTemplate, url);
    }

    private void sendPersonaEmail(final Persona user, final String template, final String url) {
        message.setTo(user.getFullName() + "<" + user.getEmail() + ">");

        final Map<String, Serializable> model = new HashMap<String, Serializable>();
        model.put("user", user);
        model.put("applicationURL", url);

        mailEngine.sendMessage(message, template, model);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Persona updatePassword(final String username, final String currentPassword, final String recoveryToken, final String newPassword, final String applicationUrl) throws UserExistsException {
        Persona user = getPersonaByUsername(username);
        if (isRecoveryTokenValid(user, recoveryToken)) {
            log.debug("Updating password from recovery token for user:" + username);
            user.setPassword(newPassword);
            user = savePersona(user);
            passwordTokenManager.invalidateRecoveryToken(user, recoveryToken);

            sendPersonaEmail(user, passwordUpdatedTemplate, applicationUrl);

            return user;
        } else if (StringUtils.isNotBlank(currentPassword)) {
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                log.debug("Updating password (providing current password) for user:" + username);
                user.setPassword(newPassword);
                user = savePersona(user);
                return user;
            }
        }
        // or throw exception
        return null;
    }

	@Override
	public List<Persona> getPersonasByApellido(String apellido) {
		return personaDao.getPersonasByApellido(apellido);
	}
}
