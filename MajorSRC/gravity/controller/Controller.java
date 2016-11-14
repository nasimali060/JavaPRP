package gravity.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import api.jaws.Jaws;
import api.jaws.Shark;
import gravity.model.Search;
import gravity.view.FavouritesFrame;
import gravity.view.SearchFrame;
import gravity.view.StartFrame;
import gravity.view.UserProfile;

public class Controller{
	
	private Jaws jaws;
	private StartFrame startFrame;
	private SearchFrame searchFrame;
	private FavouritesFrame favouritesFrame;
	private UserProfile userDialog;
	private Search search;
	
	File users = new File("src/Users.txt");

	public Controller(Jaws jaws, StartFrame startFrame, SearchFrame searchFrame, FavouritesFrame favouritesFrame, UserProfile userDialog, Search search) throws IOException{
		
		this.jaws = jaws;
		
		this.startFrame = startFrame;
		
		this.searchFrame = searchFrame;
		
		this.favouritesFrame = favouritesFrame;
		
		this.userDialog = userDialog;
		
		this.search = search;
		
		startFrame.addController(new StartFrameListener());
		
		searchFrame.addController(new SearchFrameListener());
		
		favouritesFrame.addController(new FavouritesFrameListener());
		
		userDialog.addController(new UserProfileListener());
	
	}
	
	private class StartFrameListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent event) {
			
			//JButton buttonClicked = (JButton) event.getSource();
			
			if(event.getActionCommand().equals("Search")){
				
				searchFrame.getRightPanel().removeAll();
				
				searchFrame.setVisible(true);
			
			}

			else if(event.getActionCommand().equals("Favourites")){
				
				favouritesFrame.setVisible(true);
				//favouritesFrame.getFollowedSharks().clear();
				favouritesFrame.createFavouritesList();
			
			}
			
			else if(event.getActionCommand().equals("Login")){
				userDialog.setVisible(true);
			}
		}
		
	};
	
	private class SearchFrameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event){
			
			JButton buttonClicked = (JButton) event.getSource();
			
			if(event.getActionCommand().equals("Search")){
				
				searchFrame.setFollowedSharks(favouritesFrame.getFollowedSharks());
				
				searchFrame.getRightPanel().removeAll();
				searchFrame.getRightPanel().repaint();
				searchFrame.getRightPanel().revalidate();
				
				search.setTrackingRangeInput(searchFrame.getTrackingRangeInput());
				search.setTagLocationInput(searchFrame.getTagLocationInput());
				search.setGenderInput(searchFrame.getGenderInput());
				search.setStageOfLifeInput(searchFrame.getStageOfLifeInput());
				
				new SearchWorker().execute();
				
			}
			
		}

	};
	
	private class SearchWorker extends SwingWorker<Void, Void>{

		public SearchWorker() {
			
			JPanel jpLoading = new JPanel(new GridLayout(2, 1));
			
			JPanel jpLoadingSearch = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			JLabel jlLoading = new JLabel("LOADING SEARCH...");
			
			jlLoading.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
			
			jlLoading.setForeground(Color.BLACK);
			
			jpLoading.add(new JLabel(new ImageIcon("src/shark2.gif")), BorderLayout.CENTER);
			
			jpLoadingSearch.add(jlLoading);
			
			jpLoading.add(jpLoadingSearch);
			
			searchFrame.getRightPanel().add(jpLoading);
			
			searchFrame.setTitle("Loading...");
		
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			
			search.searchTimeRange();
			search.removeDuplicates();
			search.filter();
			searchFrame.setSearchResults(search.getFinalResults());
			
			return null;
		}
		
		@Override
		protected void done() {
			
			searchFrame.setTitle("Search"); 
			searchFrame.getRightPanel().removeAll();
			searchFrame.getRightPanel().revalidate();
			searchFrame.getRightPanel().repaint();
			searchFrame.createRightPanel();
		
		}
		
	};
	
	private class FavouritesFrameListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
			
			JLabel clickedLabel = (JLabel) mouseEvent.getSource();
			
			searchFrame.getRightPanel().removeAll();
			
			searchFrame.getRightPanel().repaint();
			
			searchFrame.getRightPanel().revalidate();
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private class UserProfileListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			JButton buttonClicked = (JButton) event.getSource();
			if(buttonClicked.getActionCommand().equals("Submit")){
				
				String line = null;
				String usernameInput = userDialog.getInput();
				String passwordInput = userDialog.getPassword();
				System.out.println(passwordInput);
				
				try {
					FileReader 	fr = new FileReader(users);
					BufferedReader br = new BufferedReader(fr);
					
					while((line = br.readLine()) != null) {
					    //System.out.println(line);
						if(line.equals(usernameInput)){					
							System.out.println("UserName Already Taken");
							//JOptionPane.showMessageDialog(null,"UserName Already Taken!", "Error",
	                                 // JOptionPane.ERROR_MESSAGE);
						  }
					}		
				}
				
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					
				FileWriter	fw = new FileWriter(users, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(usernameInput + ": " + passwordInput);
				bw.newLine();
				bw.close();
				System.out.println("Done");
				
				}
				catch (IOException e) {
					e.printStackTrace();
				}
	   
				userDialog.dispose();
				userDialog.getTextField().setText("");
			}
			
		}
		
		
	};
	
}
