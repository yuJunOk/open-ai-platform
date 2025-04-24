package com.example.mps.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mps.annoation.AuthCheck;
import com.example.mps.common.ResponseCode;
import com.example.mps.common.ResponseEntity;
import com.example.mps.constant.CommonConstant;
import com.example.mps.constant.UserConstant;
import com.example.mps.exception.BusinessException;
import com.example.mps.pojo.bo.AkSkBo;
import com.example.multicommon.pojo.domain.InterfaceInfoDo;
import com.example.mps.pojo.dto.*;
import com.example.mps.pojo.dto.interfaceinfo.*;
import com.example.mps.pojo.enums.InterfaceInfoStatusEnum;
import com.example.mps.pojo.vo.UserVo;
import com.example.mps.service.InterfaceInfoService;
import com.example.mps.service.UserService;
import com.example.multicommon.pojo.domain.UserDo;
import com.example.sdkclient.client.SdkClient;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pengYuJun
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param interfaceInfoAddDo
     * @param request
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddDo interfaceInfoAddDo, HttpServletRequest request) {
        if (interfaceInfoAddDo == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        InterfaceInfoDo interfaceInfo = new InterfaceInfoDo();
        BeanUtils.copyProperties(interfaceInfoAddDo, interfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        UserVo loginUser = userService.getCurrentUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ResponseCode.ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResponseEntity.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param idDto
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteInterfaceInfo(@RequestBody IdDto idDto, HttpServletRequest request) {
        if (idDto == null || idDto.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserVo user = userService.getCurrentUser(request);
        long id = idDto.getId();
        // 判断是否存在
        InterfaceInfoDo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ResponseCode.FORBIDDEN);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResponseEntity.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateDo
     * @param request
     * @return
     */
    @PostMapping("/update")
    public ResponseEntity<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateDo interfaceInfoUpdateDo,
                                                     HttpServletRequest request) {
        if (interfaceInfoUpdateDo == null || interfaceInfoUpdateDo.getId() == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        InterfaceInfoDo interfaceInfo = new InterfaceInfoDo();
        BeanUtils.copyProperties(interfaceInfoUpdateDo, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        UserVo user = userService.getCurrentUser(request);
        long id = interfaceInfoUpdateDo.getId();
        // 判断是否存在
        InterfaceInfoDo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ResponseCode.FORBIDDEN);
        }
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResponseEntity.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public ResponseEntity<InterfaceInfoDo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        InterfaceInfoDo interfaceInfo = interfaceInfoService.getById(id);
        return ResponseEntity.success(interfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param interfaceInfoQueryDto
     * @return
     */
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/list")
    public ResponseEntity<List<InterfaceInfoDo>> listInterfaceInfo(InterfaceInfoQueryDto interfaceInfoQueryDto) {
        InterfaceInfoDo interfaceInfoQuery = new InterfaceInfoDo();
        if (interfaceInfoQueryDto != null) {
            BeanUtils.copyProperties(interfaceInfoQueryDto, interfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfoDo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        List<InterfaceInfoDo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResponseEntity.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param interfaceInfoQueryPageDto
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public ResponseEntity<Page<InterfaceInfoDo>> listInterfaceInfoByPage(InterfaceInfoQueryPageDto interfaceInfoQueryPageDto, HttpServletRequest request) {
        if (interfaceInfoQueryPageDto == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        InterfaceInfoDo interfaceInfoQuery = new InterfaceInfoDo();
        BeanUtils.copyProperties(interfaceInfoQueryPageDto, interfaceInfoQuery);
        long current = interfaceInfoQueryPageDto.getCurrent();
        long size = interfaceInfoQueryPageDto.getPageSize();
        String sortField = interfaceInfoQueryPageDto.getSortField();
        String sortOrder = interfaceInfoQueryPageDto.getSortOrder();
        String description = interfaceInfoQuery.getDescription();
        // description 需支持模糊搜索
        interfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > CommonConstant.LIMIT_PAGE_SIZE) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfoDo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfoDo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResponseEntity.success(interfaceInfoPage);
    }

    // endregion

    /**
     * 发布
     *
     * @param idDto
     * @param request
     * @return
     */
    @PostMapping("/online")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<Boolean> onlineInterfaceInfo(@RequestBody IdDto idDto,
                                                     HttpServletRequest request) {
        if (idDto == null || idDto.getId() == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        long id = idDto.getId();
        // 判断是否存在
        InterfaceInfoDo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 判断该接口是否可以调用
//        com.yupi.yuapiclientsdk.model.User user = new com.yupi.yuapiclientsdk.model.User();
//        user.setUsername("test");
//        String username = yuApiClient.getUsernameByPost(user);
//        if (StringUtils.isBlank(username)) {
//            throw new BusinessException(ResponseCode.SYSTEM_ERROR, "接口验证失败");
//        }
        // 仅本人或管理员可修改
        InterfaceInfoDo interfaceInfo = new InterfaceInfoDo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResponseEntity.success(result);
    }

    /**
     * 下线
     *
     * @param idDto
     * @param request
     * @return
     */
    @PostMapping("/offline")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<Boolean> offlineInterfaceInfo(@RequestBody IdDto idDto,
                                                      HttpServletRequest request) {
        if (idDto == null || idDto.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        long id = idDto.getId();
        // 判断是否存在
        InterfaceInfoDo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 仅本人或管理员可修改
        InterfaceInfoDo interfaceInfo = new InterfaceInfoDo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResponseEntity.success(result);
    }

    /**
     * 测试调用
     *
     * @param interfaceInfoInvokeDto
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public ResponseEntity<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeDto interfaceInfoInvokeDto,
                                                     HttpServletRequest request) {
        if (interfaceInfoInvokeDto == null || interfaceInfoInvokeDto.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        long id = interfaceInfoInvokeDto.getId();
        String userRequestParams = interfaceInfoInvokeDto.getUserRequestParams();
        // 判断是否存在
        InterfaceInfoDo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        if (oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR, "接口已关闭");
        }
        AkSkBo akSkBo = userService.getCurrentUserAkSk(request);
        String accessKey = akSkBo.getAccessKey();
        String secretKey = akSkBo.getSecretKey();
        SdkClient tempClient = new SdkClient(accessKey, secretKey);
        UserDo user = JSONUtil.toBean(userRequestParams, UserDo.class);
        String usernameByPost = tempClient.getUsernameByPost(user);
        return ResponseEntity.success(usernameByPost);
    }
}