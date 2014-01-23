package cz.muni.fi.pa165.deliverysysteweb.security;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Filip Volner
 */
public class CustomGrantedAuthority implements GrantedAuthority {
    
    private String authority;
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
    
}
