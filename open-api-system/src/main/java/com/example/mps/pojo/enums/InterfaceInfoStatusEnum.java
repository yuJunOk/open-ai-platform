package com.example.mps.pojo.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author pengYuJun
 */

public enum InterfaceInfoStatusEnum {

    /**
     *
     */
    OFFLINE("关闭", 0),
    ONLINE("上线", 1);

    private final String text;

    private final int value;

    InterfaceInfoStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<Integer> getValues() {
        return Arrays.stream(values()).map(item -> item.value).toList();
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}