package org.example.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodExecutionAnnotation {

    /**
     * 自定义消息内容
     * @return  返回自定义结果
     */
    String value() default "方法即将被执行";

    /**
     * 是否打印方法参数（默认开启)
     */
    boolean logArguments() default true;

    /**
     * 是否打印调用者信息
     */
    boolean logCaller() default true;
}
