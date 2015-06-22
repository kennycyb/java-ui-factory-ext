package com.wpl.ui.ext.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wpl.ui.components.IComponent;
import com.wpl.ui.factory.ComponentContext;
import com.wpl.ui.factory.IAnnotationHandler;
import com.wpl.ui.factory.IComponentFactory;
import com.wpl.ui.factory.IUiFactory;
import com.wpl.ui.factory.impl.components.awt.ComponentFactory;

public class JxComponentFactory implements IComponentFactory {

	private static Logger LOGGER = LoggerFactory.getLogger(ComponentFactory.class);

	private final List<IAnnotationHandler<?>> mAnnotationHandlers = new ArrayList<>();

	protected void addAnnotationHandler(final IAnnotationHandler<?> handler) {
		mAnnotationHandlers.add(handler);
	}

	@Override
	public void createInstance(final IUiFactory factory, final ComponentContext context) throws Exception {
		Object instance = null;

		if (context.getParentContext() != null && context.getType().getDeclaringClass() != null && !Modifier.isStatic(context.getType().getModifiers())) {

			final Class<?> innerClass = context.getType();
			final Class<?> outerClass = context.getParentContext().getType();

			final Constructor<?> innerClassConstructor = innerClass.getDeclaredConstructor(outerClass);

			innerClassConstructor.setAccessible(true);

			instance = innerClassConstructor.newInstance(context.getParentContext().getComponent());
		} else {

			LOGGER.debug("{}|creating from {}", context.getId(), context.getType());

			instance = context.getType().newInstance();
		}

		context.setJxComponent(IComponent.class.cast(instance));

	}

	@Override
	public void initialize(final ComponentContext context) {
	}

}
