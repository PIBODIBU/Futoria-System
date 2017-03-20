package main.controller;

import com.futoria.core.application.configuration.security.CustomUserDetails;
import com.futoria.core.model.Permission;
import com.futoria.core.model.Role;
import com.futoria.core.model.User;
import com.futoria.core.serializer.PermissionSerializer;
import com.futoria.core.serializer.RoleSerializer;
import com.futoria.core.serializer.UserSerializer;
import com.futoria.core.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    private UserService userService;
    private static Gson gson;

    static {
        gson = new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleSerializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(Permission.class, new PermissionSerializer())
                .create();
    }

    @Autowired
    public void setUserService(@Qualifier("userService") UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin.jsp");
        User user = ((CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUser();

        modelAndView.addObject("roles", gson.toJson(user.getRoles()));
        modelAndView.addObject("my_permissions", gson.toJson(userService.getMyPermissions()));
        modelAndView.addObject("other_permissions", gson.toJson(userService.getUserPermissions(2L)));

        return modelAndView;
    }
}