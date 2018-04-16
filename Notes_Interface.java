package workstation;

/*
 * Author: Curtis Warren
 * Description: This is a working example of the notes saving feature.
 * Version 2.0
 */
import java.awt.BorderLayout;
import java.awt.Color;
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
	
	private JButton browseButton;
	
	static {
	
		notesArea = new JTextArea();
		notesFont = new Font("Arial", 20, 20);
		
	}
	
	public Notes_Interface ( ) {
		
		JsonParser parser = new JsonParser();

		//  Recovers previous data structure of notes.
		listOfNotes = parser.retrieveNotesArray();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500, 400);
								
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
							
			//  Adds to the list.
			listOfNotes.add(newNote);
			
			parser.save(listOfNotes);
			
		});
		

		browseButton = new JButton("Browse");
		
		browseButton.addActionListener( e -> {
			
			browseScreen();
			
		});
		
		buttonsPanel.add(setTitleBt);
		buttonsPanel.add(setCategoryBt);
		buttonsPanel.add(saveBt);
		buttonsPanel.add(browseButton);
		
		
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
	
	public void browseScreen () {
		
		JsonParser browserParser = new JsonParser();

		this.remove(NotesCreationPanel);

		JPanel browsePane = new JPanel();
		
		JScrollPane scrollBrowsePane = new JScrollPane(browsePane);
		
		browsePane.setLayout(new GridLayout(4, 4));
		
		int row = 0;
		int col = 0;
		Boolean alternateColor = true;
		for (Notes note : browserParser.retrieveNotesArray()) {
			
			browsePane.add(new CustomNotesComponent("Title: " + note.getTitle(), "Content: " + note.getContent(), alternateColor), row, 0);
			
			System.out.print("[" + note.getContent() + "]");
			alternateColor = !alternateColor;
			
			row++;
		}
		
		this.add(scrollBrowsePane);
		
		//  Cleanly repaints the new screen.
		this.repaint();
		this.setVisible(true);
		
	}
	
	public class CustomNotesComponent extends JPanel {
		
		JLabel noteTitle;
		JLabel noteContent;
		
		CustomNotesComponent(String newTitle, String newContent, Boolean alternate) {
			
			noteTitle = new JLabel(newTitle);
			noteContent = new JLabel(newContent);
			
			this.setLayout(new GridLayout(2,2));
			
			this.add(noteContent, 1, 0);
			this.add(noteTitle, 0, 0);

			//  Alternates color of background.
			if (alternate) {
				this.setBackground(new Color(.5f, .7f, .75f));
			} else {
				this.setBackground(new Color(.5f, .5f, .7f));
			}

		}
		
	}
	
	public static void main (String [] args) {
		
			Notes_Interface notesScreen = new Notes_Interface();
			
			notesScreen.setVisible(true);	
		
	}

}

