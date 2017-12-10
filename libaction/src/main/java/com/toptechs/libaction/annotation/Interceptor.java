package com.toptechs.libaction.annotation;


import com.toptechs.libaction.action.Valid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这里只能用于无参数的valid模型。
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Interceptor {

    Class<? extends Valid>[] value() default {};

}
