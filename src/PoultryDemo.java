import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sun.glass.events.KeyEvent;


//
//
//Icons courtesy http://www.aha-soft.com/

public class PoultryDemo extends JFrame{

	//The top, all inclusive frame
	JFrame topFrame = new JFrame();
    //one class that holds references to all inputs (and future outputs) w/methods for IO
    IOclass ioclass = new IOclass();
    
    class MyCustomFilter extends javax.swing.filechooser.FileFilter {
        @Override
        public boolean accept(File file) {
            // Allow only directories, or files with ".txt" extension
            return file.isDirectory() || file.getAbsolutePath().endsWith(".xml");
        }
        @Override
        public String getDescription() {
            // This description will be displayed in the dialog
            return "XML Files (*.xml)";
        }
    }
	
	public PoultryDemo() {
		
		// main frame
		topFrame.setSize(600, 500);
		topFrame.setTitle("Poultry Demo");
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		topFrame.setLocationRelativeTo(null); //center on screen
		
		//////////////////////////////////////////////////////////////////
	    //Content panels
	    
	    // main desktop pane
		JDesktopPane desktopPane = createDesktopPane();
		// home panel
		JDesktopPane homePane = createHomePane();
		// bird panel
		JTabbedPane birdPanel = createBirdPanel();
		// feed panel
		JTabbedPane feedPanel = createFeedPanel();
		// barn panel
		JTabbedPane barnPanel = createBarnPanel();
		// waste panel
		JTabbedPane wastePanel = createWastePanel();
		// result panel
		JTabbedPane resultPanel = createResultPanel();
		
		//add card panels to card layout
		desktopPane.add(homePane, "home panel");
		desktopPane.add(birdPanel, "bird panel");
		desktopPane.add(barnPanel, "barn panel");
		desktopPane.add(feedPanel, "feed panel");
		desktopPane.add(wastePanel, "waste panel");
		desktopPane.add(resultPanel, "result panel");
		desktopPane.setBackground(getForeground());
		CardLayout cardLayout = (CardLayout) desktopPane.getLayout();
		
		//////////////////////////////////////////////////////////////////
		// main menu bar
		JMenuBar topMenuBar = createMenuBar();
		
		//////////////////////////////////////////////////////////////////
		// main tool bar
		JToolBar topToolBar = createToolBar(desktopPane, cardLayout);

		//////////////////////////////////////////////////////////////////
		//Combine pieces		
		topFrame.setJMenuBar(topMenuBar);
		topFrame.add(topToolBar, BorderLayout.WEST);
		topFrame.add(desktopPane, BorderLayout.CENTER);
	    topFrame.setVisible(true);
        
			
	}//end PoultryDemo
	
	/*                Creation Methods              */

