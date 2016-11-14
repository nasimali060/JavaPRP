 package radhikanasim.model;

import java.util.ArrayList;

/**
 * 
 * All players will be generated here, and this will be used mainly in the controller
 * @author Radhika
 * @author Nasim
 *
 */

public class Squad {
	private ArrayList<Player> squad = new ArrayList<>();
	Player found;
	
	/**
	 * In this constructor, whenever the Squad class is generated all default players will also be created
	 * All players here will be given default Names, which is their specialisation, and a standard ID so that
	 * all player object will have a unique number referencing to that specific object
	 */
	public Squad(){
		Goalkeeper goalKeeper1 = new Goalkeeper(11);
		squad.add(goalKeeper1);
		Goalkeeper goalKeeper2 = new Goalkeeper(12);
		squad.add(goalKeeper2);
		Defender defender1 = new Defender(21);
		squad.add(defender1);
		Defender defender2 = new Defender(22);
		squad.add(defender2);
		Defender defender3 = new Defender(23);
		squad.add(defender3);
		Defender defender4 = new Defender(24);
		squad.add(defender4);
		Defender defender5 = new Defender(25);
		squad.add(defender5);
		Midfielder midFielder1 = new Midfielder(31);
		squad.add(midFielder1);
		Midfielder midFielder2 = new Midfielder(32);
		squad.add(midFielder2);
		Midfielder midFielder3 = new Midfielder(33);
		squad.add(midFielder3);
		Midfielder midFielder4 = new Midfielder(34);
		squad.add(midFielder4);
		Midfielder midFielder5 = new Midfielder(35);
		squad.add(midFielder5);
		Striker striker1 = new Striker(41);
		squad.add(striker1);
		Striker striker2 = new Striker(42);
		squad.add(striker2);
		Striker striker3 = new Striker(43);
		squad.add(striker3);
	}
	
      /**
       * A return method to search for a specific Player based on their ID
       * @param id an integer field
       * @return a Player object
       */
	public Player search(int id){
		for (int i = 0; i < squad.size();i++){
			if (squad.get(i).getID()==id){
				found = squad.get(i);
			}
		}
		return found;
	}
	
	
}
