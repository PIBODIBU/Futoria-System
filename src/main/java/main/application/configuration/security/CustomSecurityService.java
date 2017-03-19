package main.application.configuration.security;

import main.model.Permission;
import main.model.Role;
import main.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("customSecurityService")
public class CustomSecurityService {
    public boolean hasPermission(String permission) {
        CustomUserDetails userDetails = ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());

        User user = userDetails.getUser();

        for (Role role : user.getRoles()) {
            for (Permission rolePermission : role.getPermissions()) {
                if (rolePermission.getName().equals(permission))
                    return true;
            }
        }

        return false;
    }

}

