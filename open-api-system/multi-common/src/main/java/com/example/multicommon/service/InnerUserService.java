package com.example.multicommon.service;

import com.example.multicommon.pojo.domain.UserDo;

/**
 * @author pengYuJun
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    UserDo getInvokeUser(String accessKey);
}