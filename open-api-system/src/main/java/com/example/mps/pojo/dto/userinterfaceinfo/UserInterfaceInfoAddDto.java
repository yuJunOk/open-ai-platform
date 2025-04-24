package com.example.mps.pojo.dto.userinterfaceinfo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pengYuJun
 */
@Data
public class UserInterfaceInfoAddDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8864196142517233122L;

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
}
