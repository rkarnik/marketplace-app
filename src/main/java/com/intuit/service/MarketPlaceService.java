package com.intuit.service;

import com.intuit.model.Bid;
import com.intuit.model.Project;
import com.intuit.model.ProjectBidStatus;
import com.intuit.model.User;

import java.util.List;

public interface MarketPlaceService {

    /**
     *
     * @param project - project to be created
     * @return - newly created project
     */
    Project createProject(Project project);

    /**
     *
     * @return all projects
     */
    List<Project> getProjects();

    /**
     *
     * @param uuid - project uuid
     * @return -  project of given uuid
     */
    Project getProjectByUuid(String uuid);


    /**
     *
     * @param projectBidStatus - valid values are OPEN and CLOSED
     * @return - projects with given bid status
     */
    List<Project> getProjectsByBidStatus(String projectBidStatus);

    /**
     *
     * @param bid
     * @return newly created bid
     */
    Bid createBid(Bid bid);
}
