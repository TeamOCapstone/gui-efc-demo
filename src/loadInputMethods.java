import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.ItemSelectable;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class loadInputMethods {

	static JComboBox<String> barnCountyInputBox;
	static JTabbedPane phase;
	static int numberOfPhases;
	static Border blackline = BorderFactory.createLineBorder(Color.black);
	static IOclass inoutclass;

	public static void loadBirdDataPanel(JPanel birdPanel, IOclass ioclass) {

		GroupLayout layout = new GroupLayout(birdPanel);
		birdPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		String[] breedStrings = { "-Choose Breed-", " Cobb 500 ", " Cobb 700 ", " Ross 308 ",
				" Ross 708 " };

		// the labels to the left of the inputs
		JLabel birdBreedLabel = new JLabel("  Breed: ");
		JLabel targetWeightLabel = new JLabel("  Target Weight  (lb): ");
		JLabel numBroilersLabel = new JLabel("  Number of Broilers: ");
		JLabel numFatalitiesLabel = new JLabel("  Number of Fatalitites: ");

		// the input areas themselves, on the right
		JComboBox<String> breedInputBox = new JComboBox<>(breedStrings);
		JTextField targetWeightField = new JTextField();
		JTextField numBroilersField = new JFormattedTextField();
		JTextField numFatalitiesField = new JFormattedTextField();

		// adding to reference class
		ioclass.addInput(breedInputBox);
		ioclass.addInput(targetWeightField);
		ioclass.addInput(numBroilersField);
		ioclass.addInput(numFatalitiesField);

		// naming IO components with the same name they will have in the XML
		// file
		breedInputBox.setName("Breed_Input");
		targetWeightField.setName("Target_Weight");
		numBroilersField.setName("Number_Of_Broilers");
		numFatalitiesField.setName("Number_Of_Fatalities");

		// Settings and layouts
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(birdBreedLabel)
				.addComponent(targetWeightLabel)
				.addComponent(numBroilersLabel)
				.addComponent(numFatalitiesLabel)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(breedInputBox)
				.addComponent(targetWeightField)
				.addComponent(numBroilersField)
				.addComponent(numFatalitiesField)
			)
			
		);



		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(birdBreedLabel)
				.addComponent(breedInputBox)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(targetWeightLabel)
				.addComponent(targetWeightField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(numBroilersLabel)
				.addComponent(numBroilersField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(numFatalitiesLabel)
				.addComponent(numFatalitiesField)
			)
		);

		layout.linkSize(SwingConstants.VERTICAL, birdBreedLabel, breedInputBox, targetWeightLabel, targetWeightField, numBroilersLabel, numBroilersField, numFatalitiesLabel, numFatalitiesField);
		layout.linkSize(SwingConstants.HORIZONTAL,birdBreedLabel, targetWeightLabel,  numBroilersLabel, numFatalitiesLabel);
		layout.linkSize(SwingConstants.HORIZONTAL, breedInputBox, targetWeightField,  numBroilersField,  numFatalitiesField);




	}

	public static void loadFeedIngredPanel(JPanel feedIngredientsPanel, IOclass ioclass) {

		inoutclass = ioclass;

		GroupLayout layout = new GroupLayout(feedIngredientsPanel);
		feedIngredientsPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);


		JLabel numPhasesLabel = new JLabel("  Number of Feeding Phases:  ");
		final JTextField numPhasesField = new JTextField();
		ioclass.addInput(numPhasesField);
		numPhasesField.setName("Number_Of_Phases");

		JButton setPhaseNumber = new JButton("Set");
		ioclass.addPhaseButton(setPhaseNumber);
		
		phase = new JTabbedPane();


		setPhaseNumber.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {
         		numberOfPhases = Integer.parseInt(numPhasesField.getText());
         		phase.removeAll();
         		inoutclass.removePhaseInfo();
            	for(int i=0;i<numberOfPhases;i++){
            		JPanel phasePanel = new JPanel();
            		loadPhasePanel(phasePanel, inoutclass, i);
            		phase.add("Phase "+(i+1), phasePanel);
            	}
         	}
      	});

		// Settings and layout
		
		
		layout.setHorizontalGroup(layout.createParallelGroup()
			.addGroup(layout.createSequentialGroup()
				.addComponent(numPhasesLabel)
				.addComponent(numPhasesField)
				.addComponent(setPhaseNumber)
			)
			.addGroup(layout.createSequentialGroup()
			)
			.addComponent(phase)
			
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(numPhasesLabel)
				.addComponent(numPhasesField)
				.addComponent(setPhaseNumber)
			)
			.addComponent(phase)
		);
		
		numPhasesField.setColumns(5);
		layout.linkSize(SwingConstants.VERTICAL, numPhasesLabel, numPhasesField, setPhaseNumber);
		layout.linkSize(SwingConstants.HORIZONTAL, numPhasesField, setPhaseNumber);


	}
	
	private static void loadPhasePanel(JPanel phase, IOclass ioclass, int phaseNumber){
	
		GroupLayout layout = new GroupLayout(phase);
		phase.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

		JLabel phaseNameLabel = new JLabel("  Phase Name:  ");
		JLabel numDaysInPhaseLabel = new JLabel("  Number of Days in Phase:  ");
		JLabel spacer = new JLabel("");

		// Inputs themselves
		JTextField phaseNameField = new JTextField();
		JTextField numDaysInPhaseField = new JTextField();

		// naming IO components with the same name they will have in the XML file
		phaseNameField.setName("PhaseName"+phaseNumber);
		numDaysInPhaseField.setName("DaysInPhase"+phaseNumber);

		ioclass.addInput(phaseNameField,true);
		ioclass.addInput(numDaysInPhaseField,true);

		final IngredientsPanel ingredientsPanel = new IngredientsPanel(ioclass, phaseNumber);
		
		JButton addIngredientButton = new JButton("Add Ingredient");
		JButton removeIngredientButton = new JButton("Remove Ingredient");

		addIngredientButton.setName("addFeedButton"+phaseNumber);
		ioclass.addFeedButton(addIngredientButton);
	
		addIngredientButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {ingredientsPanel.addIngredient();}
      	});
      	
		removeIngredientButton.addActionListener(new ActionListener() {
         	public void actionPerformed(ActionEvent e) {ingredientsPanel.removeIngredient();}
      	});


		// Settings and layouts
		
		layout.setHorizontalGroup(layout.createParallelGroup()
			.addComponent(ingredientsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
					.addComponent(phaseNameLabel)
					.addComponent(numDaysInPhaseLabel)
				)
				.addGroup(layout.createParallelGroup()
					.addComponent(phaseNameField)
					.addComponent(numDaysInPhaseField)
				)
			)
			.addGroup(layout.createSequentialGroup()
				.addComponent(addIngredientButton)
				.addComponent(removeIngredientButton)
			)
			.addComponent(spacer)
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(phaseNameLabel)
				.addComponent(phaseNameField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(numDaysInPhaseLabel)
				.addComponent(numDaysInPhaseField)
			)
			.addComponent(spacer)
			.addComponent(ingredientsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addGroup(layout.createParallelGroup()
				.addComponent(addIngredientButton)
				.addComponent(removeIngredientButton)
			)
		);

		phaseNameField.setColumns(15);
		layout.linkSize(SwingConstants.VERTICAL, spacer, numDaysInPhaseLabel, phaseNameLabel, numDaysInPhaseField, phaseNameField);
		layout.linkSize(SwingConstants.HORIZONTAL,numDaysInPhaseLabel, phaseNameLabel);
		layout.linkSize(SwingConstants.HORIZONTAL, numDaysInPhaseField, phaseNameField);

	}

	public static void loadFeedShippingPanel(JPanel feedShippingPanel, IOclass ioclass) {


		GroupLayout layout = new GroupLayout(feedShippingPanel);
		feedShippingPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);


		JLabel feedDistanceLabel = new JLabel("  Distance from Feed Mill: (miles)  ");
		JLabel feedMassDelLabel = new JLabel("  Amount of Feed Delivered (lb)  ");

		// Inputs themselves
		JTextField feedDistanceField = new JTextField();
		JTextField feedMassDelField = new JTextField();

		ioclass.addInput(feedDistanceField);
		ioclass.addInput(feedMassDelField);

		// naming IO components with the same name they will have in the XML file
		feedDistanceField.setName("Feed_Distance");
		feedMassDelField.setName("Feed_Mass");

		// Settings and layouts
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(feedDistanceLabel)
				.addComponent(feedMassDelLabel)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(feedDistanceField)
				.addComponent(feedMassDelField)
			)
			
		);

		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(feedDistanceLabel)
				.addComponent(feedDistanceField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(feedMassDelLabel)
				.addComponent(feedMassDelField)
			)
		);

		feedDistanceField.setColumns(5);
		layout.linkSize(SwingConstants.VERTICAL, feedDistanceLabel, feedMassDelLabel, feedDistanceField, feedMassDelField);
		layout.linkSize(SwingConstants.HORIZONTAL,feedDistanceLabel, feedMassDelLabel);
		layout.linkSize(SwingConstants.HORIZONTAL, feedDistanceField, feedMassDelField);

		
	}

	public static void loadBarnLocSizePanel(JPanel BarnLocationSize, IOclass ioclass) {

		GroupLayout layout = new GroupLayout(BarnLocationSize);
		BarnLocationSize.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		

		// Strings for the State dropdown
		String[] barnStates = { "-Choose State-","AL", "AK", "AZ", "AR", "CA", "CO",
				"CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI",
				"MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA",
				"RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA",
				"WA", "WV", "WI", "WY" };

		// Test strings for the County dropdown
		String[] barnCounties = { "-Choose County-"};//," Test County 1", " Test County 2 ", " Test County 3" };

		// The names for the inputs on the left side
		JLabel barnLocationState = new JLabel("  Location (State):  ");
		JLabel barnLocationCounty = new JLabel("  Location (County):  ");
		JLabel barnLength = new JLabel("  Barn Length (ft):  ");
		JLabel barnWidth = new JLabel("  Barn Width (ft):  ");
		JLabel barnHeight = new JLabel("  Barn Average Height (ft):  ");

		// the input areas themselves, on the right
		JComboBox<String> barnStateInputBox = new JComboBox<>(barnStates);
		 barnCountyInputBox = new JComboBox<>(barnCounties);
		JTextField barnLengthField = new JFormattedTextField();
		JTextField barnWidthField = new JFormattedTextField();
		JTextField barnHeightField = new JFormattedTextField();
		
		final IOclass tclass = ioclass;
		final JComboBox<String> tbox = barnStateInputBox;
		
    	barnStateInputBox.addItemListener(new ItemListener() {
      		public void itemStateChanged(ItemEvent e) {
      			String[] counties = tclass.getCountyList(tbox.getSelectedItem().toString(), "resources/counties.xml");
      			
      			barnCountyInputBox.removeAllItems();
        		barnCountyInputBox.insertItemAt("-Choose County-",0);
        		
        		for(int i = 0;i<counties.length;i++)
        			barnCountyInputBox.addItem(counties[i]);
        			
        		barnCountyInputBox.setSelectedIndex(0);
      		}
    	});

		// adding to reference class
		ioclass.addInput(barnStateInputBox);
		ioclass.addInput(barnCountyInputBox);
		ioclass.addInput(barnLengthField);
		ioclass.addInput(barnWidthField);
		ioclass.addInput(barnHeightField);

		// naming IO components with the same name they will have in the XML
		// file
		barnStateInputBox.setName("Barn_State");
		barnCountyInputBox.setName("Barn_State_County");
		barnLengthField.setName("Barn_Length");
		barnWidthField.setName("Barn_Width");
		barnHeightField.setName("Barn_Average_Height");

		// Settings and layouts
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(barnLocationState)
				.addComponent(barnLocationCounty)
				.addComponent(barnLength)
				.addComponent(barnWidth)
				.addComponent(barnHeight)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(barnStateInputBox)
				.addComponent(barnCountyInputBox)
				.addComponent(barnLengthField)
				.addComponent(barnWidthField)
				.addComponent(barnHeightField)
			)
			
		);



		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(barnLocationState)
				.addComponent(barnStateInputBox)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(barnLocationCounty)
				.addComponent(barnCountyInputBox)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(barnLength)
				.addComponent(barnLengthField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(barnWidth)
				.addComponent(barnWidthField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(barnHeight)
				.addComponent(barnHeightField)
			)
		);

		layout.linkSize(SwingConstants.VERTICAL, barnLocationState, barnLocationCounty, barnLength, barnWidth, barnHeight, barnStateInputBox, barnCountyInputBox, barnHeightField, barnWidthField, barnLengthField);
		layout.linkSize(SwingConstants.HORIZONTAL, barnLocationState, barnLocationCounty, barnLength, barnWidth, barnHeight);
		layout.linkSize(SwingConstants.HORIZONTAL, barnStateInputBox, barnCountyInputBox, barnHeightField, barnWidthField, barnLengthField);

		
	}

	public static void loadBarnHeatCoolPanel(JPanel BarnHeatCool, IOclass ioclass) {
	
		GroupLayout layout = new GroupLayout(BarnHeatCool);
		BarnHeatCool.setLayout(layout);
		//layout.setAutoCreateGaps(true);
		//layout.setAutoCreateContainerGaps(true);
		
	
		JLabel blankspace = new JLabel("");
		// String for Heating Fuel dropdown
		String[] HeatingFuelString = { "-Choose Fuel-"," Propane ", " Natural Gas " };

		// Labels for Side Wall Fans
		JLabel SideWallFans = new JLabel("Side Wall Fans");
		JLabel SideFanAmt = new JLabel("  Number of Fans:  ");
		JLabel SideFanThroughput = new JLabel("  Throughtput (cfm):  ");
		JLabel SideFanPower = new JLabel("  Power (W):  ");
		
		// Labels for Tunnel Fans
		JLabel TunnelFans = new JLabel("Tunnel Fans");
		JLabel TunnelFanAmt = new JLabel("  Number of Fans:  ");
		JLabel TunnelFanThroughput = new JLabel("  Throughtput (cfm):  ");
		JLabel TunnelFanPower = new JLabel("  Power (W):  ");
		
		// Label for Heating Fuel
		JLabel HeatingFuel = new JLabel("Heating Fuel:  ");
		
		// Labels for Cooling Fan info
		JLabel CoolFanUsed = new JLabel("Cooling Fans Used?  ");
		JLabel CellTotalArea = new JLabel("Total Area of Cells (ft^2):  ");
		
		// Label for Sprinkler Usage
		JLabel SprinklersUsed = new JLabel("Sprinklers Used?  ");

		JTextField SideFanAmtField = new JFormattedTextField();
		JTextField SideFanThroughField = new JFormattedTextField();
		JTextField SideFanPowerField = new JFormattedTextField();
		JTextField TunnelFanAmtField = new JFormattedTextField();
		JTextField TunnelFanThroughField = new JFormattedTextField();
		JTextField TunnelFanPowerField = new JFormattedTextField();
		JComboBox<String> HeatingFuelDrop = new JComboBox<>(HeatingFuelString);
		JCheckBox CoolFanCheck = new JCheckBox();
		JTextField CellAreaField = new JFormattedTextField();
		JCheckBox SprinklerCheck = new JCheckBox();

		ioclass.addInput(SideFanAmtField);
		ioclass.addInput(SideFanThroughField);
		ioclass.addInput(SideFanPowerField);
		ioclass.addInput(TunnelFanAmtField);
		ioclass.addInput(TunnelFanThroughField);
		ioclass.addInput(TunnelFanPowerField);
		ioclass.addInput(HeatingFuelDrop);
		ioclass.addInput(CoolFanCheck);
		ioclass.addInput(CellAreaField);
		ioclass.addInput(SprinklerCheck);

		SideFanAmtField.setName("Side_Fan_Amt");
		SideFanThroughField.setName("Side_Fan_Throughput");
		SideFanPowerField.setName("Side_Fan_Power");
		TunnelFanAmtField.setName("Tunnel_Fan_Amt");
		TunnelFanThroughField.setName("Tunnel_Fan_Throughput");
		TunnelFanPowerField.setName("Tunnel_Fan_Power");
		HeatingFuelDrop.setName("Heating_and_Fuel");
		CoolFanCheck.setName("Cooling_Fans_Used");
		CellAreaField.setName("Area_Of_Cells");
		SprinklerCheck.setName("Sprinklers_Used");




		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(SideWallFans)
				.addComponent(SideFanAmt)
				.addComponent(SideFanThroughput)
				.addComponent(SideFanPower)
				.addComponent(TunnelFans)
				.addComponent(TunnelFanAmt)
				.addComponent(TunnelFanThroughput)
				.addComponent(TunnelFanPower)
				.addComponent(HeatingFuel)
				.addComponent(CoolFanUsed)
				.addComponent(CellTotalArea)
				.addComponent(SprinklersUsed)
				.addComponent(blankspace)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(SideFanAmtField)
				.addComponent(SideFanThroughField)
				.addComponent(SideFanPowerField)
				.addComponent(TunnelFanAmtField)
				.addComponent(TunnelFanThroughField)
				.addComponent(TunnelFanPowerField)
				.addComponent(HeatingFuelDrop)
				.addComponent(CoolFanCheck)
				.addComponent(CellAreaField)
				.addComponent(SprinklerCheck)
			)
			
		);



		layout.setVerticalGroup(layout.createSequentialGroup()
			.addComponent(SideWallFans)
			.addGroup(layout.createParallelGroup()
				.addComponent(SideFanAmt)
				.addComponent(SideFanAmtField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(SideFanThroughput)
				.addComponent(SideFanThroughField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(SideFanPower)
				.addComponent(SideFanPowerField)
			)
			.addComponent(blankspace)
			.addComponent(TunnelFans)
			.addGroup(layout.createParallelGroup()
				.addComponent(TunnelFanAmt)
				.addComponent(TunnelFanAmtField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(TunnelFanThroughput)
				.addComponent(TunnelFanThroughField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(TunnelFanPower)
				.addComponent(TunnelFanPowerField)
			)
			.addComponent(blankspace)
			.addGroup(layout.createParallelGroup()
				.addComponent(HeatingFuel)
				.addComponent(HeatingFuelDrop)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(SprinklersUsed)
				.addComponent(SprinklerCheck)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(CoolFanUsed)
				.addComponent(CoolFanCheck)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(CellTotalArea)
				.addComponent(CellAreaField)
			)
		);

		layout.linkSize(SwingConstants.VERTICAL, blankspace, SideWallFans, SideFanAmt, SideFanThroughput, SideFanPower, TunnelFans, TunnelFanAmt, TunnelFanThroughput, TunnelFanPower, HeatingFuel, CoolFanUsed, CellTotalArea, SprinklersUsed,SideFanAmtField, SideFanThroughField, SideFanPowerField, TunnelFanAmtField, TunnelFanThroughField, TunnelFanPowerField, HeatingFuelDrop, CoolFanCheck, CellAreaField, SprinklerCheck);
		layout.linkSize(SwingConstants.HORIZONTAL, blankspace, SideWallFans, SideFanAmt, SideFanThroughput, SideFanPower, TunnelFans, TunnelFanAmt, TunnelFanThroughput, TunnelFanPower, HeatingFuel, CoolFanUsed, CellTotalArea, SprinklersUsed);
		layout.linkSize(SwingConstants.HORIZONTAL, SideFanAmtField, SideFanThroughField, SideFanPowerField, TunnelFanAmtField, TunnelFanThroughField, TunnelFanPowerField, HeatingFuelDrop, CoolFanCheck, CellAreaField, SprinklerCheck);

		
	}

	public static void loadBarnWater(JPanel BarnWater, IOclass ioclass) {

		GroupLayout layout = new GroupLayout(BarnWater);
		BarnWater.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);


		JLabel WellAmount = new JLabel("  Amount from Well (%):  ");
		JLabel PipedAmount = new JLabel("  Amount Piped In (%):  ");
		JLabel SurfaceWaterAmount = new JLabel(
				"  Amount from Surface Water (%):  ");
		JLabel WaterPumpPower = new JLabel(
				"  Power of Water Distribution Pump (HP):  ");
		JLabel MaxFlowrate = new JLabel(
				"  Max Flowrate of Water Distribution Pump (gpm):  ");

		// the input areas themselves, on the right
		JTextField WellAmountField = new JFormattedTextField();
		JTextField PipedAmoutField = new JFormattedTextField();
		JTextField SurfaceWaterField = new JFormattedTextField();
		JTextField WaterPumpField = new JFormattedTextField();
		JTextField FlowrateField = new JFormattedTextField();

		// adding to reference class
		ioclass.addInput(WellAmountField);
		ioclass.addInput(PipedAmoutField);
		ioclass.addInput(SurfaceWaterField);
		ioclass.addInput(WaterPumpField);
		ioclass.addInput(FlowrateField);

		// naming IO components with the same name they will have in the XML
		// file
		WellAmountField.setName("Well_Amt");
		PipedAmoutField.setName("Piped_In_Amt");
		SurfaceWaterField.setName("Surface_Water_Amt");
		WaterPumpField.setName("Water_Pump_Power");
		FlowrateField.setName("Flowrate");

		// Settings and layouts
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(WellAmount)
				.addComponent(PipedAmount)
				.addComponent(SurfaceWaterAmount)
				.addComponent(WaterPumpPower)
				.addComponent(MaxFlowrate)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(WellAmountField)
				.addComponent(PipedAmoutField)
				.addComponent(SurfaceWaterField)
				.addComponent(WaterPumpField)
				.addComponent(FlowrateField)
			)
			
		);



		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(WellAmount)
				.addComponent(WellAmountField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(PipedAmount)
				.addComponent(PipedAmoutField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(SurfaceWaterAmount)
				.addComponent(SurfaceWaterField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(WaterPumpPower)
				.addComponent(WaterPumpField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(MaxFlowrate)
				.addComponent(FlowrateField)
			)
		);

		WellAmountField.setColumns(5);
		layout.linkSize(SwingConstants.VERTICAL, WellAmount,PipedAmount,SurfaceWaterAmount,WaterPumpPower,MaxFlowrate, WellAmountField, PipedAmoutField,SurfaceWaterField, WaterPumpField, FlowrateField);
		layout.linkSize(SwingConstants.HORIZONTAL, WellAmount,PipedAmount,SurfaceWaterAmount,WaterPumpPower,MaxFlowrate);
		layout.linkSize(SwingConstants.HORIZONTAL, WellAmountField, PipedAmoutField,SurfaceWaterField, WaterPumpField, FlowrateField);

	}

	public static void loadBarnLighting(JPanel BarnLighting, IOclass ioclass) {


		GroupLayout layout = new GroupLayout(BarnLighting);
		BarnLighting.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);


		JLabel ConstantLight = new JLabel(
				"  Lights Left on Constantly (Total Watts):  ");
		JLabel PartialLight = new JLabel(
				"  Lights Left on Partially  (Total Watts):     ");
		JLabel TotalTime = new JLabel("  Total Time on Per Day (hours):               ");

		// the input areas themselves, on the right
		JTextField ConstantLightField = new JFormattedTextField();
		JTextField PartialLightField = new JFormattedTextField();
		JTextField TotalTimeField = new JFormattedTextField();

		// adding to reference class
		ioclass.addInput(ConstantLightField);
		ioclass.addInput(PartialLightField);
		ioclass.addInput(TotalTimeField);

		// naming IO components with the same name they will have in the XML
		// file
		ConstantLightField.setName("Constant_Light");
		PartialLightField.setName("Partial_Light");
		TotalTimeField.setName("Total_Time");

		// Settings and layouts
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(ConstantLight)
				.addComponent(PartialLight)
				.addComponent(TotalTime)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(ConstantLightField)
				.addComponent(PartialLightField)
				.addComponent(TotalTimeField)
			)
			
		);



		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(ConstantLight)
				.addComponent(ConstantLightField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(PartialLight)
				.addComponent(PartialLightField)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(TotalTime)
				.addComponent(TotalTimeField)
			)
		);

		ConstantLightField.setColumns(5);
		layout.linkSize(SwingConstants.VERTICAL, ConstantLight, PartialLight, TotalTime, ConstantLightField, PartialLightField, TotalTimeField);
		layout.linkSize(SwingConstants.HORIZONTAL, ConstantLight, PartialLight, TotalTime);
		layout.linkSize(SwingConstants.HORIZONTAL, ConstantLightField, PartialLightField, TotalTimeField);

	}

	public static void loadWastePanel(JPanel wasteData, IOclass ioclass) {
	
	
		GroupLayout layout = new GroupLayout(wasteData);
		wasteData.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);

	
		String[] LitterUseStrings = { "-Choose Litter Use-"," Fertilizer ", " Animal Feed ",
				" Fuel Source " };

		JLabel LitterUse = new JLabel("  End Use of Litter:  ");
		JLabel LitterCleanout = new JLabel(
				"  Number of Batches Between Litter Cleanouts:  ");

		// the input areas themselves, on the right
		JComboBox<String> LitterUseDrop = new JComboBox<>(LitterUseStrings);
		JTextField LitterCleanoutField = new JFormattedTextField();

		// adding to reference class
		ioclass.addInput(LitterUseDrop);
		ioclass.addInput(LitterCleanoutField);

		// naming IO components with the same name they will have in the XML
		// file
		LitterUseDrop.setName("Litter_Usage");
		LitterCleanoutField.setName("Litter_Cleanout");

		// Settings and layouts
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(LitterUse)
				.addComponent(LitterCleanout)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(LitterUseDrop)
				.addComponent(LitterCleanoutField)
			)
			
		);


		layout.setVerticalGroup(layout.createSequentialGroup()
			.addGroup(layout.createParallelGroup()
				.addComponent(LitterUse)
				.addComponent(LitterUseDrop)
			)
			.addGroup(layout.createParallelGroup()
				.addComponent(LitterCleanout)
				.addComponent(LitterCleanoutField)
			)
		);

		layout.linkSize(SwingConstants.VERTICAL, LitterUse, LitterCleanout, LitterUseDrop, LitterCleanoutField);
		layout.linkSize(SwingConstants.HORIZONTAL, LitterUse, LitterCleanout);
		layout.linkSize(SwingConstants.HORIZONTAL, LitterUseDrop, LitterCleanoutField);

	}

	public static void loadResultSummaryPanel(JPanel resultPanel, IOclass ioclass) {

		GroupLayout layout = new GroupLayout(resultPanel);
		resultPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// the buttons to create PDF and Excel
		javax.swing.JButton btnCreatePdf = new javax.swing.JButton();
		javax.swing.JButton btnCreateExc = new javax.swing.JButton();
		
		// labels of the buttons
		btnCreatePdf.setText("Create PDF Report");
		btnCreateExc.setText("Create Excel Report");
		
		// actions of the buttons
		btnCreatePdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//btnCreatePdfActionPerformed(evt);
            	JOptionPane.showMessageDialog(null, "Done later: create functionality to create PDF");
            }
        });
		btnCreateExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	//btnCreatePdfActionPerformed(evt);
            	JOptionPane.showMessageDialog(null, "Done later: create functionality to create Excel");
            }
        });
		
		// Settings and layouts
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(20, 20, 20)
	                .addComponent(btnCreatePdf)
	                .addGap(70, 70, 70)
	                .addComponent(btnCreateExc)
	                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
			layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGap(35, 35, 35)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnCreatePdf)
	                    .addComponent(btnCreateExc))
	                .addContainerGap(294, Short.MAX_VALUE))
	        );
	

	}

        //The creator of this next part of the code did not adhere to the previous format
        //that we used in all the other panels. A lot of statistics were left 'static'
        //so I can't get the XML to load into any slots.
        //It would probably be best if these output/result functions were redone completely to
        //match the previous panel functions in style and layout. Due to time constraints,
        //I have chosen to fix what I can in these functions rather than redoing the
        //functions entirely. However, I would advise future programmers to redo these
        //functions to match the above functions.
        
	public static void loadResultBroilerPanel(JPanel resultPanel, IOclass ioclass) {
		
		GroupLayout layout = new GroupLayout(resultPanel);
		resultPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// making the graph
		javax.swing.JInternalFrame graph = new javax.swing.JInternalFrame();
		graph.setTitle("The graph is inside");
        graph.setVisible(true);
        
        javax.swing.GroupLayout graphLayout = new javax.swing.GroupLayout(graph.getContentPane());
        graph.getContentPane().setLayout(graphLayout);
        graphLayout.setHorizontalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );
        graphLayout.setVerticalGroup(
            graphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );
        
        
        // the finalBodyWeight 
        JTextField Final_Weight = new JFormattedTextField();
        ioclass.addInput(Final_Weight);
        javax.swing.JLabel finalBodyWeight = new javax.swing.JLabel();
        finalBodyWeight.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        finalBodyWeight.setText("Final body Weight: 8.73 lbs");
        finalBodyWeight.setToolTipText("");
        finalBodyWeight.setAlignmentX(0.5F);
        finalBodyWeight.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        
        
        // report table
        javax.swing.JScrollPane reportTable = new javax.swing.JScrollPane();
        javax.swing.JTable jTable1 = new javax.swing.JTable();
                
        
        //For future reference, this table needs to be a table of variable size
        //This table is static and cannot grow in size, but I don't have the
        //necessary amount of time to fix this
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {"1", "42", "91.061", "10"},
                    {"2", "52", "91.061", "14"},
                    {"3", "66", "91.061", "15"}
                },
                new String [] {
                    "Day", "Initial body weight (g)", "MEI (kcal)", "Daily Gain (g)"
                }
            ) {
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
                };
                boolean[] canEdit = new boolean [] {
                    true, false, false, false
                };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            reportTable.setViewportView(jTable1);
            if (jTable1.getColumnModel().getColumnCount() > 0) {
                jTable1.getColumnModel().getColumn(0).setHeaderValue("Day");
                jTable1.getColumnModel().getColumn(1).setHeaderValue("Initial body weight (g)"); // NOI18N
                jTable1.getColumnModel().getColumn(2).setHeaderValue("MEI (kcal)");
                jTable1.getColumnModel().getColumn(3).setHeaderValue("Daily Gain (g)");
            }
	
         // Settings and layouts
            layout.setHorizontalGroup(
            		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 66, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(graph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(98, 98, 98))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(reportTable, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36))))
                .addGroup(layout.createSequentialGroup()
                    .addGap(201, 201, 201)
                    .addComponent(finalBodyWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
            		layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(graph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(finalBodyWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(35, 35, 35)
                    .addComponent(reportTable, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
	

	}

	public static void loadResultFeedPanel(JPanel resultPanel, IOclass ioclass) {
		
		GroupLayout layout = new GroupLayout(resultPanel);
		resultPanel.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		// top information
		javax.swing.JLabel lInformation = new javax.swing.JLabel();
		lInformation.setText("Average Total Feed Consumption: 2,954,238.00 lb/year");
        lInformation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));		
		
		// bottom information
		javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
	    javax.swing.JLabel jLabel2 = new javax.swing.JLabel();;	    
	    javax.swing.JLabel jLabel3 = new javax.swing.JLabel();;	    
	    javax.swing.JLabel jLabel4 = new javax.swing.JLabel();;
	    javax.swing.JLabel jLabel5 = new javax.swing.JLabel();;
	    jLabel1.setText("Food Delivery");
	    jLabel2.setText("Load Per Trip:   1,5000 lb wet");
	    jLabel3.setText("Distance to Feed Mill:  25.00 mi");
	    jLabel4.setText("Trips per Year:    24.00");
	    jLabel5.setText("Diesel Used:       15,345.00 gallons");
	    
	    // mid table
	    javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();;	    
	    javax.swing.JTable tFoodResult = new javax.swing.JTable();;
	
            //Not sure why this table was made differently from the previous table
	    TableModel dataModel = new DefaultTableModel(
                    new Object[][]{{"Alfalfa meal dehydrated, 17% protein","2323639"},{"Alfalfa meal dehydrated, 20% protein","478097"}}, 
                    new Object[]{"Feed Ingredient","Dry Basis"});
	    tFoodResult.setModel(dataModel);
	    
	    jScrollPane1.setViewportView(tFoodResult);
	    
	    // Settings and layouts
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(lInformation))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lInformation)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addContainerGap(108, Short.MAX_VALUE))
        );

	}

}
