package com.apsrtc.service;

import com.apsrtc.exception.AdminException;
import com.apsrtc.model.Admin;

public interface AdminService {
    public Admin createAdmin(Admin admin) throws AdminException;
    public Admin updateAdmin(Admin admin, String key) throws AdminException;
}
