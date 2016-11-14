import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingWorker;

import api.jaws.Jaws;
import api.jaws.Shark;

public class SearchFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Jaws jaws;
	private JPanel jpFrame;
	private JPanel jpRightPanel;
	protected String trackingRangeInput;
	protected String genderInput;
	protected String stageOfLifeInput;
	protected String tagLocationInput;
	protected TreeMap<String,String> storedfav;
	private TreeMap<String, Shark> finalSearchResults;

	public SearchFrame(Jaws jaws, TreeMap <String,String> storedfav) {
		super("Search");
		this.jaws = jaws;
		setSize(900, 750);
		this.storedfav = storedfav;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		jpFrame = new JPanel(new BorderLayout());
		jpFrame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		createLeftPanel();
		createCopyright();
		add(jpFrame, BorderLayout.CENTER);
		pack();
	}

	private void createLeftPanel() {

		JPanel jpMainLeftPanel = new JPanel(new BorderLayout());
		JPanel titlePanel = new JPanel(new GridLayout(2, 1));

		JLabel jlTitle = new JLabel("Shark Tracker");
		jlTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 25));

		String[] trackingRanges = { "Past 24 Hours", "Past Week", "Past Month" };
		String[] sharkGenders = { "All", "Male", "Female" };
		String[] sharkStageOfLife = { "All", "Mature", "Immature", "Undetermined" };
		ArrayList <String> sharkTageLocations = jaws.getTagLocations();
		String[] sharkTagLocations = new String[sharkTageLocations.size()];
		sharkTagLocations = sharkTageLocations.toArray(sharkTagLocations);

		JComboBox<String> jcbGender = new JComboBox<String>(sharkGenders);
		JComboBox<String> jcbTrackingRange = new JComboBox<String>(trackingRanges);
		JComboBox<String> jcbStageOfLife = new JComboBox<String>(sharkStageOfLife);
		JComboBox<String> jcbTagLocation = new JComboBox<String>(sharkTagLocations);
		jcbTagLocation.addItem("All");
		jcbTagLocation.setSelectedItem("All");

		JButton jbSearchButton = new JButton("Search");

		jpMainLeftPanel.add(titlePanel, BorderLayout.NORTH);
		titlePanel.add(jlTitle);
	

		JPanel jpFilter = new JPanel(new GridLayout(14, 1));
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
		JPanel jpLeftPanelGap = new JPanel();
		jpLeftPanelGap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		jpLeftPanelGap.add(jpMainLeftPanel);
		jpRightPanel = new JPanel(new BorderLayout());
		jpRightPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));
		jpFrame.add(jpLeftPanelGap, BorderLayout.WEST);
		jpFrame.add(jpRightPanel, BorderLayout.CENTER);

		jbSearchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jpRightPanel.removeAll();
				jpRightPanel.repaint();
				jpRightPanel.revalidate();
				trackingRangeInput = jcbTrackingRange.getSelectedItem().toString();
				genderInput = jcbGender.getSelectedItem().toString();
				stageOfLifeInput = jcbStageOfLife.getSelectedItem().toString();
				tagLocationInput = jcbTagLocation.getSelectedItem().toString();
				new SearchWorker().execute();
			}
		});
	}

	private void createCopyright() {
		JLabel jlCopyRight = new JLabel(jaws.getAcknowledgement());
		add(jlCopyRight, BorderLayout.SOUTH);
	}

	private void createRightPanel() {
		JPanel jpFinalResultsPanel = new JPanel();
		if (finalSearchResults.isEmpty()){
			jpFinalResultsPanel.setLayout(new BorderLayout());
			jpFinalResultsPanel.removeAll();
			JLabel jlnoResults = new JLabel("NO RESULTS FOUND!");
			jlnoResults.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
			jlnoResults.setForeground(Color.BLACK);
			jpFinalResultsPanel.add(jlnoResults, BorderLayout.WEST);
			
		}
		else{
			jpFinalResultsPanel.setLayout(new GridLayout(0, 1));
			jpFinalResultsPanel.removeAll();
			for (String shark : finalSearchResults.keySet()) {
				JPanel jpMainRightPanel = new JPanel(new BorderLayout());
				JPanel jpSharkDetails = new JPanel(new GridLayout(6, 2));
				JPanel jpSharkDescription = new JPanel(new GridLayout(6,1));
	
				jpSharkDetails.add(new JLabel("Name: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getName()));
				jpSharkDetails.add(new JLabel("Gender: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getGender()));
				jpSharkDetails.add(new JLabel("Stage of Life: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getStageOfLife()));
				jpSharkDetails.add(new JLabel("Species: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getSpecies()));
				jpSharkDetails.add(new JLabel("Length: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getLength()));
				jpSharkDetails.add(new JLabel("Weight: "));
				jpSharkDetails.add(new JLabel(finalSearchResults.get(shark).getWeight()));
	
				String description = finalSearchResults.get(shark).getDescription();
				StringBuilder descrip = new StringBuilder(description.length());
				descrip.append("<html>" + description + "<html>");
				JLabel jlDescriptionContents = new JLabel(descrip.toString());
				jpSharkDescription.add(new JLabel("Description:"));
				jpSharkDescription.add(jlDescriptionContents);
		
				
	
				jpMainRightPanel.add(jpSharkDetails, BorderLayout.NORTH);
				jpMainRightPanel.add(jpSharkDescription, BorderLayout.CENTER);
				JPanel jpPingAndFav = new JPanel(new BorderLayout());
				JButton jbFavourites = new JButton("Follow");
	
				jpPingAndFav.add(new JLabel("Last Ping : " + shark), BorderLayout.WEST);
				jpPingAndFav.add(jbFavourites, BorderLayout.EAST);
				jpMainRightPanel.add(jpPingAndFav, BorderLayout.SOUTH);
				jpMainRightPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
				jpMainRightPanel.setPreferredSize(new Dimension(470, 500));
				jpFinalResultsPanel.add(jpMainRightPanel);
				String date = shark;
				jbFavourites.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						jbFavourites.setText("Following");
						String name = finalSearchResults.get(shark).getName();
						
						storedfav.put(date,name);
						
						System.out.println(date);
						//jaws.getLastLocation(finalSearchResults.get(shark).getName())
						
						
						
						
						
					}
	
				});

		}
		}
		jpFinalResultsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		jpFinalResultsPanel.revalidate();
		jpFinalResultsPanel.repaint();
		jpRightPanel.add(new JScrollPane(jpFinalResultsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

	}

	class SearchWorker extends SwingWorker<Void, Void> {

		public SearchWorker() {
			JPanel jpLoading = new JPanel(new GridLayout(2, 1));
			JPanel jpLoadingSearch = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JLabel jlLoading = new JLabel("LOADING SEARCH");
			jlLoading.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
			jlLoading.setForeground(Color.BLACK);
			jpLoading.add(new JLabel(new ImageIcon("src/shark2.gif")), BorderLayout.CENTER);
			jpLoadingSearch.add(jlLoading);
			jpLoading.add(jpLoadingSearch);
			jpRightPanel.add(jpLoading);
			SearchFrame.this.setTitle("Loading...");
		}

		@Override
		protected Void doInBackground() throws Exception {
			Search search = new Search(trackingRangeInput, genderInput, stageOfLifeInput, tagLocationInput, jaws);
			finalSearchResults = search.finalSharkResults;
			return null;
		}

		@Override

		protected void done() {
			SearchFrame.this.setTitle("Search"); 
			jpRightPanel.removeAll();
			jpRightPanel.revalidate();
			jpRightPanel.repaint();
			createRightPanel();
			jpFrame.repaint();
			jpFrame.validate();
		}
	}
}
