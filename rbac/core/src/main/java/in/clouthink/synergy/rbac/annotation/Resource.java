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
     * @return the resource type
     */
    String type() default "";

    /**
     * @return the parent resource code if not empty or not blank
     */
    String parent() default "";

    /**
     * @return the code of resource , must be unique in globle
     */
    String code();

    /**
     * @return the display name of resource
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
