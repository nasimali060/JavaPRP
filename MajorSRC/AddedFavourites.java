import java.util.HashMap;

import api.jaws.Location;
import api.jaws.Shark;

public class AddedFavourites extends HashMap<Shark,Location> {
	
	private static final long serialVersionUID = 1336376075694568244L;

	public AddedFavourites(){
		super();
	}
	
	protected void addToList(Shark shark, Location location){
		this.put(shark, location);
	}
	
}
