package com.example.multicommon.service;

import com.example.multicommon.pojo.domain.InterfaceInfoDo;

/**
 * @author pengYuJun
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    InterfaceInfoDo getInterfaceInfo(String path, String method);
}