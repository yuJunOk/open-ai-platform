package com.example.mps.service;

import com.example.multicommon.pojo.domain.UserInterfaceInfoDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author pengYuJun
* @description 针对表【tb_user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-04-20 15:54:09
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfoDo> {

    /**
     * 验证信息有效性
     * @param userInterfaceInfo
     * @param add
     */
    void validUserInterfaceInfo(UserInterfaceInfoDo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
