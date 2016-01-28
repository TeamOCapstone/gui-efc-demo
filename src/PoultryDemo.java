import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.sun.glass.events.KeyEvent;

//
//
//

public class PoultryDemo extends JPanel{

	public PoultryDemo() {
		
		// main frame
		JFrame topFrame = new JFrame();
		topFrame.setSize(800, 600);
		topFrame.setTitle("Poultry Demo");
		topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//////////////////////////////////////////////////////////////////
		// top panel
		// card layout 'holds' the other panels and switches between them
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new CardLayout());
		topPanel.setName("top panel");
		topPanel.setBackground(java.awt.Color.BLACK);
		
		// main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(java.awt.Color.WHITE);
		
		
		// bird panel
		JPanel birdPanel = new JPanel();
		birdPanel.setLayout(new BorderLayout());
		birdPanel.setBackground(java.awt.Color.YELLOW);
		birdPanel.setVisible(false);
		
		// feed panel
		JPanel feedPanel = new JPanel();
		feedPanel.setLayout(new BorderLayout());
		feedPanel.setBackground(java.awt.Color.GREEN);
		feedPanel.setVisible(false);
		
		// barn panel
		JPanel barnPanel = new JPanel();
		barnPanel.setLayout(new BorderLayout());
		barnPanel.setBackground(new Color(69,64,25)); //BROWN
		
		// waste panel
		JPanel wastePanel = new JPanel();
		wastePanel.setLayout(new BorderLayout());
		wastePanel.setBackground(java.awt.Color.BLUE);
		wastePanel.setVisible(false);		
		
		//add card panels to card layout
		topPanel.add(mainPanel, "main panel");
		topPanel.add(birdPanel, "bird panel");
		topPanel.add(barnPanel, "barn panel");
		topPanel.add(feedPanel, "feed panel");
		topPanel.add(wastePanel, "waste panel");
		CardLayout cardLayout = (CardLayout) topPanel.getLayout();
		
		//////////////////////////////////////////////////////////////////
		// main menu bar
		JMenuBar topMenuBar = new JMenuBar();
		JMenuItem menuItem = new JMenuItem();
		
		// main menu bar --> FILE
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
		
		// main menu bar --> EDIT
		JMenu mainMenuEdit = new JMenu("Edit");
		mainMenuEdit.setMnemonic(KeyEvent.VK_E);
		mainMenuEdit.getAccessibleContext().setAccessibleDescription("Edit Menu");
		topMenuBar.add(mainMenuEdit);
		
		// main menu bar --> VIEW
		JMenu mainMenuView = new JMenu("View");
		mainMenuView.setMnemonic(KeyEvent.VK_V);
		mainMenuView.getAccessibleContext().setAccessibleDescription("View Menu");
		topMenuBar.add(mainMenuView);
		
		// main menu bar --> ABOUT
		JMenu mainMenuAbout = new JMenu("About");
		mainMenuAbout.setMnemonic(KeyEvent.VK_A);
		mainMenuAbout.getAccessibleContext().setAccessibleDescription("About Menu");
		topMenuBar.add(mainMenuAbout);
		
		//////////////////////////////////////////////////////////////////
		// main tool bar
		JToolBar topToolBar = new JToolBar();
		JButton tbButton = new JButton();
		
		// main tool bar --> BIRD BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/bird.png")));
		tbButton.setToolTipText("BirdToolTip");
		tbButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(topPanel, "bird panel");
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
				cardLayout.show(topPanel, "feed panel");
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
				cardLayout.show(topPanel, "barn panel");
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
				cardLayout.show(topPanel, "waste panel");
			}
		});
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		
		// main tool bar --> HELP BUTTON
		tbButton = new JButton(new ImageIcon(
				this.getClass().getResource("resources/help.png")));
		tbButton.setToolTipText("HelpToolTip");
		topToolBar.add(tbButton);
		topToolBar.addSeparator();
		

		

		//////////////////////////////////////////////////////////////////
		//Combine pieces
		
		topFrame.setJMenuBar(topMenuBar);
		topFrame.add(topToolBar, BorderLayout.NORTH);
		topFrame.add(topPanel, BorderLayout.CENTER);
		topFrame.setVisible(true);
			
	}

	public static void main(String[] args) {
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        PoultryDemo demo = new PoultryDemo();

	}

}
