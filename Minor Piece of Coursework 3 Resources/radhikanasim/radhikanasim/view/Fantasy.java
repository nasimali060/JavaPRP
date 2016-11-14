package radhikanasim.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Radhika
 * @author Nasim
 *
 */
public class Fantasy extends JFrame {
	
	private static final long serialVersionUID = 5261975429182702904L;
	
	JPanel pitch;
	JPanel bench;
	JPanel gkPitch;
	JPanel dfPitch;
	JPanel mdPitch;
	JPanel stPitch;
	JComboBox <String> jcbFormations;
	
	public Fantasy(){
		super("Fantasy Football");
		this.setSize(350,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		addWidgets();
		setVisible(true);
	}
	
	private void addWidgets(){
		String[] diffFormations = {"Select Formation","4-4-2","4-3-3","3-5-2","5-3-2","3-4-3","4-5-1"};
		jcbFormations = new JComboBox<String>(diffFormations);
		add(jcbFormations, BorderLayout.NORTH);
		
		pitch = new JPanel(new GridLayout(4,1));
		bench = new JPanel();
	
		add(pitch,BorderLayout.CENTER);
		add(bench,BorderLayout.SOUTH);
		
		addPitch();
		
	}
	
	public void addPitch(){
		pitch.removeAll();
		pitch.revalidate();
		pitch.repaint();
		gkPitch = new JPanel(new FlowLayout(FlowLayout.CENTER));
		dfPitch = new JPanel(new FlowLayout(FlowLayout.CENTER));
		mdPitch = new JPanel(new FlowLayout(FlowLayout.CENTER));
		stPitch = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		pitch.add(gkPitch);
		pitch.add(dfPitch);
		pitch.add(mdPitch);
		pitch.add(stPitch);
		}
	
	public JPanel getGkPitch(){
		return gkPitch;
	}

	public JPanel getDfPitch(){
		return dfPitch;
	}
	
	public JPanel getMdPitch(){
		return mdPitch;
	}
	
	public JPanel getStPitch(){
		return stPitch;
	}
	
	public JPanel getBench(){
		return bench;
	}
	
	public JPanel getPitch(){
		return pitch;
	}
	
	public JComboBox<String> getFormationSelector (){
		return jcbFormations;
	}
	
	public void addFormationListner(ActionListener listenForFormation){
		jcbFormations.addActionListener(listenForFormation);
	}
}
