package com.itl.iap.service.impl;


import com.itl.iap.dao.SystemUserDao;
import com.itl.iap.pojo.SystemUser;
import com.itl.iap.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {


    @Autowired
    private SystemUserDao systemUserDao;

    @Override
    public List<SystemUser> findOneWithAuthoritiesByLogin() {
        List<SystemUser> user = systemUserDao.findSystenUserByname();
        return user;
    }


}
