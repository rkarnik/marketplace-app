package com.intuit.controller;

import com.intuit.exception.BadRequestException;
import com.intuit.exception.ResourceNotFoundException;
import com.intuit.model.Bid;
import com.intuit.model.Project;
import com.intuit.model.User;
import com.intuit.service.MarketPlaceService;
import com.intuit.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MarketPlaceController {

    @Autowired
    private MarketPlaceService marketPlaceService;

    @Autowired
    private UserManagementService userManagementService;

    @RequestMapping(method = RequestMethod.POST, value = "/projects", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createProject(@RequestBody Project project) {
        validateProjectCreationRequest(project);
        return new ResponseEntity<>(marketPlaceService.createProject(project), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/bids", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createBid(@RequestBody Bid bid) {
        validateBidRequest(bid);
        return new ResponseEntity<>(marketPlaceService.createBid(bid), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        validateUserCreationRequest(user);
        return new ResponseEntity<>(userManagementService.createUser(user), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/projects", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> getProjects(@RequestParam String projectBidStatus) {

        if (!StringUtils.isEmpty(projectBidStatus))
            return new ResponseEntity<>(marketPlaceService.getProjectsByBidStatus(projectBidStatus), HttpStatus.OK);
        else
            return new ResponseEntity<>(marketPlaceService.getProjects(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_uuid}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> getProjectById(@PathVariable("project_uuid") String projectUuid) {
        Project project = marketPlaceService.getProjectByUuid(projectUuid);
        if (ObjectUtils.isEmpty(project))
            throw new ResourceNotFoundException("Project with uuid " + projectUuid + " not found");

        return new ResponseEntity<>(marketPlaceService.getProjectByUuid(projectUuid), HttpStatus.OK);
    }

    private boolean validateProjectCreationRequest(Project project) {
        if (StringUtils.isEmpty(project.getName()))
            throw new BadRequestException("Project name must be specified");
        if (StringUtils.isEmpty(project.getDescription()))
            throw new BadRequestException("Project description must be specified");
        if (project.getDeadLineTime() == null)
            throw new BadRequestException("Project deadLineTime must be specified");
        return true;
    }

    private boolean validateBidRequest(Bid bid) {
        if (StringUtils.isEmpty(bid.getBuyerUuid()))
            throw new BadRequestException("Buyer uuid  must be specified");
        if (StringUtils.isEmpty(bid.getProjectUuid()))
            throw new BadRequestException("Project uuid must be specified");
        if (bid.getBidValue() == null)
            throw new BadRequestException("Bid value must be specified");
        return true;
    }

    private boolean validateUserCreationRequest(User user) {
        if (StringUtils.isEmpty(user.getFirstName()))
            throw new BadRequestException("First name of user must be specified");
        if (StringUtils.isEmpty(user.getLastName()))
            throw new BadRequestException("Last name must be specified");
        return true;
    }

}
