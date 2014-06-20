package com.bcpv.service.impl;

import com.bcpv.model.Persona;
import com.bcpv.model.User;


public interface PasswordTokenManager {

    /**
     * {@inheritDoc}
     */
    String generateRecoveryToken(User user);

    /**
     * {@inheritDoc}
     */
    boolean isRecoveryTokenValid(User user, String token);

    void invalidateRecoveryToken(User user, String token);

	String generateRecoveryToken(Persona user);

	boolean isRecoveryTokenValid(Persona user, String token);

	void invalidateRecoveryToken(Persona user, String recoveryToken);
}