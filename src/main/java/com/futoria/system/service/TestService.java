package com.futoria.system.service;

import com.futoria.core.application.configuration.security.FutoriaSecurityService;
import com.futoria.core.model.user.User;
import com.futoria.system.model.Test;
import com.futoria.system.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(value = "txManager", readOnly = true)
public class TestService {
    private TestRepository testRepository;
    private FutoriaSecurityService securityService;

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_READ')")
    public Test get(Long id) {
        return testRepository.findOne(id);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_READ')")
    public Test get(String uuid) {
        return testRepository.findFirstByUuid(uuid);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_READ')")
    public Set<Test> getUserTests(User user) {
        return testRepository.getAllByOwner(user);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_MY_READ')")
    public Test getMy(Long id) {
        return testRepository.findOne(id);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_MY_READ')")
    public Test getMy(String uuid) {
        return testRepository.findFirstByUuid(uuid);
    }

    @PreAuthorize("@CoreSecurityService.hasPermission('PERM_TEST_MY_READ')")
    public Set<Test> getMyTests() {
        return testRepository.getAllByOwner(securityService.getUserFromContext());
    }

    // Beans
    @Autowired
    public void setTestRepository(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Autowired
    public void setSecurityService(@Qualifier("CoreSecurityService") FutoriaSecurityService securityService) {
        this.securityService = securityService;
    }
}
