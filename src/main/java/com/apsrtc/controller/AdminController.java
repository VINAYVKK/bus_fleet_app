package com.apsrtc.controller;

import com.apsrtc.exception.AdminException;
import com.apsrtc.model.Admin;
import com.apsrtc.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/apsrtc")
public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("/admin/register")
    public ResponseEntity<Admin> registerAdmin(@Valid @RequestBody Admin admin) throws AdminException {
    	System.out.println("Admin Controller: " + admin);
        Admin savedAdmin = service.createAdmin(admin);
        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/admin/update")
    public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin admin, @RequestParam(required = false) String key) throws AdminException {
        Admin updatedAdmin = service.updateAdmin(admin, key);
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }
}
