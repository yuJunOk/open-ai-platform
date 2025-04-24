package com.example.mps.service;

import com.example.multicommon.pojo.domain.InterfaceInfoDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author pengYuJun
* @description 针对表【tb_interface_info(接口信息)】的数据库操作Service
* @createDate 2025-04-20 15:13:07
*/
public interface InterfaceInfoService extends IService<InterfaceInfoDo> {

    /**
     * 校验信息有效
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfoDo interfaceInfo, boolean add);
}
