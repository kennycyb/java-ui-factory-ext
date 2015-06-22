package com.wpl.ui.ext.gridview;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.table.TableColumn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridViewColumn extends TableColumn {

	private static Logger LOGGER = LoggerFactory.getLogger(GridViewColumn.class);

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String mFieldName;
	private final Class<?> mFieldType;
	private final Method mFieldGet;
	private final Method mFieldSet;

	public GridViewColumn(final String name, final Method getter, final Method setter) {
		mFieldName = name;
		mFieldType = getter.getReturnType();
		mFieldGet = getter;
		mFieldSet = setter;
		setHeaderValue(name);
		LOGGER.debug("intiailize: {}, {}, {}", new Object[] { name, getter, setter });
	}

	public Class<?> getFieldType() {
		return mFieldType;
	}

	public String getFieldName() {
		return mFieldName;
	}

	public Object getValue(final Object object) {
		if (object == null)
			return null;

		try {
			return mFieldGet.invoke(object, new Object[] {});
		} catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return null;
		}
	}

	public void setValue(final Object object, final Object value) {
		if (object == null)
			return;

		try {
			mFieldSet.invoke(object, value);
		} catch (final IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		}
	}
}
