package com.yong.orders.annotation;

/**
 * Created by yong.a.liang on 6/22/2017.
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType
        .CONSTRUCTOR,
        ElementType
                .PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {
    String message() default "Field should be unique.";
    String fields() default "";
}
