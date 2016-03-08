import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;


public class loadInputMethods {
	
	static Border blackline = BorderFactory.createLineBorder(Color.black);

	public static void loadBirdDataPanel(JPanel birdCard1){
		
		String[] breedStrings = {" Cobb 500 ", " Cobb 700 ", " Ross 308 ", " Ross 708 "};
		
		//the labels to the left of the inputs
		JLabel birdBreedLabel = new JLabel("  Breed: ");
		JLabel targetWeightLabel = new JLabel("  Target Weight  (lb): ");
		JLabel numBroilersLabel = new JLabel("  Number of Broilers: ");
		JLabel numFatalitiesLabel = new JLabel("  Number of Fatalitites: ");
		
		//the input areas themselves, on the right
		JComboBox<String> breedInputBox = new JComboBox<>(breedStrings);
		breedInputBox.setMaximumSize(breedInputBox.getPreferredSize());
		JTextField targetWeightField = new JTextField();
		targetWeightField.setMaximumSize(breedInputBox.getPreferredSize());
		JTextField numBroilersField = new JFormattedTextField();
		numBroilersField.setMaximumSize(breedInputBox.getPreferredSize());
		JTextField numFatalitiesField = new JFormattedTextField();
		numFatalitiesField.setMaximumSize(breedInputBox.getPreferredSize());
		
		Box breedBox = Box.createHorizontalBox();
		breedBox.add(birdBreedLabel);
		breedBox.add(Box.createHorizontalStrut(80));  //hard coded spacing is bad
		breedBox.add(breedInputBox);
		
		Box weightBox = Box.createHorizontalBox();
		weightBox.add(targetWeightLabel);
		weightBox.add(Box.createHorizontalStrut(16));
		weightBox.add(targetWeightField);
		
		Box broilersBox = Box.createHorizontalBox();
		broilersBox.add(numBroilersLabel);
		broilersBox.add(Box.createHorizontalStrut(12));
		broilersBox.add(numBroilersField);
		
		Box fatalBox = Box.createHorizontalBox();
		fatalBox.add(numFatalitiesLabel);
		fatalBox.add(Box.createHorizontalStrut(2));
		fatalBox.add(numFatalitiesField);

		//the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		//top.setBorder(blackline);
		//top.add(Box.createVerticalStrut(30));
		top.add(breedBox);
		top.add(Box.createVerticalStrut(10));
		top.add(weightBox);
		top.add(Box.createVerticalStrut(10));
		top.add(broilersBox);
		top.add(Box.createVerticalStrut(10));
		top.add(fatalBox);
		top.add(Box.createVerticalStrut(30));
		
		//top.add(Box.createHorizontalStrut(300));
		
		birdCard1.add(top);
	}//END loadBirdDataPanel

	public static void loadFeedIngredPanel(JPanel feedIngredientsPanel) {
		
		//labels on the left
		JLabel numPhasesLabel = new JLabel("  Number of Feeding Phases:  ");
		JLabel phaseNameLabel = new JLabel("  Phase Name:  ");
		JLabel numDaysPerPhaseLabel = new JLabel("  Number of Days in Phase:  ");
		
		//input areas on the right
		JTextField numPhasesField = new JTextField();
		numPhasesField.setColumns(5);
		JTextField phaseNameField = new JTextField();
		JTextField numDaysPerPhaseField = new JTextField();

		Box labelsBox = Box.createVerticalBox();
		labelsBox.add(numPhasesLabel);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(phaseNameLabel);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(numDaysPerPhaseLabel);
		
		Box fieldsBox = Box.createVerticalBox();
		fieldsBox.add(numPhasesField);
		fieldsBox.add(phaseNameField);
		fieldsBox.add(numDaysPerPhaseField);
		
		Box top = Box.createHorizontalBox();
		top.add(labelsBox);
		top.add(fieldsBox);
		
		//top.add(Box.createHorizontalStrut(300));
		feedIngredientsPanel.add(top);
		
	}//end loadFeedIngredPanel

	public static void loadFeedShippingPanel(JPanel feedShippingPanel) {
		
		JButton ingredientButton = new JButton("Add New Ingredient");
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		//buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		
		JLabel feedDistanceLabel = new JLabel("  Distance from Feed Mill: (miles)  ");
		JLabel feedMassDelLabel = new JLabel("  Amount of Feed Delivered (lb)  ");
		
		JTextField feedDistanceField = new JTextField();
		feedDistanceField.setColumns(5);
		JTextField feedMassDelField = new JTextField();
		JTextField ingredientsField = new JTextField("Enter Ingredient Name");
		JTextField percentIngedField = new JTextField("%");
		
		Box labelsBox = Box.createVerticalBox();
		labelsBox.add(feedDistanceLabel);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(feedMassDelLabel);
		labelsBox.add(Box.createVerticalStrut(5));
		
		Box fieldsBox = Box.createVerticalBox();
		fieldsBox.add(feedDistanceField);
		fieldsBox.add(feedMassDelField);
		
		Box top = Box.createHorizontalBox();
		top.add(labelsBox);
		top.add(fieldsBox);
		
		Box bottom = Box.createHorizontalBox();
		bottom.add(ingredientsField);
		bottom.add(percentIngedField);
		
		Box main = Box.createVerticalBox();
		main.add(top);
		main.add(Box.createVerticalStrut(50));
		main.add(bottom);
		
		buttonPane.add(ingredientButton);
		main.add(buttonPane);
		feedShippingPanel.add(main);
		
	}//end loadFeedShippingPanel

}
