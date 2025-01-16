package homeworke.lesson8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface Cache {
    CacheType cacheType() default CacheType.IN_MEMORY;
    String failNamePrefix() default "";
    boolean zip() default false;
    Class<?>[] identityBy() default {};
    int listLimit() default Integer.MAX_VALUE;
}
