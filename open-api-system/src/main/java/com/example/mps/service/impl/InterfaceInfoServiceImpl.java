package com.example.mps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mps.common.ResponseCode;
import com.example.mps.exception.BusinessException;
import com.example.multicommon.pojo.domain.InterfaceInfoDo;
import com.example.mps.service.InterfaceInfoService;
import com.example.mps.mapper.InterfaceInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author pengYuJun
* @description 针对表【tb_interface_info(接口信息)】的数据库操作Service实现
* @createDate 2025-04-20 15:13:07
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfoDo>
    implements InterfaceInfoService{

    @Override
    public void validInterfaceInfo(InterfaceInfoDo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ResponseCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "名称过长");
        }
    }
}




