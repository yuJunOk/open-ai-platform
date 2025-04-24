package com.example.mps.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mps.common.ResponseCode;
import com.example.mps.exception.BusinessException;
import com.example.mps.mapper.UserMapper;
import com.example.multicommon.pojo.domain.UserDo;
import com.example.multicommon.service.InnerUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部用户服务实现类
 * @author pengYuJun
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDo getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<UserDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDo::getAccessKey, accessKey);
        return userMapper.selectOne(queryWrapper);
    }
}
