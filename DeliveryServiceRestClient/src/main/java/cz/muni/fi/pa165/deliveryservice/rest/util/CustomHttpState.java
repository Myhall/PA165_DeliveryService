/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.rest.util;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 *
 * @author Tomas Frkan
 */
public class CustomHttpState extends HttpState {

    public synchronized void setCredentials(Credentials credentials) {
        super.setCredentials(AuthScope.ANY, credentials);
    }
}
