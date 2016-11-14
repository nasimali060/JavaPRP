package gravity.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.jaws.Jaws;
import api.jaws.Shark;
import api.jaws.Location;

public class FavouritesFrame extends JFrame{
	
	private static final long serialVersionUID = -4603689865706099368L;
	private List<String> followedSharks;
	private Map<Double, Shark> sortedSharksDistance;
	private MouseListener controller;
	private JPanel jpCenter;
	private Jaws jaws;
	
	public FavouritesFrame(Jaws jaws) {
		
		super("Favourites");
		
		this.jaws = jaws;
		followedSharks = new ArrayList<String>();
		sortedSharksDistance = new TreeMap<Double, Shark>();
		
		setLayout(new BorderLayout());
		setSize(500,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		addWidgets();
		readStoredFollowedSharks();
	}

	private void addWidgets() {
		
		JPanel jpFrame = new JPanel(new BorderLayout());
		jpFrame.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
		jpCenter =  new JPanel();
		jpCenter.setLayout(new BoxLayout(jpCenter, BoxLayout.Y_AXIS));
		jpCenter.setPreferredSize(new Dimension(400,300));
		jpCenter.setBackground(Color.WHITE);
		
		jpFrame.add(new JLabel("Your favourite sharks are this far away from you right now: "), BorderLayout.NORTH);
		jpFrame.add(jpCenter, BorderLayout.CENTER);
		
		add(jpFrame, BorderLayout.CENTER);
	}
	
	public void createFavouritesList() {
		
		jpCenter.removeAll();
		jpCenter.repaint();
		jpCenter.revalidate();
		sortedSharksDistance.clear();
		followedSharks.clear();
		
		readStoredFollowedSharks();
		
		sortFollowedSharks();
		this.repaint();
		this.revalidate();
		
	}
	
	private void createSharksLabel(){
		
		if(!sortedSharksDistance.isEmpty()){
			for (Double distance: sortedSharksDistance.keySet()){
				
				JLabel jlFavouriteShark = new JLabel();
				jlFavouriteShark.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				jlFavouriteShark.setText(sortedSharksDistance.get(distance) + ": " + distance + " miles");
				jlFavouriteShark.addMouseListener(controller);
				
				jpCenter.add(jlFavouriteShark);
			}
		}
	}

	private double calculateDistance(String name){
		
		Location location = jaws.getLastLocation(name);
	    double lat1 = location.getLatitude();
	    double lon1 = location.getLongitude();
	    
	    final double kingsLatitude = 51.5119;
	    final double kingsLongitute = 0.1161;
	    final double earthRadius = 6372.8; // In Kilometers.
        
	    double differenceLat = Math.toRadians(kingsLatitude - lat1);
        double differenceLong = Math.toRadians(kingsLongitute - lon1);
        
        lat1 = Math.toRadians(lat1);
        
        final double kingsLatitudeAngle = Math.toRadians(kingsLatitude);
        
        double part1 =  Math.pow(Math.sin(differenceLat / 2), 2) + 
        				Math.pow(Math.sin(differenceLong / 2), 2) * 
        				Math.cos(lat1) * Math.cos(kingsLatitudeAngle);
        double part2 = 2 * Math.asin(Math.sqrt(part1));
     
        double finalValue =  earthRadius * part2; //Distance To Kings.
        finalValue = Math.round(finalValue*100);
        finalValue = finalValue/100;
        
        return finalValue;
	}
	
	public void addController(MouseListener controller){
		this.controller = controller;
	}
	
	public void readStoredFollowedSharks(){
		
		File file = new File("src/FavouriteSharks.txt");
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = null;
		try {
			
			while( (line = bufferedReader.readLine())!= null){
				followedSharks.add(line);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
	}
	
	public void sortFollowedSharks(){
		String currentSharkDate = null;
		for (String sharkDate: followedSharks){
			currentSharkDate = sharkDate;
			sortedSharksDistance.put(calculateDistance(currentSharkDate), jaws.getShark(currentSharkDate));
		}
	
	}
	
	public ArrayList<String> getFollowedSharks(){
		return  (ArrayList<String>) followedSharks;
	}

}
