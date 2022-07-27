package org.liu.common.service.datascope;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {
    DataColumn[] value() default {};
    boolean ignore() default false;
}
