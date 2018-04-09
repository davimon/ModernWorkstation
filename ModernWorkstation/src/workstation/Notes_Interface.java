package workstation;

/*
 * Author: Curtis Warren
 * Description: This is a working example of the notes saving feature.
 * 
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class Notes_Interface extends JFrame {
	
	ArrayList<Notes> listOfNotes = new ArrayList<Notes>();
	private JButton saveBt;
	private JButton setCategoryBt;
	private JButton setTitleBt;
	
	Notes newNote = new Notes();
	
	private static JTextArea notesArea;
	private JPanel NotesCreationPanel;
	private static Font notesFont;
	private static File outputFile;
	
	static {
		
		notesArea = new JTextArea();
		notesFont = new Font("Arial", 20, 20);
		
	}
	
	public Notes_Interface ( ) {
		
		JsonParser parser = new JsonParser();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(400, 300);
								
		NotesCreationPanel = new JPanel();
		
		JPanel buttonsPanel = new JPanel ();
		
		buttonsPanel.setLayout(new FlowLayout());
		
		NotesCreationPanel.setLayout(new BorderLayout());
		
		notesArea = new JTextArea();
		
		notesArea.setFont(notesFont);		
		
		saveBt = new JButton ("SAVE");
		
		setCategoryBt = new JButton("Set Category");
		
		setCategoryBt.addActionListener( e -> {
			
			String results = JOptionPane.showInputDialog(this, "Choose a category for this note.");
			newNote.setCategory(results);
			
			
		});
		
		setTitleBt = new JButton("Set Title");
		
		setTitleBt.addActionListener( e -> {
			
			String results = JOptionPane.showInputDialog(this, "Choose a title for this note.");
			newNote.setTitle(results);
			
		});

		saveBt.addActionListener( e -> {
			
			newNote.setContent(notesArea.getText());
									
			listOfNotes.add(newNote.getID(), newNote);
			
			parser.save(listOfNotes);
			
		});
		
		buttonsPanel.add(setTitleBt);
		buttonsPanel.add(setCategoryBt);
		buttonsPanel.add(saveBt);
		
		NotesCreationPanel.add(buttonsPanel, BorderLayout.SOUTH);
		NotesCreationPanel.add(notesArea, BorderLayout.CENTER);

		this.add(NotesCreationPanel);
		
	}
	
	
	public void createHomeScreen () {
		
	}
	
	public void createNotesScreen () {
		
	}

	public void createOtherScreen () {
	
	}
	
	public static void main (String [] args) {
		
		//SwingUtilities.invokeLater(()-> {

			Notes_Interface notesScreen = new Notes_Interface();
			
			notesScreen.setVisible(true);
			
		
	}

}

