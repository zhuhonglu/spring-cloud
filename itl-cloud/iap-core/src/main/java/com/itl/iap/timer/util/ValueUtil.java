
package com.itl.iap.timer.util;

/**
 * 封装常用的String参数
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午1:38:39
 * @version 1.0
 */
public final class ValueUtil {

    public static final String NULL = "null";
    public static final String CATIDS = "cateIds";
    // 默认的删除标记
    // 未删除
    public static final String DEFAULTDELFLAG = "0";
    // 已经删除
    public static final String ALREADYDELFLAG = "1";
    // 获取管理员名称
    public static final String NAME = "name";

    /* 添加日志需要的获取菜单名称 */
    public static final String OPERAPATH = "operaPath";
  
    // 促销LOGO控制器 END
    // 拼接token的字符串
    public static final String TOKENPARAM = "?CSRFToken=";
    public static final String PAGEBEANPARAM = "&pageNo=";
    public static final String TOKENPARAM2 = "&CSRFToken=";
    public static final String PAGENO = "&pageNo=";
    public static final String PAGESIZE = "&pageSize=";

    private ValueUtil() {
        super();
    }
}
