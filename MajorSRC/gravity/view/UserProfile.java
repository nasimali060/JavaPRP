package gravity.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserProfile extends JDialog {

	JTextField inputName;
	JButton submitButton;
	JPasswordField passwordField;
	private ActionListener controller;
	private TreeMap<String, String> usersFavourites;

	public UserProfile() {

		setTitle("Login");
		setSize(300,250);
		setLayout(new GridLayout(3,2));
		addWidgets();
		//usersFavourites = new TreeMap<String, String>();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	public void addWidgets() {

		JLabel name = new JLabel("Enter Name: ");
		name.setFont(new Font("Courier New", Font.ITALIC, 14));
		this.add(name);
		
		inputName = new JTextField();
		inputName.setPreferredSize(new Dimension(30,30));
		this.add(inputName);
		
		JLabel password = new JLabel("Enter Password: ");
		password.setFont(new Font("Courier New", Font.ITALIC, 14));
		this.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(30,30));
		this.add(passwordField);

		submitButton = new JButton("Submit");
		this.add(submitButton);
	}
	
	public JTextField getTextField() {
		return inputName;
	}
	
	public String getInput() {
		return inputName.getText();
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword() {
		return passwordField.getText();
	}
	
	public void addController(ActionListener controller) {
		this.controller = controller;
		submitButton.addActionListener(controller);
	}

}