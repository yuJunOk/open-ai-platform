package com.example.mps.annoation;

import com.example.mps.constant.UserConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

/**
 * @author pengYuJun
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {

    /**
     * 有任何一个角色
     *
     * @return
     */
    int[] anyRole();

    // https://t.zsxq.com/0emozsIJh

    /**
     * 必须有某个角色
     *
     * @return
     */
    int mustRole();

}
