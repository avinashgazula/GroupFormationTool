package com.group8.dalsmartteamwork.login.model;

import com.group8.dalsmartteamwork.accesscontrol.User;
import com.group8.dalsmartteamwork.login.dao.LoginDaoImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class LoginAuthentication implements AuthenticationManager {

    public String role;
    Boolean status;
    LoginDaoImpl loginImplementation = new LoginDaoImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = new User();
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        IEncryption encryption = new Encryption();
        String encryptedPassword = encryption.encrypt(password);
        try {
            status = loginImplementation.getUserDetails(username, user.getFirstName(), user.getEmail(),
                    encryptedPassword);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            if (status) {
                role = loginImplementation.getRole();
                user.setRole(role);
                RoleAuthorization roleAuthorization = new RoleAuthorization(user);
                return new UsernamePasswordAuthenticationToken(username, password, roleAuthorization.getAuthorities());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}