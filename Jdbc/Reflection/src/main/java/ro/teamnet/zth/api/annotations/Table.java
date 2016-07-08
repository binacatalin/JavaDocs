package ro.teamnet.zth.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Created by user on 7/7/2016.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Table {
    String name() default "";
}
