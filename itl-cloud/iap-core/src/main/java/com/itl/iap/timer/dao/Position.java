package com.itl.iap.timer.dao;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 岗位表
 * @author XUEZEZHAN
 * @time 2018年2月2日 下午3:25:33
 * @since JDK 1.8
 */
@Data
public class Position{
    /**
     * 表ID，主键，供其他表做外键.
     */

    private Long positionId;

    /**
     * 部门id.
     */

    private Long unitId;

    /**
     * 部门名称
     */

    private String unitName;

    /**
     * 岗位编码.
     */

    private String positionCode;

    /**
     * 岗位名称.
     */

    @Length(max = 100)
    @NotEmpty
    private String name;

    /**
     * 岗位描述
     */

    @Length(max = 255)
    private String description;

    /**
     * 上级岗位id.
     */

    private Long parentPositionId;
    /**
     * 上级岗位名称
     */

    private String parentPositionName;




}
