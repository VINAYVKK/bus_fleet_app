package com.apsrtc.service;

import com.apsrtc.exception.AdminException;
import com.apsrtc.exception.LoginException;
import com.apsrtc.model.AdminLoginDTO;
import com.apsrtc.model.CurrentAdminSession;

public interface AdminLoginService {
    public CurrentAdminSession adminLogin(AdminLoginDTO loginDTO) throws LoginException, AdminException;
    public String adminLogout(String key) throws LoginException;
}
