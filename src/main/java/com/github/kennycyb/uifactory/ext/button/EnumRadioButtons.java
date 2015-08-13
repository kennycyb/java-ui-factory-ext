package com.github.kennycyb.uifactory.ext.button;

import java.lang.reflect.Field;

import javax.swing.JRadioButton;

public final class EnumRadioButtons<E extends Enum<?>> extends RadioButtons {

	/**
	 *
	 */
	private static final long serialVersionUID = 7344509985982715108L;

	public EnumRadioButtons(final Class<E> enumClass) {

		final Field[] fields = enumClass.getDeclaredFields();

		for (final Field field : fields) {

			if (!field.isEnumConstant()) {
				continue;
			}

			field.setAccessible(true);

			try {
				final Enum<?> e = (Enum<?>) field.get(null);
				final JRadioButton button = new JRadioButton();
				button.setText(e.name());
				addRadioButton(button);
			} catch (final IllegalArgumentException e1) {
				continue;
			} catch (final IllegalAccessException e1) {
				continue;
			}
		}
	}
}
