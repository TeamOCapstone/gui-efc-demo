import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.glass.events.KeyEvent;

//
//
//Icons courtesy http://www.aha-soft.com/

public class PoultryDemo extends JFrame{

	//The top, all inclusive frame
	JFrame topFrame = new JFrame();
	
	public PoultryDemo() {
		
		// main frame
		topFrame.setSize(800, 600);
		topFrame.setTitle("Poultry Demo");
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //topFrame.pack();
	    topFrame.setLocationRelativeTo(null);
		
		//////////////////////////////////////////////////////////////////
	    //Content panels
	    
	    // home desktop pane
		JDesktopPane desktopPane = createDesktopPane();
		// home panel
		JPanel homePanel = createHomePanel();
		// bird panel
		JTabbedPane birdPanel = createBirdPanel();
		// feed panel
		JTabbedPane feedPanel = createFeedPanel();
		// barn panel
		JTabbedPane barnPanel = createBarnPanel();
		// waste panel
		JTabbedPane wastePanel = createWastePanel();		
		
		//add card panels to card layout
		desktopPane.add(homePanel, "home panel");
		desktopPane.add(birdPanel, "bird panel");
		desktopPane.add(barnPanel, "barn panel");
		desktopPane.add(feedPanel, "feed panel");
		desktopPane.add(wastePanel, "waste panel");
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
		topFrame.add(topToolBar, BorderLayout.NORTH);
		topFrame.add(desktopPane, BorderLayout.CENTER);
	    topFrame.setVisible(true);
			
	}//end PoultryDemo
	
	// Creation Methods

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

	private JToolBar createToolBar(JDesktopPane desktopPane,
			CardLayout cardLayout) {
		JToolBar topToolBar = new JToolBar();
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
				//
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
		return topToolBar;
	}

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
		menuItem = new JMenuItem("New Farm",KeyEvent.VK_N);
		mainMenuFile.add(menuItem);
		menuItem = new JMenuItem("Load Farm",KeyEvent.VK_L);
		mainMenuFile.add(menuItem);
		menuItem = new JMenuItem("Save Farm",KeyEvent.VK_S);
		mainMenuFile.add(menuItem);
		menuItem = new JMenuItem("Exit",KeyEvent.VK_X);
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev){
				System.exit(0);
			}
		});
		mainMenuFile.add(menuItem);
		topMenuBar.add(mainMenuFile);
	}

	
	private JTabbedPane createWastePanel() {
		JTabbedPane wastePanel = new JTabbedPane();
		wastePanel.setLayout(new BorderLayout());
		wastePanel.setBackground(java.awt.Color.BLUE);
		wastePanel.setVisible(false);
		return wastePanel;
	}

	private JTabbedPane createBarnPanel() {
		JTabbedPane barnPanel = new JTabbedPane();
		barnPanel.setLayout(new BorderLayout());
		barnPanel.setBackground(new Color(69,64,25)); //BROWN
		return barnPanel;
	}

	private JTabbedPane createFeedPanel() {
		JTabbedPane feedPanel = new JTabbedPane();
		feedPanel.setLayout(new BorderLayout());
		feedPanel.setBackground(java.awt.Color.GREEN);
		feedPanel.setVisible(false);
		return feedPanel;
	}

	private JPanel createHomePanel() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(java.awt.Color.WHITE);
		
		// home frame
		JInternalFrame homeFrame = new JInternalFrame("Home Frame", true, true, true, true);
		homeFrame.setSize(new Dimension(300,300));
		homeFrame.setLocation(100, 100);
		homeFrame.setVisible(true);
		mainPanel.add(homeFrame);
		
		//the WTF frame
		homeFrame = new JInternalFrame("WTF", true, true, true, true);
		mainPanel.add(homeFrame);

		return mainPanel;
	}

	private JDesktopPane createDesktopPane() {

		// card layout 'holds' the other panels and switches between them
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setLayout(new CardLayout());
		desktopPane.setName("top panel");
		return desktopPane;
	}
	
	public JTabbedPane createBirdPanel(){
		
		// bird panel
		JTabbedPane birdPanel = new JTabbedPane();
		JPanel birdCard1 = new JPanel();
		birdCard1.setLayout(new BorderLayout());
		birdCard1.setVisible(true);
		
		JPanel birdCard2 = new JPanel();
		birdCard2.setLayout(new BorderLayout());
		birdCard2.setVisible(true);
		
		JPanel birdCard3 = new JPanel();
		birdCard3.setLayout(new BorderLayout());
		birdCard3.setVisible(true);
		
		JPanel birdCard4 = new JPanel();
		birdCard4.setLayout(new BorderLayout());
		birdCard4.setVisible(true);
		
		birdPanel.add("Bird Card 1", birdCard1);
		birdPanel.add("Bird Card 2", birdCard2);
		birdPanel.add("Bird Card 3", birdCard3);
		birdPanel.add("Bird Card 4", birdCard4);
		
		return birdPanel;
		
	}//end bird panel

	public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        PoultryDemo demo = new PoultryDemo();

	}//end main

}
