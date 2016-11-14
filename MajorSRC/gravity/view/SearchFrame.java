package gravity.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import api.jaws.Jaws;
import api.jaws.Shark;


public class SearchFrame extends JFrame{

	private static final long serialVersionUID = 2475261946584528309L;
	private Jaws jaws;
	private JPanel jpFrame;
	private JPanel jpRightPanel;
	private JButton jbSearchButton;
	private TreeMap<String, Shark> finalSearchResults;
	private List<String> followedSharks;
	private JComboBox<String> jcbGender;
	private JComboBox<String> jcbTrackingRange;
	private JComboBox<String> jcbStageOfLife;
	private JComboBox<String> jcbTagLocation;
	private ActionListener controller;

	public SearchFrame(Jaws jaws) throws IOException {
		
		super("Search");
		this.jaws = jaws;
		jpFrame = new JPanel(new BorderLayout());
		jbSearchButton  = new JButton("Search");
		followedSharks = new ArrayList<String>();
		jpFrame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		setSize(900, 750);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		add(jpFrame, BorderLayout.CENTER);
		
		createLeftPanel();
		createCopyright();
		
		pack();
		
	}
	
	private void createLeftPanel() {

		JPanel jpLeftPanelGap = new JPanel();
		JPanel jpMainLeftPanel = new JPanel(new BorderLayout());
		JPanel titlePanel = new JPanel(new GridLayout(2, 1));
		JLabel jlTitle = new JLabel("Shark Tracker");
		JPanel jpFilter = new JPanel(new GridLayout(14, 1));
		String[] trackingRanges = { "Past 24 Hours", "Past Week", "Past Month" };
		String[] sharkGenders = { "All", "Male", "Female" };
		String[] sharkStageOfLife = { "All", "Mature", "Immature", "Undetermined" };
		ArrayList<String> sharkTagLocationsList = jaws.getTagLocations();
		String[] sharkTagLocations = new String[sharkTagLocationsList.size()];
		sharkTagLocations = sharkTagLocationsList.toArray(sharkTagLocations);

		jcbGender = new JComboBox<String>(sharkGenders);
		jcbTrackingRange = new JComboBox<String>(trackingRanges);
		jcbStageOfLife = new JComboBox<String>(sharkStageOfLife);
		jcbTagLocation = new JComboBox<String>(sharkTagLocations);
		jcbTagLocation.addItem("All");
		jcbTagLocation.setSelectedItem("All");
		jbSearchButton = new JButton("Search");
		jpRightPanel = new JPanel(new BorderLayout());
	
		jlTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		jpMainLeftPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.add(jlTitle);
		jpMainLeftPanel.add(jpFilter, BorderLayout.CENTER);
		
		jpFilter.add(new JLabel("Tracking Range"));
		jpFilter.add(jcbTrackingRange);
		
		jpFilter.add(new JSeparator(JSeparator.HORIZONTAL));
		jpFilter.add(new JLabel("Gender"));
		jpFilter.add(jcbGender);
		
		jpFilter.add(new JSeparator(JSeparator.HORIZONTAL));
		jpFilter.add(new JLabel("Stage Of Life"));
		jpFilter.add(jcbStageOfLife);
		
		jpFilter.add(new JSeparator(JSeparator.HORIZONTAL));
		jpFilter.add(new JLabel("Tag Location"));
		jpFilter.add(jcbTagLocation);
		
		jpFilter.add(new JSeparator(JSeparator.HORIZONTAL));
		jpFilter.add(jbSearchButton);

		jpMainLeftPanel.add(new JLabel(new ImageIcon("src/Logo.png")), BorderLayout.SOUTH);
		jpMainLeftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		jpLeftPanelGap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		jpLeftPanelGap.add(jpMainLeftPanel);
		
		jpRightPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		jpFrame.add(jpLeftPanelGap, BorderLayout.WEST);
		jpFrame.add(jpRightPanel, BorderLayout.CENTER);

	}
	
	private void createCopyright() {
		JLabel jlCopyRight = new JLabel(jaws.getAcknowledgement());
		add(jlCopyRight, BorderLayout.SOUTH);
	}
	
