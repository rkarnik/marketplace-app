package com.intuit.repository;

import com.intuit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository<User, Integer> {

    User findByUuid(String uuid);
}
