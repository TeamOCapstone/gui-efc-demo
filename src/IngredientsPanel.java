import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.text.*;

class IngredientsPanel extends JPanel{

	private GridBagLayout layout;
	IOclass ioclass;
	int nextRow;
	
	int phaseNumber;

	JLabel ingredientsLabel = new JLabel("  Ingredients:");
	JLabel percentLabel = new JLabel("% of Mix");

	ArrayList<JComboBox> feedSelections;
	ArrayList<JTextField> feedPercents;
	ArrayList<JLabel> percentSigns;
	
    public IngredientsPanel(IOclass InOut, int phaseNum){
    	ioclass = InOut;
    	phaseNumber = phaseNum;
    	layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(layout);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(ingredientsLabel, c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		add(percentLabel,c);
		
		nextRow = 1;
		feedSelections = new ArrayList<JComboBox>();
		feedPercents = new ArrayList<JTextField>();
		percentSigns = new ArrayList<JLabel>();
		
		ioclass.addFeedArrayList(feedSelections);
		ioclass.addPercentsArrayList(feedPercents);
		
		addIngredient();
    }

	public void addIngredient(){
		GridBagConstraints c = new GridBagConstraints();

		JComboBox<String> ingredientsField = new JComboBox<String>(ioclass.getFeedList("resources/feedlist.xml"));
		JTextField percentIngedField = new JTextField();
		JLabel percentSign = new JLabel("%");

		ingredientsField.setName("Phase"+phaseNumber+"IngredientName"+(nextRow-1));
		percentIngedField.setName("Phase"+phaseNumber+"IngredientPercentage"+(nextRow-1));
		
		feedSelections.add(ingredientsField);
		feedPercents.add(percentIngedField);
		percentSigns.add(percentSign);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = nextRow;
		c.anchor = GridBagConstraints.LINE_START;
		add(ingredientsField,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = nextRow;
		c.anchor = GridBagConstraints.LINE_START;
		add(percentIngedField,c);
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = nextRow;
		c.anchor = GridBagConstraints.LINE_START;
		add(percentSign,c);
		
		revalidate();
		repaint();
		
		nextRow++;

	}
	
	public void removeIngredient(){
	
		remove(feedSelections.get(nextRow-2));
		remove(feedPercents.get(nextRow-2));
		remove(percentSigns.get(nextRow-2));
	
		revalidate();
		repaint();
		
		feedSelections.remove(nextRow-2);
		feedPercents.remove(nextRow-2);
		percentSigns.remove(nextRow-2);
		
		nextRow--;
	}

}
