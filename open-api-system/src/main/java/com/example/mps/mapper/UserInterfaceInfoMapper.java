package com.example.mps.mapper;

import com.example.multicommon.pojo.domain.UserInterfaceInfoDo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author pengYuJun
* @description 针对表【tb_user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2025-04-20 15:54:09
* @Entity generator.domain.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfoDo> {

    List<UserInterfaceInfoDo> listTopInvokeInterfaceInfo(int limit);
}




