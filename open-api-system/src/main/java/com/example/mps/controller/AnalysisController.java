package com.example.mps.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mps.annoation.AuthCheck;
import com.example.mps.common.ResponseCode;
import com.example.mps.common.ResponseEntity;
import com.example.mps.constant.UserConstant;
import com.example.mps.exception.BusinessException;
import com.example.mps.mapper.UserInterfaceInfoMapper;
import com.example.mps.pojo.vo.InterfaceInfoVO;
import com.example.mps.service.InterfaceInfoService;
import com.example.multicommon.pojo.domain.InterfaceInfoDo;
import com.example.multicommon.pojo.domain.UserInterfaceInfoDo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfoDo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        Map<Long, List<UserInterfaceInfoDo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfoDo::getInterfaceInfoId));
        LambdaQueryWrapper<InterfaceInfoDo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(InterfaceInfoDo::getId, interfaceInfoIdObjMap.keySet());
        List<InterfaceInfoDo> list = interfaceInfoService.list(queryWrapper);
        if (list.isEmpty()) {
            throw new BusinessException(ResponseCode.ERROR);
        }
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResponseEntity.success(interfaceInfoVOList);
    }
}
