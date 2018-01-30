package harry.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import harry.enums.Validator;

/**
 * 
 * @author harry
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateParam {
	/**
	 * 验证器
	 * @return
	 */
	Validator[] value() default {};
	
	/**
	 * 参数的描述名称
	 * @return
	 */
	String name() default "";
}
