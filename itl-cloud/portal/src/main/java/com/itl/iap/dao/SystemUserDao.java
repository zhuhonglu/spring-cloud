package com.itl.iap.dao;

import com.itl.iap.pojo.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Joeysin on  2017/8/18  上午10:22.
 * Describe：
 */
@Mapper
public interface SystemUserDao {

    List<SystemUser> findSystenUserByname();


}
