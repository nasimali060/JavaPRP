package gravity.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import api.jaws.Jaws;
import api.jaws.Shark;
import gravity.controller.Controller;


public class StartFrame extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel jpFrame;
	private JButton jbSearch;
	private JButton jbFavourites;
	private Jaws jaws;
	private ActionListener controller;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem login;
	
	public StartFrame(Jaws jaw) {
		
		super("Amnity Police");
		
		this.jaws = jaws;
		
		setLayout(new BorderLayout());
		
		setSize(500, 500);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWidget();
		
		createMenuBar();
		
		pack();
	
	}
	
	private void addWidget() {
		
		jpFrame = new JPanel(new BorderLayout());
		
		jbSearch = new JButton("Search");
		
		jbFavourites = new JButton("Favourites");
		
		JPanel jpSouth = new JPanel(new GridLayout(2, 1));	
		
		jpFrame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		jpFrame.add(jpSouth, BorderLayout.SOUTH);	
		
		jpFrame.add(new JLabel(new ImageIcon("src/Logo.png")), BorderLayout.CENTER);
		
		jpSouth.add(jbSearch);
		
		jpSouth.add(jbFavourites);
		
		add(jpFrame);
	}
	
	private void createMenuBar(){
		
		menuBar = new JMenuBar();
		
        menu = new JMenu("User Profile");
        
        login = new JMenuItem("Login");
        
        menu.add(login);
        
        menuBar.add(menu);
        
        this.setJMenuBar(menuBar);
	} 
	
	
	public void addController(ActionListener controller){
		
		this.controller = controller;
		
		jbSearch.addActionListener(controller);
		
		jbFavourites.addActionListener(controller);
		
		login.addActionListener(controller);
		
	}
	
	public JButton getFavouritesButton(){
		
		return jbFavourites;
		
	}
	
}
