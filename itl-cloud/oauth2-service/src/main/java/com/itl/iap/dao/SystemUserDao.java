package com.itl.iap.dao;

import com.itl.iap.domain.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Joeysin on  2017/8/18  上午10:22.
 * Describe：
 */
@Mapper
public interface SystemUserDao {

    SystemUser findSystenUserByname(String name);


}
