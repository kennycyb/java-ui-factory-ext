package com.github.kennycyb.uifactory.ext.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kennycyb.uifactory.core.components.IComponent;
import com.github.kennycyb.uifactory.core.factory.ComponentContext;
import com.github.kennycyb.uifactory.core.factory.IAnnotationHandler;
import com.github.kennycyb.uifactory.core.factory.IComponentFactory;
import com.github.kennycyb.uifactory.core.factory.IUiFactory;
import com.github.kennycyb.uifactory.core.factory.impl.components.awt.ComponentFactory;

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
