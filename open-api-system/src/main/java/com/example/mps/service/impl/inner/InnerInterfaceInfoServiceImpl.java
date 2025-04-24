package com.example.mps.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mps.common.ResponseCode;
import com.example.mps.exception.BusinessException;
import com.example.mps.mapper.InterfaceInfoMapper;
import com.example.multicommon.pojo.domain.InterfaceInfoDo;
import com.example.multicommon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * 内部接口服务实现类
 *
 * @author pengYuJun
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfoDo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        LambdaQueryWrapper<InterfaceInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(InterfaceInfoDo::getUrl, url);
        queryWrapper.eq(InterfaceInfoDo::getMethod, method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
