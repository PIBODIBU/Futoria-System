package com.futoria.system.controller;

import com.futoria.core.application.configuration.security.FutoriaUserDetails;
import com.futoria.core.model.security.Permission;
import com.futoria.core.model.security.Role;
import com.futoria.core.model.user.User;
import com.futoria.core.serializer.PermissionSerializer;
import com.futoria.core.serializer.RoleSerializer;
import com.futoria.core.serializer.UserSerializer;
import com.futoria.core.service.PermissionService;
import com.futoria.core.service.RoleService;
import com.futoria.core.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleSerializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(Permission.class, new PermissionSerializer())
                .create();
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping(value = "/perm", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin.jsp");
        User user = ((FutoriaUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();

        modelAndView.addObject("user", gson.toJson(user));
        modelAndView.addObject("roles", gson.toJson(user.getRoles()));
        modelAndView.addObject("my_permissions", gson.toJson(permissionService.getMyPermissions()));
        modelAndView.addObject("other_permissions", gson.toJson(permissionService.getUserPermissions(2L)));

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/role/add", method = RequestMethod.GET)
    public String addUser(@RequestParam(name = "role_name") String name) {
        Role role = new Role();
        HashSet<Permission> permissions = new HashSet<>();

        // Prepare role's permissions
        permissions.add(permissionService.get(1L));
        permissions.add(permissionService.get(2L));

        // Prepare role model
        role.setRoleName(name);
        role.setPermissions(permissions);

        // Save model to db
        roleService.create(role);

        return "DONE";
    }
}