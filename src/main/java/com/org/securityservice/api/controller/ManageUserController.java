package com.org.securityservice.api.controller;

import com.org.securityservice.api.model.UserRequest;
import com.org.securityservice.domain.model.UserRole;
import com.org.securityservice.domain.service.user.ManageUserService;
import com.org.securityservice.domain.service.user.UserRepository;
import com.org.securityservice.utils.UserRoleHelperUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/manageUser")
public class ManageUserController {

    private ManageUserService manageUserService;
    private UserRoleHelperUtil userRoleHelperUtil;

    @Autowired
    public ManageUserController(ManageUserService manageUserService, UserRoleHelperUtil userRoleHelperUtil) {
        this.manageUserService = manageUserService;
        this.userRoleHelperUtil = userRoleHelperUtil;
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @GetMapping(value = "/user")
    public ResponseEntity getUser(@RequestBody @Valid UserRequest userRequest) {

        return ResponseEntity.ok(this.manageUserService.getUserByUsername(userRequest.getUsername()));
    }

    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    @PutMapping("/addRole")
    public ResponseEntity addUserRole(@RequestParam("newRole") String role, @RequestBody @Valid UserRequest userRequest) {

        UserRole userRole = this.userRoleHelperUtil.validateAndConvertToUserRole(role);
        //todo
        this.manageUserService.addRole(userRole, userRequest.getUsername());

        return ResponseEntity.ok("success");
    }
}
