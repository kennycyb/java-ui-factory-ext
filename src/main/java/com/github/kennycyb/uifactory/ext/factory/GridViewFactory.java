package com.github.kennycyb.uifactory.ext.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.swing.JScrollPane;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kennycyb.uifactory.core.factory.ComponentContext;
import com.github.kennycyb.uifactory.ext.gridview.GridView;
import com.github.kennycyb.uifactory.ext.gridview.GridViewColumn;
import com.github.kennycyb.uifactory.ext.gridview.UiGridViewColumns;

public final class GridViewFactory extends JxComponentFactory {

	private static Logger LOGGER = LoggerFactory.getLogger(GridViewFactory.class);

	public GridViewFactory() {

	}

	private Method tryGetSetter(final Class<?> clazz, final String name) {
		try {
			return clazz.getMethod("set" + name, new Class<?>[] {});
		} catch (NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

	private Method findGetter(final Class<?> clazz, final String name) {

		final String capName = StringUtils.capitalize(name);

		try {
			final Method method = clazz.getMethod("get" + capName, new Class<?>[0]);
			if (method != null) {
				return method;
			}
		} catch (NoSuchMethodException | SecurityException e) {
		}

		try {
			return clazz.getMethod("is" + capName, new Class<?>[0]);
		} catch (NoSuchMethodException | SecurityException e) {
		}

		return null;
	}

	private GridViewColumn initGridViewColumn(final Class<?> clazz, final Method method) {
		if (Modifier.isStatic(method.getModifiers()) || method.getParameterTypes().length != 0 || method.getName().equals("getClass")) {
			return null;
		}

		if (method.getName().startsWith("is") && method.getName().length() > 2) {
			// Is a boolean getter
			final String name = method.getName().substring(2, 2).toUpperCase() + method.getName().substring(3);
			final GridViewColumn column = new GridViewColumn(name, method, tryGetSetter(clazz, name));
			return column;
		}

		if (method.getName().startsWith("get") && method.getName().length() > 3) {
			// Is a getter
			final String name = method.getName().substring(3, 3).toUpperCase() + method.getName().substring(3);
			final GridViewColumn column = new GridViewColumn(name, method, tryGetSetter(clazz, name));
			return column;
		}

		return null;
	}

	@Override
	public void initialize(final ComponentContext context) {
		super.initialize(context);

		final GridView<?> gridview = (GridView<?>) context.getJxComponent();

		final Field field = (Field) context.getAnnotatedElement();

		final ParameterizedType type = (ParameterizedType) field.getGenericType();

		final Type actualType = type.getActualTypeArguments()[0];

		final Class<?> cls = (Class<?>) actualType;

		final UiGridViewColumns gvColumns = field.getAnnotation(UiGridViewColumns.class);

		int columnIndex = 0;

		if (gvColumns != null) {
			for (final String name : gvColumns.value()) {

				final GridViewColumn column = initGridViewColumn(cls, findGetter(cls, name));

				if (column == null) {
					continue;
				}

				column.setModelIndex(columnIndex++);

				LOGGER.debug("Adding column: {}", column.getFieldName());
				gridview.getColumnModel().addColumn(column);
			}
		} else {
			final Method[] methods = cls.getMethods();
			for (final Method method : methods) {
				final GridViewColumn column = initGridViewColumn(cls, method);

				if (column == null) {
					continue;
				}

				column.setModelIndex(columnIndex++);

				LOGGER.debug("Adding column: {}", column.getFieldName());
				gridview.getColumnModel().addColumn(column);
			}
		}

		// context.setComponent(gridview.getComponent());
		context.setJxComponent(gridview);
		context.setEnclosedComponent(new JScrollPane(gridview.getComponent()));
	}
}