	private JToolBar createToolBar(final JDesktopPane desktopPane, final CardLayout cardLayout) {
		JToolBar topToolBar = new JToolBar();
		topToolBar.setOrientation(SwingConstants.VERTICAL);
		JButton tbButton = new JButton();
		
		// main tool bar --> HOME BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/home.png")));
		tbButton.setToolTipText("HomeToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "home panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		// main tool bar --> BIRD BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/bird.png")));
		tbButton.setToolTipText("BirdToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "bird panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		// main tool bar --> FEED BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/feed.png")));
		tbButton.setToolTipText("FeedToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "feed panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		// main tool bar --> BARN BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/barn.png")));
		tbButton.setToolTipText("BarnTooltip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "barn panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();

		// main tool bar --> WASTE BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/waste.png")));
		tbButton.setToolTipText("WasteToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "waste panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		// main tool bar --> HELP BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/help.png")));
		tbButton.setToolTipText("HelpToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new help frame popup
				JFrame helpFrame = new JFrame();
				helpFrame.setSize(400, 300);
				helpFrame.setTitle("Help Frame");
				//pop up center of existing main frame
				helpFrame.setLocationRelativeTo(topFrame);
				helpFrame.setVisible(true);
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		
		
		// main tool bar --> Trigger BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/refresh.png")));
		tbButton.setToolTipText("TriggerToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new help frame popup
				JFrame TroubleFrame = new JFrame();
				TroubleFrame.setSize(400, 300);
				TroubleFrame.setTitle("Error Found Frame");
				//pop up center of existing main frame
				TroubleFrame.setLocationRelativeTo(topFrame);
				TroubleFrame.setVisible(true);
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		
		// main tool bar --> RESULT BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/run.png")));
		tbButton.setToolTipText("ResultToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(desktopPane, "result panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		
		return topToolBar;
	}

	/*           Create-Menu-Item Methods           */

	private JMenuBar createMenuBar() {
		JMenuBar topMenuBar = new JMenuBar();
		//JMenuItem menuItem = new JMenuItem();
		
		// main menu bar --> FILE
		createFileMenu(topMenuBar);
		
		// main menu bar --> EDIT
		createEditMenu(topMenuBar);
		
		// main menu bar --> VIEW
		createViewMenu(topMenuBar);
		
		// main menu bar --> ABOUT
		createAboutMenu(topMenuBar);
		return topMenuBar;
	}//end create menu bar
	
	private void createAboutMenu(JMenuBar topMenuBar) {
		JMenu mainMenuAbout = new JMenu("About");
		mainMenuAbout.setMnemonic(KeyEvent.VK_A);
		mainMenuAbout.getAccessibleContext().setAccessibleDescription("About Menu");
		topMenuBar.add(mainMenuAbout);
	}

	private void createViewMenu(JMenuBar topMenuBar) {
		JMenu mainMenuView = new JMenu("View");
		mainMenuView.setMnemonic(KeyEvent.VK_V);
		mainMenuView.getAccessibleContext().setAccessibleDescription("View Menu");
		topMenuBar.add(mainMenuView);
	}

	private void createEditMenu(JMenuBar topMenuBar) {
		JMenu mainMenuEdit = new JMenu("Edit");
		mainMenuEdit.setMnemonic(KeyEvent.VK_E);
		mainMenuEdit.getAccessibleContext().setAccessibleDescription("Edit Menu");
		topMenuBar.add(mainMenuEdit);
	}

	private void createFileMenu(JMenuBar topMenuBar) {
		JMenuItem menuItem;
		JMenu mainMenuFile = new JMenu("File");
		mainMenuFile.setMnemonic(KeyEvent.VK_F);
		mainMenuFile.getAccessibleContext().setAccessibleDescription("File Menu");
		
        menuItem = new JMenuItem("New Project",KeyEvent.VK_N);
        mainMenuFile.add(menuItem);
        
        menuItem = new JMenuItem("Load Project",KeyEvent.VK_L);
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){OpenActionPerformed(ev);}
        });
        mainMenuFile.add(menuItem);
        
        menuItem = new JMenuItem("Load and Run Project",KeyEvent.VK_L);
        mainMenuFile.add(menuItem);
        
        menuItem = new JMenuItem("Save Project",KeyEvent.VK_S);
        mainMenuFile.add(menuItem);
        
        menuItem = new JMenuItem("Save Project As..",KeyEvent.VK_S);
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ev){SaveActionPerformed(ev);}
        });
        mainMenuFile.add(menuItem);
		
        menuItem = new JMenuItem("Exit",KeyEvent.VK_X);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){System.exit(0);}
		});
		mainMenuFile.add(menuItem);
        
