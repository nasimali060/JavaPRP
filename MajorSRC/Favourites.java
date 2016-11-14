import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import api.jaws.Jaws;
import api.jaws.Shark;

public class Favourites extends JFrame {

	private static final long serialVersionUID = -2921993554023162377L;
	private Jaws jaws;
	private TreeMap<String,String> storedfav;
	
	public Favourites(TreeMap <String,String> storedfav, Jaws jaws) {
		super("Favourites");
		this.setLayout(new BorderLayout());
		setSize(500,400);
		this.storedfav = storedfav;
		this.jaws = jaws;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createHeader();
		createFavouritesList();
	}

	private void createHeader() {
		JPanel headerPanel = new JPanel();
		JLabel header = new JLabel("Your favourite sharks are this far away from you right now: ");
		headerPanel.add(header);
		this.add(headerPanel, BorderLayout.NORTH);
	}

	private void createFavouritesList() {
		JPanel listPanel = new JPanel();
		JTextArea jtFavouritesTracking = new JTextArea();
		jtFavouritesTracking.setEditable(false);
		jtFavouritesTracking.setPreferredSize(new Dimension(400,300));
		
		// Check this part, as there wasn't any internet
		for(String date: storedfav.keySet()){
			jtFavouritesTracking.append(storedfav.get(date)+": "+ calculateDistance(storedfav.get(date))+"miles");
			jtFavouritesTracking.append("\n");
			calculateDistance(storedfav.get(date));
			
		}
		
		
		
		
		//JList<String> favouritesList = new JList<String>();
		//favouritesList.setPreferredSize(new Dimension(400,300));
		listPanel.add(jtFavouritesTracking);
		this.add(listPanel, BorderLayout.CENTER);
	}
	
	public double calculateDistance(String name){
		    double lat1 = jaws.getLastLocation(name).getLatitude();
	        double lon1 = jaws.getLastLocation(name).getLongitude();
	        double kingsLatitude = 51.5119;
	        double kingsLongitute = 0.1161;
	        final double earthRadius = 6372.8; // In Kilometers.
	        double differenceLat = Math.toRadians(kingsLatitude - lat1);
	        double differenceLong = Math.toRadians(kingsLongitute - lon1);
	        lat1 = Math.toRadians(lat1);
	        kingsLatitude = Math.toRadians(kingsLatitude);
	        double part1 = Math.pow(Math.sin(differenceLat / 2), 2) + Math.pow(Math.sin(differenceLong / 2), 2) * Math.cos(lat1) * Math.cos(kingsLatitude);
	        double part2 = 2 * Math.asin(Math.sqrt(part1));
	        double finalValue =  earthRadius * part2; //Distance To Kings.
	        finalValue = Math.round(finalValue*100);
	        finalValue = finalValue/100;
	        return finalValue;
	}
	
	
	
	

}
