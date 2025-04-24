package com.example.mps.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mps.common.ResponseCode;
import com.example.mps.exception.BusinessException;
import com.example.multicommon.pojo.domain.UserInterfaceInfoDo;
import com.example.mps.service.UserInterfaceInfoService;
import com.example.mps.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author pengYuJun
* @description 针对表【tb_user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2025-04-20 15:54:09
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfoDo>
    implements UserInterfaceInfoService{

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfoDo userInterfaceInfo, boolean add) {
        if (userInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        // 创建时，所有参数必须非空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0) {
                throw new BusinessException(ResponseCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum() < 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        // 判断
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        LambdaUpdateWrapper<UserInterfaceInfoDo> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserInterfaceInfoDo::getInterfaceInfoId, interfaceInfoId);
        updateWrapper.eq(UserInterfaceInfoDo::getUserId, userId);
        //updateWrapper.gt(UserInterfaceInfoDo::getLeftNum, 0);
        updateWrapper.setSql("left_num = left_num - 1, total_num = total_num + 1");
        return this.update(updateWrapper);
    }
}




