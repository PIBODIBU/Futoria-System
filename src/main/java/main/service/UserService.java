package main.service;

import main.application.configuration.security.CustomUserDetails;
import main.model.Permission;
import main.model.Role;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("@customSecurityService.hasPermission('PERM_PROFILE_USER_READ')")
    public User getUser(Long id) {
        return userRepository.getFirstById(id);
    }

    @PreAuthorize("@customSecurityService.hasPermission('PERM_USER_PERMISSIONS_READ')")
    public Set<Permission> getUserPermissions(Long userId) {
        Set<Permission> permissions = new HashSet<>();
        User user = userRepository.getFirstById(userId);

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return permissions;
    }

    public Set<Permission> getMyPermissions() {
        Set<Permission> permissions = new HashSet<>();
        User user = userRepository.getFirstById(
                ((CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUser()
                        .getId());

        for (Role role : user.getRoles()) {
            permissions.addAll(role.getPermissions());
        }

        return permissions;
    }

    public User getMyData() {
        return userRepository.getFirstById(
                ((CustomUserDetails) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUser()
                        .getId());
    }
}
