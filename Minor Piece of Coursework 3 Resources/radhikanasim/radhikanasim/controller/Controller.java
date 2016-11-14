package radhikanasim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import radhikanasim.model.Squad;
import radhikanasim.view.Fantasy;

/**
 * 
 * This class will act as like a bridge between model classes and the view classes, so anything changed
 * in view will not have a direct effect on the model class, vice versa
 * 
 * @author Radhika
 * @author Nasim
 *
 */
public class Controller {
	
	private Squad squad;
	
	private Fantasy view;
	
	/**
	 * 
	 * @param theView an Fantasy Class field
	 * @param teamSquad an Squad class field
	 */
	public Controller (Fantasy theView, Squad teamSquad){
		view = theView;
		squad = teamSquad;
		
		view.addFormationListner(new FormationListener());
	}
	
	/**
	 * A inner class used to create the action listener that will contain the function of the JComboBox
	 * and will create a view based on the users choice
	 * @author Radhika
	 * @author Nasim
	 *
	 */
	class FormationListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			/**
			 * A simple if else statement that will check the current selected item of JComboBox, and 
			 * based on that will perform a certain action\method of the particular If-else branch
			 */
			if (view.getFormationSelector().getSelectedItem().toString().equals("Select Formation")){
				//Do nothing LoL
				view.getPitch().removeAll();
				view.getBench().removeAll();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("4-4-2")){
				create442();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("4-3-3")){
				create433();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("3-5-2")){
				create352();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("5-3-2")){
				create532();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("3-4-3")){
				create343();
			}
			else if(view.getFormationSelector().getSelectedItem().toString().equals("4-5-1")){
				create451();
			}
			
			
			
		}
			
		}
	
		// The methods for the different formations are bellow
		private void create451() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			view.getDfPitch().add(squad.search(24));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			view.getMdPitch().add(squad.search(34));
			view.getMdPitch().add(squad.search(35));
			
			view.getStPitch().add(squad.search(41));
			
			view.getBench().add(squad.search(25));
			view.getBench().add(squad.search(42));
			view.getBench().add(squad.search(43));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
			
		}

		private void create343() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			view.getMdPitch().add(squad.search(34));
			
			view.getStPitch().add(squad.search(41));
			view.getStPitch().add(squad.search(42));
			view.getStPitch().add(squad.search(43));
			
			view.getBench().add(squad.search(24));
			view.getBench().add(squad.search(25));
			view.getBench().add(squad.search(35));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
			
		}

		private void create532() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			view.getDfPitch().add(squad.search(24));
			view.getDfPitch().add(squad.search(25));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			
			view.getStPitch().add(squad.search(41));
			view.getStPitch().add(squad.search(42));
			
			view.getBench().add(squad.search(34));
			view.getBench().add(squad.search(35));
			view.getBench().add(squad.search(43));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
			
		}

		private void create352() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			view.getMdPitch().add(squad.search(34));
			view.getMdPitch().add(squad.search(35));
			
			view.getStPitch().add(squad.search(41));
			view.getStPitch().add(squad.search(42));
			
			view.getBench().add(squad.search(24));
			view.getBench().add(squad.search(25));
			view.getBench().add(squad.search(43));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
			
		}

		private void create433() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			view.getDfPitch().add(squad.search(24));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			
			view.getStPitch().add(squad.search(41));
			view.getStPitch().add(squad.search(42));
			view.getStPitch().add(squad.search(43));
			
			view.getBench().add(squad.search(25));
			view.getBench().add(squad.search(34));
			view.getBench().add(squad.search(35));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
			
		}

		private void create442() {
			view.getPitch().removeAll();
			view.addPitch();
			view.getGkPitch().add(squad.search(11));
			view.getBench().removeAll();
			view.getBench().add(squad.search(12));
			
			
			view.getDfPitch().add(squad.search(21));
			view.getDfPitch().add(squad.search(22));
			view.getDfPitch().add(squad.search(23));
			view.getDfPitch().add(squad.search(24));
			
			view.getMdPitch().add(squad.search(31));
			view.getMdPitch().add(squad.search(32));
			view.getMdPitch().add(squad.search(33));
			view.getMdPitch().add(squad.search(34));
			
			view.getStPitch().add(squad.search(41));
			view.getStPitch().add(squad.search(42));
			
			view.getBench().add(squad.search(25));
			view.getBench().add(squad.search(35));
			view.getBench().add(squad.search(43));
			
			view.getPitch().revalidate();
			view.getPitch().repaint();
		}
	
		
		
	

}
