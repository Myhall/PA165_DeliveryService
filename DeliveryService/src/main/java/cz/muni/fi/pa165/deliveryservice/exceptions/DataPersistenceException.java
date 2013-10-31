package cz.muni.fi.pa165.deliveryservice.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * The exception to be throw by service layer if any exception occurred on the 
 * persistence layer.
 * 
 * @author Michal Sorentiny
 */
public class DataPersistenceException extends DataAccessException {

    public DataPersistenceException(String msg) {
        super(msg);
    }

    public DataPersistenceException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
