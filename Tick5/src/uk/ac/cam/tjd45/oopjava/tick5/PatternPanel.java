package uk.ac.cam.tjd45.oopjava.tick5;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uk.ac.cam.tjd45.oopjava.tick5.Pattern;

//TODO:  specify the appropriate import statements

abstract public class PatternPanel extends JPanel {
	
	private JList guiList;
	private Pattern currentPattern;
	private List<Pattern> patternList;
	
	abstract void onPatternChange();
	
	public PatternPanel() {
		super();
		setLayout(new BorderLayout());
		guiList = new JList();
		currentPattern = null;
		add(new JScrollPane(guiList));
		

		guiList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting() && (patternList != null)) {
					int sel = guiList.getSelectedIndex();
					if (sel != -1) {
						currentPattern = patternList.get(sel);
						onPatternChange();
					} 
				}
			}
		});
	}
	
	public Pattern getCurrentPattern() {
		return currentPattern;
	}
 
	public void setPatterns(List<Pattern> list) {
		if (list == null) {
		     currentPattern = null; //if list is null, then no valid pattern
		     guiList.setListData(new String[]{}); //no list item to select
		     return;
		}
		ArrayList<String> names = new ArrayList<String>();
		for(Pattern p : list){
			String name = p.getName();
			String author = p.getAuthor();
			names.add(name+" ("+author+")");
		}
		
		guiList.setListData(names.toArray());
		
		patternList = list;
		
		currentPattern = list.get(0); //select first element in list
		guiList.setSelectedIndex(0);  //select first element in guiList
	}

}