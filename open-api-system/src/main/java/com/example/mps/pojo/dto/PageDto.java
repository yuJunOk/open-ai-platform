package com.example.mps.pojo.dto;

import com.example.mps.constant.CommonConstant;
import lombok.Data;

/**
 * @author pengYuJun
 */
@Data
public class PageDto {
    /**
     *
     */
    private Long current;

    /**
     *
     */
    private Long pageSize;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}