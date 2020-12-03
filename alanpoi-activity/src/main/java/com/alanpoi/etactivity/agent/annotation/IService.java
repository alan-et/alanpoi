package com.alanpoi.etactivity.agent.annotation;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Documented
@Indexed
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface IService {
    String value() default "";
}
