package com.apsrtc.service;

import com.apsrtc.exception.AdminException;
import com.apsrtc.model.Admin;
import com.apsrtc.model.CurrentAdminSession;
import com.apsrtc.repository.AdminRepository;
import com.apsrtc.repository.CurrentAdminSessionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CurrentAdminSessionRepository adminSessionRepository;

    @Override
    public Admin createAdmin(Admin admin) throws AdminException{
//        Admin a = adminRepository.findByEmail(admin.getEmail());
        List<Admin> admins = adminRepository.findByEmail(admin.getEmail());
        
        if(!admins.isEmpty()) throw new AdminException("Admin already present with the given email: " + admin.getEmail());
        
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin, String key) throws AdminException {
        CurrentAdminSession adminSession = adminSessionRepository.findByaid(key);
        if(adminSession == null) throw new AdminException("Please enter valid key or login first!");
        if(admin.getAdminID() != adminSession.getAdminID()) throw new AdminException("Invalid admin details, please login for updating admin!");
        return adminRepository.save(admin);
    }
    	
}
