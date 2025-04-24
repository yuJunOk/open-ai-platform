package com.example.mps.pojo.dto.userinterfaceinfo;

import com.example.mps.pojo.dto.PageDto;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengYuJun
 */
@Data
public class UserInterfaceInfoQueryPageDto extends PageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 934949857733507123L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;
}
