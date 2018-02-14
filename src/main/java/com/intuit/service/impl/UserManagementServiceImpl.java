package com.intuit.service.impl;

import com.intuit.model.User;
import com.intuit.repository.ProjectRepository;
import com.intuit.repository.UserManagementRepository;
import com.intuit.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Autowired
    UserManagementRepository userManagementRepository;

    @Override
    public User createUser(User user) {
        return userManagementRepository.save(user)
                ;
    }
}