		topMenuBar.add(mainMenuFile);
	}
    
    //Action Events for menu selections
    
    
    
    private void OpenActionPerformed(java.awt.event.ActionEvent evt) {
        String startingDirectory = "../inputfiles";
        JFileChooser fileChooser = new javax.swing.JFileChooser(new File(startingDirectory));
        fileChooser.setFileFilter(new MyCustomFilter());
        
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ioclass.loadInputs(file);//This loads the file
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    
    
    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {
        String startingDirectory = "../inputfiles";
        JFileChooser fileChooser = new javax.swing.JFileChooser(new File(startingDirectory));
        fileChooser.setFileFilter(new MyCustomFilter());
        
        int returnVal = fileChooser.showSaveDialog(this);
        if (returnVal == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            ioclass.saveInputs(file);//This loads the file
        } else {
            System.out.println("File access cancelled by user.");
        }
    }
    
    private void TriggerActionPerformed(java.awt.event.ActionEvent evt){
    
    
    }
    

	/*           Create-Internal-Pane Methods             */
    private JTabbedPane createResultPanel(){
		JTabbedPane resultPanel = new JTabbedPane();
		
		FlowLayout birdFlowLayout = new FlowLayout(FlowLayout.LEFT);
		birdFlowLayout.setHgap(10);  //horizontal gap between input boxes
		birdFlowLayout.setVgap(30);  //vertical
		
		//Summary panels on the bird window
		JPanel resultSummary = new JPanel();
		resultSummary.setLayout(birdFlowLayout);
		resultSummary.setBackground(Color.WHITE);
		loadInputMethods.loadResultSummaryPanel(resultSummary, ioclass);
		resultSummary.setVisible(true);
		
		//Broiler Performance panels on the bird window
		JPanel resultBroiler = new JPanel();
		resultBroiler.setLayout(birdFlowLayout);
		resultBroiler.setBackground(Color.WHITE);
		loadInputMethods.loadResultBroilerPanel(resultBroiler, ioclass);
		resultBroiler.setVisible(true);
                
		resultPanel.add("Summary", resultSummary);
		resultPanel.add("Broiler Performance", resultBroiler);
		
		
        /*Leaving extra cards in code in case we need them.
		JPanel birdCard2 = new JPanel();
		birdCard2.setLayout(birdFlowLayout);
		birdCard2.setBackground(new Color(90, 0, 0));
		birdCard2.setVisible(true);
		
		JPanel birdCard3 = new JPanel();
		birdCard3.setLayout(birdFlowLayout);
		birdCard3.setBackground(new Color(64, 0, 0));
		birdCard3.setVisible(true);
		
		JPanel birdCard4 = new JPanel();
		birdCard4.setLayout(birdFlowLayout);
		birdCard4.setBackground(new Color(32, 0, 0));
		birdCard4.setVisible(true);
		
		birdPanel.add("Bird Card 2", birdCard2);
		birdPanel.add("Bird Card 3", birdCard3);
		birdPanel.add("Bird Card 4", birdCard4);*/
		
		return resultPanel;
	}//end create bird panel
    
	private JTabbedPane createWastePanel() {
		JTabbedPane wastePanel = new JTabbedPane();
		
		JPanel wasteData = new JPanel();
		wasteData.setLayout(new FlowLayout(FlowLayout.LEFT));
		wasteData.setVisible(true);
                loadInputMethods.loadWastePanel(wasteData, ioclass);
		wasteData.setBackground(Color.WHITE);
                
		wastePanel.add("Waste Data", wasteData);
		
                //Removing these extra cards, leaving them in case we need them                
                
		/*JPanel wasteCard2 = new JPanel();
		wasteCard2.setLayout(new FlowLayout(FlowLayout.LEFT));
		wasteCard2.setVisible(true);
		wasteCard2.setBackground(new Color(0,0,169));
		
		JPanel wasteCard3 = new JPanel();
		wasteCard3.setLayout(new FlowLayout(FlowLayout.LEFT));
		wasteCard3.setVisible(true);
		wasteCard3.setBackground(new Color(0,0,128));
		
		JPanel wasteCard4 = new JPanel();
		wasteCard4.setLayout(new FlowLayout(FlowLayout.LEFT));
		wasteCard4.setVisible(true);
		wasteCard4.setBackground(new Color(0,0,64));*/                

		/*wastePanel.add("Barn Card 2", wasteCard2);
		wastePanel.add("Barn Card 3", wasteCard3);
		wastePanel.add("Barn Card 4", wasteCard4);*/
	
		return wastePanel;
	}//end create waste pane
	
	private JTabbedPane createBarnPanel() {
		JTabbedPane barnPanel = new JTabbedPane();
		
		JPanel BarnLocationSize = new JPanel();
		BarnLocationSize.setLayout(new FlowLayout(FlowLayout.LEFT));
		BarnLocationSize.setVisible(true);
		loadInputMethods.loadBarnLocSizePanel(BarnLocationSize, ioclass);
		BarnLocationSize.setBackground(Color.WHITE); 

		JPanel BarnHeatCool = new JPanel();
		BarnHeatCool.setLayout(new FlowLayout(FlowLayout.LEFT));
		BarnHeatCool.setVisible(true);
                loadInputMethods.loadBarnHeatCoolPanel(BarnHeatCool, ioclass);
		BarnHeatCool.setBackground(Color.WHITE); 
		
		JPanel BarnWater = new JPanel();
		BarnWater.setLayout(new FlowLayout(FlowLayout.LEFT));
		BarnWater.setVisible(true);
                loadInputMethods.loadBarnWater(BarnWater, ioclass);
		BarnWater.setBackground(Color.WHITE); 
		
		JPanel BarnLighting = new JPanel();
		BarnLighting.setLayout(new FlowLayout(FlowLayout.LEFT));
		BarnLighting.setVisible(true);
                loadInputMethods.loadBarnLighting(BarnLighting, ioclass);
		BarnLighting.setBackground(Color.WHITE); 
		
		barnPanel.add("Location and Size", BarnLocationSize);
		barnPanel.add("Heating and Cooling", BarnHeatCool);
		barnPanel.add("Water", BarnWater);
		barnPanel.add("Lighting", BarnLighting);
	
		return barnPanel;
	}//end create barn pane
	
	private JTabbedPane createFeedPanel() {
		JTabbedPane feedPanel = new JTabbedPane();
		
		FlowLayout feedFlowLayout = new FlowLayout(FlowLayout.LEFT);
		feedFlowLayout.setHgap(10);  //horizontal gap between input boxes
		feedFlowLayout.setVgap(30);  //vertical
		
		//panels on the feed window
		JPanel feedIngredientsPanel = new JPanel();
		feedIngredientsPanel.setLayout(feedFlowLayout);
		loadInputMethods.loadFeedIngredPanel(feedIngredientsPanel, ioclass);
		feedIngredientsPanel.setVisible(true);
		feedIngredientsPanel.setBackground(Color.WHITE);
		
		JPanel feedShippingPanel = new JPanel();
		feedShippingPanel.setLayout(feedFlowLayout);
		loadInputMethods.loadFeedShippingPanel(feedShippingPanel, ioclass);
		feedShippingPanel.setVisible(true);
		feedShippingPanel.setBackground(Color.WHITE);
		
		feedPanel.add("Ingredients", feedIngredientsPanel);
		feedPanel.add("Shipping", feedShippingPanel);

		return feedPanel;
	}//end create feed pane
	
	public JTabbedPane createBirdPanel(){
		JTabbedPane birdPanel = new JTabbedPane();
		
		FlowLayout birdFlowLayout = new FlowLayout(FlowLayout.LEFT);
		birdFlowLayout.setHgap(10);  //horizontal gap between input boxes
		birdFlowLayout.setVgap(30);  //vertical
		
		//panels on the bird window
		JPanel birdDataPanel = new JPanel();
		birdDataPanel.setLayout(birdFlowLayout);
		birdDataPanel.setBackground(Color.WHITE);
		loadInputMethods.loadBirdDataPanel(birdDataPanel, ioclass);
		birdDataPanel.setVisible(true);
                
		birdPanel.add("Bird Data", birdDataPanel);
		
                /*Leaving extra cards in code in case we need them.
		JPanel birdCard2 = new JPanel();
		birdCard2.setLayout(birdFlowLayout);
		birdCard2.setBackground(new Color(90, 0, 0));
		birdCard2.setVisible(true);
		
		JPanel birdCard3 = new JPanel();
		birdCard3.setLayout(birdFlowLayout);
		birdCard3.setBackground(new Color(64, 0, 0));
		birdCard3.setVisible(true);
		
		JPanel birdCard4 = new JPanel();
		birdCard4.setLayout(birdFlowLayout);
		birdCard4.setBackground(new Color(32, 0, 0));
		birdCard4.setVisible(true);
		
		birdPanel.add("Bird Card 2", birdCard2);
		birdPanel.add("Bird Card 3", birdCard3);
		birdPanel.add("Bird Card 4", birdCard4);*/
		
		return birdPanel;
	}//end create bird panel
	
	private JDesktopPane createHomePane() {
		JDesktopPane mainPane = new JDesktopPane();
		mainPane.setBackground(java.awt.Color.LIGHT_GRAY);
		
		createHomeFrame(mainPane, "Home Frame 1", 100, 100);
		createHomeFrame(mainPane, "Home Frame 2", 75, 75);

		return mainPane;
	}//end create home pane

	//creates a frame in the home panel with title and location
	private void createHomeFrame(JDesktopPane mainPane, String title, int x, int y) {
		// home frame
		JInternalFrame homeFrame = new JInternalFrame(title, true, true, true, true);
		homeFrame.setSize(new Dimension(300,300));
		homeFrame.setLocation(x, y);
		homeFrame.setVisible(true);
		mainPane.add(homeFrame);
		
		//the WTF frame that makes home frame visible
		//homeFrame = new JInternalFrame("WTF", true, true, true, true);
		//mainPane.add(homeFrame);
	}
	
	private JDesktopPane createDesktopPane() {
		// card layout 'holds' the other panels and switches between them
		JDesktopPane topPane = new JDesktopPane();
		topPane.setLayout(new CardLayout());
		topPane.setName("top panel");
		return topPane;
	}//end create desktop pane

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		PoultryDemo demo = new PoultryDemo();

	}//end main

}//end poultry demo
