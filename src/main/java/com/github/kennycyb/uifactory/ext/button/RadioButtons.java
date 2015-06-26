package com.github.kennycyb.uifactory.ext.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.github.kennycyb.uifactory.core.events.EventHandler;

public class RadioButtons extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 7363872197150033227L;

	private final ButtonGroup mButtonGroup = new ButtonGroup();
	private final List<JRadioButton> mRadioButtons = new ArrayList<JRadioButton>();

	// ~ Events ----------------------------------------------------------------

	/**
	 * actionPerformed - when any JRadioButton is clicked
	 */
	private final EventHandler<ActionEvent> actionPerformed = new EventHandler<ActionEvent>();

	/**
	 * Forward actionPerformed action to the owner of this control
	 */
	private final ActionListener mButtonListener = e -> actionPerformed.invoke(e);

	public final void addRadioButton(final JRadioButton button) {

		button.addActionListener(mButtonListener);

		mRadioButtons.add(button);
		mButtonGroup.add(button);
		this.add(button);
	}

}
