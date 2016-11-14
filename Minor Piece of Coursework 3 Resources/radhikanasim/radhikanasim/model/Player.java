package radhikanasim.model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * This class is the main model of the different players that will show on the pitch in the view class
 * in this class it extends JPanel so that this can become like a placeholder for the information of the
 * different players, specialisation was used to create child classes from this class to determine player
 * types such as Striker
 * 
 * @author Radhika
 * @author Nasim
 *
 */
public class Player extends JPanel{

	private static final long serialVersionUID = -7012049176785008960L;
	private String name;
	int iD;
	private String imagePath;
	JButton jbAdd;
	JTextField jtName;
	public ImageIcon playerImage;
	
	/**
	 * This is the constructor of player, so when a Player is called it will create certain fields
	 * and will properly construct the Player Placeholder as an a panel.
	 * 
	 * @param playerName an String field
	 * @param ID an Integer field
	 */
	
	public Player(String playerName,int ID ){
		super();
		name = playerName;
		iD = ID;
		imagePath = "None";
		setLayout(new BorderLayout ());
		addWidgets();
		
	}
	
	/**
	 * A method to add the different components to the panel, this method is called in the constructor so
	 * straight way when the player type object will be created it will instantly be created.
	 */
	private void addWidgets(){
		jbAdd = new JButton("+");
		jtName = new JTextField();
		jtName.setText(name);
		add(jtName, BorderLayout.SOUTH);
		add(jbAdd,BorderLayout.NORTH);
		jbAdd.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton open = new JButton();
		        JFileChooser imageLocater = new JFileChooser();
		        imageLocater.setCurrentDirectory(new java.io.File("src\\squad"));
		        imageLocater.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		        if (imageLocater.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
		        	imagePath = imageLocater.getSelectedFile().getAbsolutePath();
	
		        }
		        File file = new File(imagePath);
	        	String fileName = file.getName();
	        	fileName = fileName.substring(0,fileName.length()-4);
	        	fileName = fileName.substring(0,1).toUpperCase()+fileName.substring(1);
		        name = fileName;
		        removeAll();
		        playerImage = new ImageIcon(imagePath);
		        if(imagePath.equals("None")){
					add(jbAdd, BorderLayout.NORTH);
				} else {
					playerImage = new ImageIcon(imagePath);
					add(new JLabel(playerImage),BorderLayout.NORTH);
				}
		        jtName.setText(name);
				add(jtName, BorderLayout.SOUTH);
				revalidate();
				repaint();
			}
			
		});
	}
	
	/**
	 * A method to set the name of the Player object
	 */
	public void setName(String playerName){
		name = playerName;
	}
	/**
	 * A method to the set the path the Image using the String file path
	 * @param selectedImagePath an String field
	 */
	public void setImagePath(String selectedImagePath){
		imagePath = selectedImagePath;
	}
	/**
	 * A return method to return the name of the Player Object
	 * @return a String 
	 */
	public String getName(){
		return name;
	}
	/**
	 * A return method that will return the integer ID of the player object
	 * @return an Integer
	 */
	public int getID(){
		return iD;
	}
	/**
	 * A return method that will return the String of the image file path 
	 * @return a String
	 */
	public String getImagePath(){
		return imagePath;
	}
	/**
	 * A return method that will return a JLabel object that will be later used in the controller class
	 * @return a JLabel object
	 */
	public JLabel getImage(){
		playerImage = new ImageIcon(imagePath);
		return new JLabel(playerImage);
	}
	/**
	 * A return method that will return a JTextField object that will later be used in the controller class
	 * @return a JTextField object
	 */
	public JTextField getTextField(){
		return jtName;
	}
	/**
	 * A to-String method that will return the object as a string format, kept for debugging purposes
	 */
	public String toString(){
		return name + " " + iD + " " + imagePath;
	}
	
}
