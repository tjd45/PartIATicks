package uk.ac.cam.tjd45.oopjava.tick5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


abstract public class SourcePanel extends JPanel {
	
	private JRadioButton current;
	
	protected abstract boolean setSourceNone();
	protected abstract boolean setSourceFile();
	protected abstract boolean setSourceLibrary();
	
	public SourcePanel() {
		super();
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
		
		none.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceNone()) {
					//successful: file found and patterns loaded
					current = none;
				} else {
					//unsuccessful: re-enable previous source choice
					current.setSelected(true);
				} 
			}
		});
		
		JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
		
		file.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceFile()) {
					//successful: file found and patterns loaded
					current = file;
				} else {
					//unsuccessful: re-enable previous source choice
					current.setSelected(true);
				} 
			}
		});
		
		JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
		
		library.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (setSourceLibrary()) {
					//successful: file found and patterns loaded
					current = library;
				} else {
					//unsuccessful: re-enable previous source choice
					current.setSelected(true);
				} 
			}
		});
		
		//add RadioButtons to this JPanel
		add(none);
		add(file);
		add(library);
		//create a ButtonGroup containing all four buttons
		//Only one Button in a ButtonGroup can be selected at once
		ButtonGroup group = new ButtonGroup();
		group.add(none);
		group.add(file);
		group.add(library);
		current = none;
	} 
}
