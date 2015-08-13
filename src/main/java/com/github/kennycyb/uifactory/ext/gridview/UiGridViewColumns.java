package com.github.kennycyb.uifactory.ext.gridview;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation allows setting the initial order of the columns.
 *
 * @author kenny
 * @since 0.2
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UiGridViewColumns {
	/**
	 * Define the columns display order.
	 *
	 * @return List of property.
	 * @since 0.2
	 */
	String[]value();
}
