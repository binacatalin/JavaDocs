package ro.teamnet.zth.api.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;

/**
 * Created by user on 7/7/2016.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Id {
    String name() default "id";
}
