import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.Border;

public class loadInputMethods {

	static Border blackline = BorderFactory.createLineBorder(Color.black);

	public static void loadBirdDataPanel(JPanel birdPanel, IOclass ioclass) {

		String[] breedStrings = { " Cobb 500 ", " Cobb 700 ", " Ross 308 ",
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
		breedInputBox.setMaximumSize(breedInputBox.getPreferredSize());
		targetWeightField.setMaximumSize(breedInputBox.getPreferredSize());
		numBroilersField.setMaximumSize(breedInputBox.getPreferredSize());
		numFatalitiesField.setMaximumSize(breedInputBox.getPreferredSize());

		Box breedBox = Box.createHorizontalBox();
		breedBox.add(birdBreedLabel);
		breedBox.add(Box.createHorizontalStrut(80)); // hard coded spacing is
														// bad
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

		// the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		// top.setBorder(blackline);
		// top.add(Box.createVerticalStrut(30));
		top.add(breedBox);
		top.add(Box.createVerticalStrut(10));
		top.add(weightBox);
		top.add(Box.createVerticalStrut(10));
		top.add(broilersBox);
		top.add(Box.createVerticalStrut(10));
		top.add(fatalBox);
		top.add(Box.createVerticalStrut(30));

		// top.add(Box.createHorizontalStrut(300));

		birdPanel.add(top);
	}// END loadBirdDataPanel

	public static void loadFeedIngredPanel(JPanel feedIngredientsPanel,
			IOclass ioclass) {

		// labels on the left
		JLabel numPhasesLabel = new JLabel("  Number of Feeding Phases:  ");
		JLabel phaseNameLabel = new JLabel("  Phase Name:  ");
		JLabel numDaysPerPhaseLabel = new JLabel("  Number of Days in Phase:  ");

		// input areas on the right
		JTextField numPhasesField = new JTextField();
		JTextField phaseNameField = new JTextField();
		JTextField numDaysPerPhaseField = new JTextField();

		ioclass.addInput(numPhasesField);
		ioclass.addInput(phaseNameField);
		ioclass.addInput(numDaysPerPhaseField);

		// naming IO components with the same name they will have in the XML
		// file
		numPhasesField.setName("Number_Of_Phases");
		phaseNameField.setName("Phase_Name");
		numDaysPerPhaseField.setName("Days_Per_Phase");

		// Settings and layout
		numPhasesField.setColumns(5);

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

		feedIngredientsPanel.add(top);

	}// end loadFeedIngredPanel

	public static void loadFeedShippingPanel(JPanel feedShippingPanel,
			IOclass ioclass) {

		JButton ingredientButton = new JButton("Add New Ingredient");
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		// buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10,
		// 10));
		buttonPane.add(Box.createHorizontalGlue());

		JLabel feedDistanceLabel = new JLabel(
				"  Distance from Feed Mill: (miles)  ");
		JLabel feedMassDelLabel = new JLabel(
				"  Amount of Feed Delivered (lb)  ");

		// Inputs themselves
		JTextField feedDistanceField = new JTextField();
		JTextField feedMassDelField = new JTextField();
		JTextField ingredientsField = new JTextField("Enter Ingredient Name");
		JTextField percentIngedField = new JTextField("%");

		ioclass.addInput(feedDistanceField);
		ioclass.addInput(feedMassDelField);
		ioclass.addInput(ingredientsField);
		ioclass.addInput(percentIngedField);

		// naming IO components with the same name they will have in the XML
		// file
		feedDistanceField.setName("Feed_Distance");
		feedMassDelField.setName("Feed_Mass");
		ingredientsField.setName("Ingredients");
		percentIngedField.setName("Percentage_Sign");

		// Settings and layouts
		feedDistanceField.setColumns(5);

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

	}// end loadFeedShippingPanel

	public static void loadBarnLocSizePanel(JPanel BarnLocationSize,
			IOclass ioclass) {

		// Strings for the State dropdown
		String[] barnStates = { " AL ", " AK ", " AZ ", " AR ", " CA ", " CO ",
				" CT ", " DE ", " FL ", " GA ", " HI ", " ID ", " IL ", " IN ",
				" IA ", " KS ", " KY ", " LA ", " ME ", " MD ", " MA ", " MI ",
				" MN ", " MS ", " MO ", " MT ", " NE ", " NV ", " NH ", " NJ ",
				" NM ", " NY ", " NC ", " ND ", " OH ", " OK ", " OR ", " PA ",
				" RI ", " SC ", " SD ", " TN ", " TX ", " UT ", " VT ", " VA ",
				" WA ", " WV ", " WI ", " WY " };

		// Test strings for the County dropdown
		String[] barnCounties = { " Test County 1", " Test County 2 ",
				" Test County 3" };

		// The names for the inputs on the left side
		JLabel barnLocationState = new JLabel("  Location (State):  ");
		JLabel barnLocationCounty = new JLabel("  Location (County):  ");
		JLabel barnLength = new JLabel("  Barn Length (ft):  ");
		JLabel barnWidth = new JLabel("  Barn Width (ft):  ");
		JLabel barnHeight = new JLabel("  Barn Average Height (ft):  ");

		// the input areas themselves, on the right
		JComboBox<String> barnStateInputBox = new JComboBox<>(barnStates);
		JComboBox<String> barnCountyInputBox = new JComboBox<>(barnCounties);
		JTextField barnLengthField = new JFormattedTextField();
		JTextField barnWidthField = new JFormattedTextField();
		JTextField barnHeightField = new JFormattedTextField();

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
		barnStateInputBox.setMaximumSize(barnCountyInputBox.getPreferredSize());
		barnCountyInputBox
				.setMaximumSize(barnCountyInputBox.getPreferredSize());
		barnLengthField.setMaximumSize(barnCountyInputBox.getPreferredSize());
		barnWidthField.setMaximumSize(barnCountyInputBox.getPreferredSize());
		barnHeightField.setMaximumSize(barnCountyInputBox.getPreferredSize());

		Box StateBox = Box.createHorizontalBox();
		StateBox.add(barnLocationState);
		StateBox.add(Box.createHorizontalStrut(20));
		StateBox.add(barnStateInputBox);

		Box CountyBox = Box.createHorizontalBox();
		CountyBox.add(barnLocationCounty);
		CountyBox.add(Box.createHorizontalStrut(20));
		CountyBox.add(barnCountyInputBox);

		Box LengthBox = Box.createHorizontalBox();
		LengthBox.add(barnLength);
		LengthBox.add(Box.createHorizontalStrut(10));
		LengthBox.add(barnLengthField);

		Box WidthBox = Box.createHorizontalBox();
		WidthBox.add(barnWidth);
		WidthBox.add(Box.createHorizontalStrut(20));
		WidthBox.add(barnWidthField);

		Box HeightBox = Box.createHorizontalBox();
		HeightBox.add(barnHeight);
		HeightBox.add(Box.createHorizontalStrut(2));
		HeightBox.add(barnHeightField);

		// the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		top.add(StateBox);
		top.add(Box.createVerticalStrut(10));
		top.add(CountyBox);
		top.add(Box.createVerticalStrut(10));
		top.add(LengthBox);
		top.add(Box.createVerticalStrut(10));
		top.add(WidthBox);
		top.add(Box.createVerticalStrut(10));
		top.add(HeightBox);
		top.add(Box.createVerticalStrut(30));

		BarnLocationSize.add(top);
	}// END loadBirdDataPanel

	public static void loadBarnHeatCoolPanel(JPanel BarnHeatCool,
			IOclass ioclass) {
		// String for Heating Fuel dropdown
		String[] HeatingFuelString = { " Propane ", " Natural Gas " };

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
		JLabel CellTotalArea = new JLabel("  Total Area of Cells (ft^2):  ");
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

		SideFanAmtField.setMaximumSize(new Dimension(40,20));
		SideFanThroughField.setMaximumSize(new Dimension(40,20));
		SideFanPowerField.setMaximumSize(new Dimension(40,20));
		TunnelFanAmtField.setMaximumSize(new Dimension(40,20));
		TunnelFanThroughField
				.setMaximumSize(new Dimension(40,20));
		TunnelFanPowerField.setMaximumSize(new Dimension(40,20));
		HeatingFuelDrop.setMaximumSize(HeatingFuelDrop.getPreferredSize());
		CoolFanCheck.setMaximumSize(HeatingFuelDrop.getPreferredSize());
		CellAreaField.setMaximumSize(HeatingFuelDrop.getPreferredSize());
		SprinklerCheck.setMaximumSize(HeatingFuelDrop.getPreferredSize());

		Box SideFanTop = Box.createHorizontalBox();
		SideFanTop.add(SideWallFans);

		Box SideFanAmtBox = Box.createHorizontalBox();
		SideFanAmtBox.add(SideFanAmt);
		SideFanAmtBox.add(Box.createHorizontalStrut(5));
		SideFanAmtBox.add(SideFanAmtField);

		Box SideFanThroughBox = Box.createHorizontalBox();
		SideFanThroughBox.add(SideFanThroughput);
		SideFanThroughBox.add(Box.createHorizontalStrut(5));
		SideFanThroughBox.add(SideFanThroughField);

		Box SideFanPowerBox = Box.createHorizontalBox();
		SideFanPowerBox.add(SideFanPower);
		SideFanPowerBox.add(Box.createHorizontalStrut(5));
		SideFanPowerBox.add(SideFanPowerField);

		Box TunnelFanTop = Box.createHorizontalBox();
		TunnelFanTop.add(TunnelFans);

		Box TunnelFanAmtBox = Box.createHorizontalBox();
		TunnelFanAmtBox.add(TunnelFanAmt);
		TunnelFanAmtBox.add(Box.createHorizontalStrut(5));
		TunnelFanAmtBox.add(TunnelFanAmtField);

		Box TunnelFanThroughBox = Box.createHorizontalBox();
		TunnelFanThroughBox.add(TunnelFanThroughput);
		TunnelFanThroughBox.add(Box.createHorizontalStrut(5));
		TunnelFanThroughBox.add(TunnelFanThroughField);

		Box TunnelFanPowerBox = Box.createHorizontalBox();
		TunnelFanPowerBox.add(TunnelFanPower);
		TunnelFanPowerBox.add(Box.createHorizontalStrut(5));
		TunnelFanPowerBox.add(TunnelFanPowerField);

		Box HeatFuelBox = Box.createHorizontalBox();
		HeatFuelBox.add(HeatingFuel);
		HeatFuelBox.add(Box.createHorizontalStrut(5));
		HeatFuelBox.add(HeatingFuelDrop);

		Box CoolFansBox = Box.createHorizontalBox();
		CoolFansBox.add(CoolFanUsed);
		CoolFansBox.add(Box.createHorizontalStrut(5));
		CoolFansBox.add(CoolFanCheck);

		Box CellAreaBox = Box.createHorizontalBox();
		CellAreaBox.add(CellTotalArea);
		CellAreaBox.add(Box.createHorizontalStrut(5));
		CellAreaBox.add(CellAreaField);

		Box SprinkerBox = Box.createHorizontalBox();
		SprinkerBox.add(SprinklersUsed);
		SprinkerBox.add(Box.createHorizontalStrut(5));
		SprinkerBox.add(SprinklerCheck);

		// the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		// top.setBorder(blackline);
		// top.add(Box.createVerticalStrut(30));
		top.add(SideFanTop);
		top.add(Box.createVerticalStrut(10));
		top.add(SideFanAmtBox);
		top.add(Box.createVerticalStrut(10));
		top.add(SideFanThroughBox);
		top.add(Box.createVerticalStrut(10));
		top.add(SideFanPowerBox);
		top.add(Box.createVerticalStrut(10));
		top.add(TunnelFanTop);
		top.add(Box.createVerticalStrut(10));
		top.add(TunnelFanAmtBox);
		top.add(Box.createVerticalStrut(10));
		top.add(TunnelFanThroughBox);
		top.add(Box.createVerticalStrut(10));
		top.add(TunnelFanPowerBox);
		top.add(Box.createVerticalStrut(10));
		top.add(HeatFuelBox);
		top.add(Box.createVerticalStrut(10));
		top.add(CoolFansBox);
		top.add(Box.createVerticalStrut(10));
		top.add(CellAreaBox);
		top.add(Box.createVerticalStrut(10));
		top.add(SprinkerBox);
		top.add(Box.createVerticalStrut(10));
		// top.add(Box.createHorizontalStrut(300));

		BarnHeatCool.add(top);
	}

	public static void loadBarnWater(JPanel BarnWater, IOclass ioclass) {

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
		WellAmountField.setColumns(10);
		WellAmountField.setMaximumSize(new Dimension(50, 50));
		PipedAmoutField.setMaximumSize(new Dimension(55, 50));
		SurfaceWaterField.setMaximumSize(new Dimension(55, 50));
		WaterPumpField.setMaximumSize(new Dimension(50,50));
		FlowrateField.setMaximumSize(new Dimension(55, 50));

		Box labelsBox = Box.createVerticalBox();
		labelsBox.add(WellAmount);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(PipedAmount);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(SurfaceWaterAmount);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(WaterPumpPower);
		labelsBox.add(Box.createVerticalStrut(5));
		labelsBox.add(MaxFlowrate);
		
		Box fieldsBox = Box.createVerticalBox();
		fieldsBox.add(WellAmountField);
		fieldsBox.add(PipedAmoutField);
		fieldsBox.add(SurfaceWaterField);
		fieldsBox.add(WaterPumpField);
		fieldsBox.add(FlowrateField);

		Box top = Box.createHorizontalBox();
		top.add(labelsBox);
		top.add(fieldsBox);
		
		BarnWater.add(top);
	}

	public static void loadBarnLighting(JPanel BarnLighting, IOclass ioclass) {

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
		ConstantLightField.setMaximumSize(new Dimension(50,50));
		PartialLightField.setMaximumSize(new Dimension(50,50));
		TotalTimeField.setMaximumSize(new Dimension(50,50));

		Box ConstantLightBox = Box.createHorizontalBox();
		ConstantLightBox.add(ConstantLight);
		ConstantLightBox.add(ConstantLightField);

		Box PartialLightBox = Box.createHorizontalBox();
		PartialLightBox.add(PartialLight);
		PartialLightBox.add(PartialLightField);

		Box TotalTimeBox = Box.createHorizontalBox();
		TotalTimeBox.add(TotalTime);
		TotalTimeBox.add(TotalTimeField);

		// the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		top.add(Box.createHorizontalStrut(350));
		top.add(ConstantLightBox);
		top.add(PartialLightBox);
		top.add(TotalTimeBox);

		BarnLighting.add(top);
	}

	public static void loadWastePanel(JPanel wasteData, IOclass ioclass) {
		String[] LitterUseStrings = { " Fertilizer ", " Animal Feed ",
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
		LitterUseDrop.setMaximumSize(LitterUseDrop.getPreferredSize());
		LitterCleanoutField.setMaximumSize(new Dimension(50,50));

		Box LitterUseBox = Box.createHorizontalBox();
		LitterUseBox.add(LitterUse);
		LitterUseBox.add(Box.createHorizontalStrut(1));
		LitterUseBox.add(LitterUseDrop);

		Box LitterCleanoutBox = Box.createHorizontalBox();
		LitterCleanoutBox.add(LitterCleanout);
		LitterCleanoutBox.add(Box.createHorizontalStrut(1));
		LitterCleanoutBox.add(LitterCleanoutField);

		// the top vertical box to align the other boxes
		Box top = Box.createVerticalBox();
		top.add(Box.createHorizontalStrut(350));
		top.add(LitterUseBox);
		top.add(Box.createVerticalStrut(10));
		top.add(LitterCleanoutBox);
		top.add(Box.createVerticalStrut(10));

		wasteData.add(top);
	}

}
