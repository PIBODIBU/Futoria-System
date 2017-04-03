package com.futoria.system.application.configuration.serializer;

import com.futoria.core.model.security.Permission;
import com.futoria.core.model.security.Role;
import com.futoria.core.model.user.User;
import com.futoria.core.model.user.UserData;
import com.futoria.core.model.university.Department;
import com.futoria.core.model.university.Faculty;
import com.futoria.core.model.university.Group;
import com.futoria.core.model.university.University;
import com.futoria.core.serializer.*;
import com.futoria.system.model.Test;
import com.futoria.system.serializer.TestSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {
    @Bean(name = "FutoriaGson")
    public Gson gson() {
        return new GsonBuilder()
                .registerTypeAdapter(Role.class, new RoleSerializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .registerTypeAdapter(Permission.class, new PermissionSerializer())
                .registerTypeHierarchyAdapter(UserData.class, new UserDataSerializer())
                .registerTypeAdapter(University.class, new UniversitySerializer())
                .registerTypeAdapter(Faculty.class, new FacultySerializer())
                .registerTypeAdapter(Department.class, new DepartmentSerializer())
                .registerTypeAdapter(Group.class, new GroupSerializer())
                .registerTypeAdapter(Test.class, new TestSerializer())
                .create();
    }
}
