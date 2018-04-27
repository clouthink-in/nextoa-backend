package in.clouthink.synergy.rbac.annotation;

import java.lang.annotation.*;

/**
 * @author dz
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Resource {

    /**
     * @return the value type
     */
    String type() default "";

    /**
     * @return the parent value code if not empty or not blank
     */
    String parent() default "";

    /**
     * @return the code of value , must be unique in globle
     */
    String code();

    /**
     * @return the display name of value
     */
    String name();

    /**
     * @return
     */
    Metadata[] metadata() default {};

    /**
     * @return
     */
    Permission[] permission() default {};

}
