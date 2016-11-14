package gravity;

import gravity.view.FavouritesFrame;
import gravity.view.SearchFrame;
import gravity.view.StartFrame;
import gravity.view.UserProfile;
import gravity.controller.Controller;
import gravity.model.Search;

import java.io.IOException;

import api.jaws.Jaws;

public class Main {

	public static void main(String[] args) throws IOException {
	
		Jaws jaws = new Jaws("4858yafFqkmaKcGp","XR90JLdpYJc3Ywgh");
		
		//Instantiate Model
		Search search = new Search(jaws);
		
		//Instantiate View searchFrame
		SearchFrame searchFrame = new SearchFrame(jaws);
		
		//Instantiate View favourites
		FavouritesFrame favourites = new FavouritesFrame(jaws);
		
		//Instantiate View startFrame
		StartFrame startFrame = new StartFrame(jaws);
		
		//Instantiate View userDialog
		UserProfile userDialog = new UserProfile();	
		
		//Instantiate Controller
		Controller controller = new Controller(jaws, startFrame, searchFrame, favourites, userDialog, search);
		
		startFrame.setVisible(true);
		
		
	}

}
