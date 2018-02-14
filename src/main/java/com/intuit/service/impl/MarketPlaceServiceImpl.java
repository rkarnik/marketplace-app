package com.intuit.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.intuit.exception.ResourceNotFoundException;
import com.intuit.exception.ServiceException;
import com.intuit.model.Bid;
import com.intuit.model.Project;
import com.intuit.model.ProjectBidStatus;
import com.intuit.model.User;
import com.intuit.repository.BidRepository;
import com.intuit.repository.ProjectRepository;
import com.intuit.repository.UserManagementRepository;
import com.intuit.service.MarketPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

@Service
public class MarketPlaceServiceImpl implements MarketPlaceService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserManagementRepository userManagementRepository;

    @Autowired
    BidRepository bidRepository;

    @Autowired
    Cache bidsCache;

    public Project createProject(Project project) {
        project.setProjectBidStatus(ProjectBidStatus.OPEN.name());
        User user = userManagementRepository.findByUuid(project.getSellerUuid());
        project.setSeller(user);
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectByUuid(String uuid) {
        Project project = projectRepository.findByUuid(uuid);
        if (ObjectUtils.isEmpty(project))
            throw new ResourceNotFoundException("Project with given uuid not found");

        Double minBidValue = (Double) bidsCache.getIfPresent(uuid);
        if (project.getDeadLineTime().before(new Date())) {
            project.setProjectBidStatus(ProjectBidStatus.CLOSED.name());
            if (minBidValue != null)
                project.setMinimumBidValue(minBidValue);
            projectRepository.save(project);
        }
        return projectRepository.findByUuid(uuid);
    }

    @Override
    public List<Project> getProjectsByBidStatus(String projectBidStatus) {
        return projectRepository.findByProjectBidStatus(projectBidStatus);
    }

    @Override
    public Bid createBid(Bid bid) {
        Project project = projectRepository.findByUuid(bid.getProjectUuid());
        Bid projectBid = validateBid(bid, project);
        Double previousMinBidValue = (Double) bidsCache.getIfPresent(project.getUuid());

        if (previousMinBidValue == null) {
            bidsCache.put(projectBid.getProjectUuid(), bid.getBidValue());
            project.setMinimumBidValue(bid.getBidValue());
        } else {
            if (previousMinBidValue > bid.getBidValue()) {
                bidsCache.put(projectBid.getProjectUuid(), bid.getBidValue());
                project.setMinimumBidValue(bid.getBidValue());
            }
        }
        return bidRepository.save(bid);
    }

    private Bid validateBid(Bid bid, Project project) {
        User user = userManagementRepository.findByUuid(bid.getBuyerUuid());
        if (ObjectUtils.isEmpty(user))
            throw new ResourceNotFoundException("Buyer with given uuid not found");
        if (ObjectUtils.isEmpty(project))
            throw new ResourceNotFoundException("Project with given uuid not found");
        if (project.getDeadLineTime().before(new Date()))
            throw new ServiceException("Bid Rejected : Project deadline has expired !!");
        if (project.getMaxBudget() < bid.getBidValue())
            throw new ServiceException("Bid Rejected : Bid value is above the max budget for the project !!");
        bid.setBuyer(user);
        bid.setProject(project);
        return bid;
    }

}
