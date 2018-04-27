package com.itl.iap.timer.mapper;
import com.itl.iap.timer.dao.Position;

import java.util.List;
public interface PositionMapper {
    List<Position> getPositionByCode();

}
