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
    
    //Variable to keep track of the current panel - Used for Help Window
    int panelCount = 0;
    
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
		topFrame.setSize(750, 600);
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
                                panelCount = 0;
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
                                panelCount = 1;
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
                                panelCount = 2;
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
                                panelCount = 3;
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
                                panelCount = 4;
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
                            //A large amount of code is thrown here for the Help Frame
                            //Not sure how to pull this down into it's own chunk of code
                            
                            //new help frame popup
                            JFrame helpFrame = new JFrame();
                            helpFrame.setLayout(new BorderLayout());
                            JPanel helpPanel = new JPanel();
                            
                            JButton nextButton = new JButton("Next");
                            nextButton.setToolTipText("Move to the next panel");
                            nextButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e) {
                                    if (panelCount < 5) //count goes up to 5 because I include a count for the output
                                        panelCount++;
                                    if (panelCount == 0)
                                        cardLayout.show(desktopPane,"home panel");
                                    if (panelCount == 1)
                                        cardLayout.show(desktopPane,"bird panel");
                                    if (panelCount == 2)
                                        cardLayout.show(desktopPane,"feed panel");
                                    if (panelCount == 3)
                                        cardLayout.show(desktopPane,"barn panel");
                                    if (panelCount == 4)
                                        cardLayout.show(desktopPane,"waste panel");
                                }
                            });
                            
                            JButton previousButton = new JButton("Previous");
                            previousButton.setToolTipText("Move to the previous panel");
                            previousButton.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e) {
                                    if (panelCount > 0)
                                        panelCount--;
                                    if (panelCount == 0)
                                        cardLayout.show(desktopPane,"home panel");
                                    if (panelCount == 1)
                                        cardLayout.show(desktopPane,"bird panel");
                                    if (panelCount == 2)
                                        cardLayout.show(desktopPane,"feed panel");
                                    if (panelCount == 3)
                                        cardLayout.show(desktopPane,"barn panel");
                                    if (panelCount == 4)
                                        cardLayout.show(desktopPane,"waste panel");
                                }
                            });
                                
				helpFrame.setSize(400, 300);
				helpFrame.setTitle("Help Frame");
				//pop up center of existing main frame
				helpFrame.setLocationRelativeTo(topFrame);
				helpFrame.setVisible(true);
                                
                                previousButton.setBounds(10,10,0,80);
                                nextButton.setBounds(10,10,20,80);
                                helpPanel.add(previousButton);
                                helpPanel.add(nextButton);
                                helpFrame.add(helpPanel);
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
		//The result button currently calls the results panel as well as
		//calling the main function in Calculator_Call.java,
		//which executes a dummy c++ program
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/run.png")));
		tbButton.setToolTipText("ResultToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					//dummy cpp calculator call
					Calculator_Call.main(null);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
		
		//FeedConsumption panels on the bird window
		JPanel resultFeed = new JPanel();
		resultFeed.setLayout(birdFlowLayout);
		resultFeed.setBackground(Color.WHITE);
		loadInputMethods.loadResultFeedPanel(resultFeed, ioclass);
		resultFeed.setVisible(true);
                
		resultPanel.add("Summary", resultSummary);
		resultPanel.add("Broiler Performance", resultBroiler);
		resultPanel.add("Feed Consumption", resultFeed);
		
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
