package in.clouthink.synergy.rbac.annotation;

import in.clouthink.synergy.rbac.model.Action;

import java.lang.annotation.*;

/**
 * The annotation to define permission
 *
 * @auther dz
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    /**
     * @return rest url (ant pattern supported)
     */
    String api();

    /**
     * @return allowed actions
     */
    Action[] action() default {};

}
