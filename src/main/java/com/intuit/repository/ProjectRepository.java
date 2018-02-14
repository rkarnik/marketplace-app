package com.intuit.repository;

import com.intuit.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project findByUuid(String uuid);

    List<Project> findByProjectBidStatus(String projectBidStatus);
}
