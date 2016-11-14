import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import api.jaws.Jaws;

public class StartFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 3730560296879467179L;

	private JButton search;
	private JButton favourites;
	private Jaws jaw;
	protected AddedFavourites listofFavourties;
	private SearchFrame sfr;
	private TreeMap<String,String> storedfav;

	public StartFrame(Jaws jaw) {
		super("Amnity Police");
		this.jaw = jaw;
		storedfav = new TreeMap<>();
		setLayout(new BorderLayout());
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWidget();
		listofFavourties = new AddedFavourites();
		pack();
	}

	private void addWidget() {
		JPanel addSpace = new JPanel(new BorderLayout());
		JPanel jpSouth = new JPanel(new GridLayout(2, 1));
		addSpace.add(jpSouth, BorderLayout.SOUTH);
		search = new JButton("Search");
		favourites = new JButton("Favourites");
		addSpace.add(new JLabel(new ImageIcon("src/Logo.png")), BorderLayout.CENTER);
		jpSouth.add(search);
		jpSouth.add(favourites);
		search.addActionListener(this);
		favourites.addActionListener(this);
		addSpace.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sfr = new SearchFrame(jaw,storedfav);
		add(addSpace);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == search.getActionCommand()) {
			sfr.setVisible(true);
		}
		if (e.getActionCommand() == favourites.getActionCommand()) {
			Favourites favframe = new Favourites(storedfav,jaw);
			favframe.setVisible(true);
		}

	}

}