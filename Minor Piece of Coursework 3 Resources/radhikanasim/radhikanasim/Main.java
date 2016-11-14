package radhikanasim;

import radhikanasim.controller.Controller;
import radhikanasim.model.Squad;
import radhikanasim.view.Fantasy;

/**
 *  
 * @author Radhika
 * @author Nasim
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Fantasy view = new Fantasy();
		Squad model = new Squad();
		Controller controller = new Controller(view, model);

	}

}
