package com.example.mps.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mps.annoation.AuthCheck;
import com.example.mps.common.ResponseCode;
import com.example.mps.common.ResponseEntity;
import com.example.mps.constant.CommonConstant;
import com.example.mps.constant.UserConstant;
import com.example.mps.exception.BusinessException;
import com.example.multicommon.pojo.domain.UserInterfaceInfoDo;
import com.example.mps.pojo.dto.*;
import com.example.mps.pojo.dto.userinterfaceinfo.UserInterfaceInfoAddDto;
import com.example.mps.pojo.dto.userinterfaceinfo.UserInterfaceInfoQueryDto;
import com.example.mps.pojo.dto.userinterfaceinfo.UserInterfaceInfoQueryPageDto;
import com.example.mps.pojo.dto.userinterfaceinfo.UserInterfaceInfoUpdateDto;
import com.example.mps.pojo.vo.UserVo;
import com.example.mps.service.UserInterfaceInfoService;
import com.example.mps.service.UserService;
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
@RequestMapping("/userInterfaceInfo")
@Slf4j
public class UserInterfaceInfoController {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param userInterfaceInfoAddDto
     * @param request
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<Long> addUserInterfaceInfo(@RequestBody UserInterfaceInfoAddDto userInterfaceInfoAddDto, HttpServletRequest request) {
        if (userInterfaceInfoAddDto == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserInterfaceInfoDo userInterfaceInfo = new UserInterfaceInfoDo();
        BeanUtils.copyProperties(userInterfaceInfoAddDto, userInterfaceInfo);
        // 校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, true);
        UserVo loginUser = userService.getCurrentUser(request);
        userInterfaceInfo.setUserId(loginUser.getId());
        boolean result = userInterfaceInfoService.save(userInterfaceInfo);
        if (!result) {
            throw new BusinessException(ResponseCode.ERROR);
        }
        long newUserInterfaceInfoId = userInterfaceInfo.getId();
        return ResponseEntity.success(newUserInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param idDto
     * @param request
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<Boolean> deleteUserInterfaceInfo(@RequestBody IdDto idDto, HttpServletRequest request) {
        if (idDto == null || idDto.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserVo user = userService.getCurrentUser(request);
        long id = idDto.getId();
        // 判断是否存在
        UserInterfaceInfoDo oldUserInterfaceInfo = userInterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 仅本人或管理员可删除
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ResponseCode.FORBIDDEN);
        }
        boolean b = userInterfaceInfoService.removeById(id);
        return ResponseEntity.success(b);
    }

    /**
     * 更新
     *
     * @param userInterfaceInfoUpdateDto
     * @param request
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<Boolean> updateUserInterfaceInfo(@RequestBody UserInterfaceInfoUpdateDto userInterfaceInfoUpdateDto,
                                                     HttpServletRequest request) {
        if (userInterfaceInfoUpdateDto == null || userInterfaceInfoUpdateDto.getId() <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserInterfaceInfoDo userInterfaceInfo = new UserInterfaceInfoDo();
        BeanUtils.copyProperties(userInterfaceInfoUpdateDto, userInterfaceInfo);
        // 参数校验
        userInterfaceInfoService.validUserInterfaceInfo(userInterfaceInfo, false);
        UserVo user = userService.getCurrentUser(request);
        long id = userInterfaceInfoUpdateDto.getId();
        // 判断是否存在
        UserInterfaceInfoDo oldUserInterfaceInfo = userInterfaceInfoService.getById(id);
        if (oldUserInterfaceInfo == null) {
            throw new BusinessException(ResponseCode.NOT_FOUND);
        }
        // 仅本人或管理员可修改
        if (!oldUserInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ResponseCode.FORBIDDEN);
        }
        boolean result = userInterfaceInfoService.updateById(userInterfaceInfo);
        return ResponseEntity.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    public ResponseEntity<UserInterfaceInfoDo> getUserInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserInterfaceInfoDo userInterfaceInfo = userInterfaceInfoService.getById(id);
        return ResponseEntity.success(userInterfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param userInterfaceInfoQueryDto
     * @return
     */
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/list")
    public ResponseEntity<List<UserInterfaceInfoDo>> listUserInterfaceInfo(UserInterfaceInfoQueryDto userInterfaceInfoQueryDto) {
        UserInterfaceInfoDo userInterfaceInfoQuery = new UserInterfaceInfoDo();
        if (userInterfaceInfoQueryDto != null) {
            BeanUtils.copyProperties(userInterfaceInfoQueryDto, userInterfaceInfoQuery);
        }
        QueryWrapper<UserInterfaceInfoDo> queryWrapper = new QueryWrapper<>(userInterfaceInfoQuery);
        List<UserInterfaceInfoDo> userInterfaceInfoList = userInterfaceInfoService.list(queryWrapper);
        return ResponseEntity.success(userInterfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param userInterfaceInfoQueryPageDto
     * @param request
     * @return
     */
    @AuthCheck(anyRole = {}, mustRole = UserConstant.ADMIN_ROLE)
    @GetMapping("/list/page")
    public ResponseEntity<Page<UserInterfaceInfoDo>> listUserInterfaceInfoByPage(UserInterfaceInfoQueryPageDto userInterfaceInfoQueryPageDto, HttpServletRequest request) {
        if (userInterfaceInfoQueryPageDto == null) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        UserInterfaceInfoDo userInterfaceInfoQuery = new UserInterfaceInfoDo();
        BeanUtils.copyProperties(userInterfaceInfoQueryPageDto, userInterfaceInfoQuery);
        long current = userInterfaceInfoQueryPageDto.getCurrent();
        long size = userInterfaceInfoQueryPageDto.getPageSize();
        String sortField = userInterfaceInfoQueryPageDto.getSortField();
        String sortOrder = userInterfaceInfoQueryPageDto.getSortOrder();
        // 限制爬虫
        if (size > CommonConstant.LIMIT_PAGE_SIZE) {
            throw new BusinessException(ResponseCode.PARAMS_ERROR);
        }
        QueryWrapper<UserInterfaceInfoDo> queryWrapper = new QueryWrapper<>(userInterfaceInfoQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<UserInterfaceInfoDo> userInterfaceInfoPage = userInterfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResponseEntity.success(userInterfaceInfoPage);
    }

    // endregion

}