	public void createRightPanel() {
		
		JPanel jpFinalResultsPanel = new JPanel();
		jpFinalResultsPanel.removeAll();
		jpFinalResultsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		if (finalSearchResults.isEmpty()){
			
			jpFinalResultsPanel.setLayout(new BorderLayout());
			JLabel jlnoResults = new JLabel("NO RESULTS FOUND!");
			jlnoResults.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
			jlnoResults.setForeground(Color.BLACK);
			jpFinalResultsPanel.add(jlnoResults, BorderLayout.WEST);
		
		}
		else{
			
			jpFinalResultsPanel.setLayout(new GridLayout(0, 1));
			for (String sharkDate: finalSearchResults.keySet()) {
				
				String currentSharkDate = sharkDate;
				Shark currentShark = finalSearchResults.get(sharkDate);
				jpFinalResultsPanel.add(createSharkPanel(currentShark, currentSharkDate));
				
			}
		
		jpFinalResultsPanel.revalidate();
		jpFinalResultsPanel.repaint();
		jpRightPanel.add(new JScrollPane(jpFinalResultsPanel, 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		
		}
	}
	
	private JPanel createSharkPanel(Shark shark, String sharkDate){
		
		JPanel jpMainRightPanel = new JPanel(new BorderLayout());
		JPanel jpSharkDetails = new JPanel(new GridLayout(6, 2));
		JPanel jpSharkDescription = new JPanel(new GridLayout(2,1));

		jpSharkDetails.add(new JLabel("Name: "));
		jpSharkDetails.add(new JLabel(shark.getName()));
		jpSharkDetails.add(new JLabel("Gender: "));
		jpSharkDetails.add(new JLabel(shark.getGender()));
		jpSharkDetails.add(new JLabel("Stage of Life: "));
		jpSharkDetails.add(new JLabel(shark.getStageOfLife()));
		jpSharkDetails.add(new JLabel("Species: "));
		jpSharkDetails.add(new JLabel(shark.getSpecies()));
		jpSharkDetails.add(new JLabel("Length: "));
		jpSharkDetails.add(new JLabel(shark.getLength()));
		jpSharkDetails.add(new JLabel("Weight: "));
		jpSharkDetails.add(new JLabel(shark.getWeight()));

		String description = shark.getDescription();
		StringBuilder descriptionBuilder = new StringBuilder(description.length());
		descriptionBuilder.append("<html>" + description + "<html>");
		
		JLabel jlDescriptionContents = new JLabel(descriptionBuilder.toString());
		
		jpSharkDescription.add(new JLabel("Description: "));
		jpSharkDescription.add(jlDescriptionContents);
		jpMainRightPanel.add(jpSharkDetails,BorderLayout.NORTH);
		jpMainRightPanel.add(jpSharkDescription,BorderLayout.CENTER);
		
		JPanel jpPingAndFav = new JPanel(new BorderLayout());
		JButton jbFavouritesButton = new JButton("Follow");
		
		jpPingAndFav.add(new JLabel("Last Ping : " + sharkDate), BorderLayout.WEST);
		jpPingAndFav.add(jbFavouritesButton, BorderLayout.EAST);
		jpMainRightPanel.add(jpPingAndFav, BorderLayout.SOUTH);
		
		jpMainRightPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		jpMainRightPanel.setPreferredSize(new Dimension(470, 500));
		
		if (followedSharks.contains(shark.getName())){
			jbFavouritesButton.setText("Following");
		}
		
		jbFavouritesButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getActionCommand().equals("Follow")){
					
					jbFavouritesButton.setText("Following");
					followedSharks.add(shark.getName());
					addLineToFile("src/FavouriteSharks.txt", shark.getName());	
				
				}
				else if(event.getActionCommand().equals("Following")){
					
					jbFavouritesButton.setText("Follow");
					followedSharks.remove(shark.getName());
					removeLineFromFile("src/FavouriteSharks.txt", shark.getName());
					
				}
			}
		});
		
		return jpMainRightPanel;
		
	}
	
	public void addController(ActionListener controller){
		this.controller = controller;
		jbSearchButton.addActionListener(controller);
	}
	
	public JPanel getRightPanel(){
		return jpRightPanel;
	}
	
	public String getTrackingRangeInput(){
		return jcbTrackingRange.getSelectedItem().toString();
	}
	
	public String getGenderInput(){
		return jcbGender.getSelectedItem().toString();
	}
	
	public String getStageOfLifeInput(){
		return jcbStageOfLife.getSelectedItem().toString();
	}
	
	public String getTagLocationInput(){
		return jcbTagLocation.getSelectedItem().toString();
	}
	
	public void setSearchResults(TreeMap<String, Shark> searchResults){
		finalSearchResults = searchResults;
	}
	
	public void setFollowedSharks(ArrayList<String> followedSharks){
		this.followedSharks = (List<String>) followedSharks;
	}
	
	public void removeLineFromFile(String file, String lineToRemove) {
		 
		try {
			 
		      File inFile = new File(file);
		       
		      //Construct the new file that will later be renamed to the original filename. 
		      File tempFile = new File("Temp.tmp");
		      
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
		      
		      String line = null;
		 
		      //Read from the original file and write to the new 
		      //unless content matches data to be removed.
		      while ((line = br.readLine()) != null) {
		        
		        if (!line.equals(lineToRemove)) {
		 
		          pw.write(line + "\n");
		          pw.flush();
		        }
		      }
		      pw.close();
		      br.close();
		      
		      //Delete the original file
		      inFile.delete();
		      
		      //Rename the new file to the filename the original file had.
		      tempFile.renameTo(inFile);
		      
		    }
		    catch (FileNotFoundException ex) {
		      ex.printStackTrace();
		    }
		    catch (IOException ex) {
		      ex.printStackTrace();
		    }
		
		
	}
	
	private void addLineToFile(String file, String lineToAdd){
		
		File outFile = new File(file);
		try {
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFile, true));
			bufferedWriter.write(lineToAdd + "\n");
			bufferedWriter.close();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String setFavouriteFilePath(String path){
		return "src/" + path+ ".txt";
	}

}
