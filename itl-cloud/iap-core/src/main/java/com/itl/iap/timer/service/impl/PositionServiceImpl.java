package com.itl.iap.timer.service.impl;

import com.itl.iap.timer.dao.Position;
import com.itl.iap.timer.mapper.PositionMapper;
import com.itl.iap.timer.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luzhuhong on 2018/4/19.
 */
@Service("IPositionService")
public class PositionServiceImpl implements IPositionService {
    @Autowired
    PositionMapper positionMapper;
    @Override
    public List<Position> getPosition() {
        return positionMapper.getPositionByCode();
    }
}
