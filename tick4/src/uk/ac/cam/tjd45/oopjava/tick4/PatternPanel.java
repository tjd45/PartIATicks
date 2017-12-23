package uk.ac.cam.tjd45.oopjava.tick4;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import uk.ac.cam.tjd45.oopjava.tick4.Pattern;

//TODO:  specify the appropriate import statements

public class PatternPanel extends JPanel {
	
	private JList guiList;
	
	public PatternPanel() {
		super();
		setLayout(new BorderLayout());
		guiList = new JList();
		add(new JScrollPane(guiList));
	}
 
	public void setPatterns(List<Pattern> list) {
		ArrayList<String> names = new ArrayList<String>();
		for(Pattern p : list){
			String name = p.getName();
			String author = p.getAuthor();
			names.add(name+" ("+author+")");
		}
		
		guiList.setListData(names.toArray());
	}

}