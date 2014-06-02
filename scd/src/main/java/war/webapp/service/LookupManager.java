package war.webapp.service;

import java.util.List;

import war.webapp.model.LabelValue;

/**
 * Business Service Interface to talk to persistence layer and
 * retrieve values for drop-down choice lists.
 *
 * @author <a>Leandro Burgos</a>
 */
public interface LookupManager {
    /**
     * Retrieves all possible roles from persistence layer
     * @return List of LabelValue objects
     */
    List<LabelValue> getAllRoles();
}
