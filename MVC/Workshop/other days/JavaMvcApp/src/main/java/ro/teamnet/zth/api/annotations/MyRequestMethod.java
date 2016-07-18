package ro.teamnet.zth.api.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by user on 7/14/2016.
 */
@Target({ElementType.METHOD})
@Retention(RUNTIME)
@Documented
public @interface MyRequestMethod {
//    methodType fiind default nu trebuie specificate la adnotarea metodei
    String methodType() default "GET";
    String urlPath();
}
