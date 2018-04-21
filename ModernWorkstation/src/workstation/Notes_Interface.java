package workstation;

/*
 * Author: Curtis Warren
 * Description: This version has provided major changes to the application, the design has been changed to more accurately 
 * reflect the UI design first introduced.
 * Version 2.3
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import workstation.Notes_Interface.CustomNotesComponent;


public class Notes_Interface extends JFrame {
	
	ArrayList<Notes> listOfNotes = new ArrayList<Notes>();
	private JButton saveBt;
	private JButton setCategoryBt;
	private JButton setTitleBt;
    private String results;
    private String catResults;
	Notes newNote = new Notes();
	
	private static JTextArea notesArea;
	//  The main panel for page one of the application.
	private JPanel homePanel;
	private JPanel notesCreationPanel;
	private JPanel browsePane;

	private static Font notesFont;
	private static File outputFile;
	private static int currentlyEditedNoteIndex;
	private JButton browseButton;
	
	static {
	
		currentlyEditedNoteIndex= -1;
		notesArea = new JTextArea();
		notesFont = new Font("Arial", 20, 20);
		
	}
	
	public Notes_Interface ( ) {
		
		JsonParser parser = new JsonParser();
		//  If notes have been saved to Notes.json we preserve them.
		if (parser.retrieveNotesArray() != null) {
			
			listOfNotes = parser.retrieveNotesArray();
			
		}
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 400);
							
		/* Start of home and browse button section */
		
		JPanel homeBrowseBtPanel = new JPanel(new GridLayout(1, 2));
		
		JButton homeBt = new JButton("Home");
		JButton brosweBt = new JButton("Browse");
		

		homeBrowseBtPanel.add(homeBt);
		homeBrowseBtPanel.add(brosweBt);

		/* End of home and browse button section*/
		
		/* Start  of the search section */
		
		GridBagConstraints constraints = new GridBagConstraints();

		Insets insets = new Insets(0,0,0,0);
		
		constraints.insets = insets;
		
		JPanel searchPanel = new JPanel(new GridBagLayout());
				
		JLabel noteTitle =  new JLabel ("Notes");
		
		constraints.insets.right = 0;
		constraints.insets.left = 0;
		constraints.insets.bottom = 0;
		constraints.insets.top = 0;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		searchPanel.add(noteTitle, constraints);
		
		JTextField searchField = new JTextField (25);
		searchField.setText("Enter Search Terms");
		searchField.setPreferredSize(new Dimension(20, 20));
		
		searchField.addMouseListener( new MouseAdapter () {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				searchField.setText("");

			}
			
		});
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		searchPanel.add(searchField, constraints);

		JButton goBt = new JButton("Go");
		
		constraints.gridx = 5;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.weightx = 0.25;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		searchPanel.add(goBt, constraints);

		/* End of the search section */
		
		/* Start of browse section */
		
		browsePane = new JPanel();
				
		browsePane.setLayout(new GridLayout(0, 1));
		
		browsePane = resetBrowsePane(browsePane);
		
		JScrollPane scrollBrowsePane = new JScrollPane(browsePane);
		scrollBrowsePane.setPreferredSize(new Dimension(200, 200));

		
		/* End of browse section */

		/* Start of notes creation section */
		notesCreationPanel = new JPanel(new GridBagLayout());
		
		notesArea = new JTextArea(10, 10);
		notesArea.setPreferredSize(new Dimension(10, 10));
		
		notesArea.setFont(notesFont);
		
		JScrollPane notesScrollPane = new JScrollPane(notesArea);
		notesScrollPane.setPreferredSize(new Dimension(200, 200));

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 5;
		constraints.weighty = 2;

		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		notesCreationPanel.add(notesScrollPane, constraints);
		
		constraints.weighty = 1;

		setTitleBt = new JButton("Set Title");
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		notesCreationPanel.add(setTitleBt, constraints);
		
		setTitleBt.addActionListener( e -> {
			
			results = JOptionPane.showInputDialog(this, "Choose a title for this note.");
			newNote.setTitle(results);
			
		});
		
		setCategoryBt = new JButton("Set Category");
		
		constraints.gridx = 1;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		notesCreationPanel.add(setCategoryBt, constraints);
		
		
		setCategoryBt.addActionListener( e -> {
			
			catResults = JOptionPane.showInputDialog(this, "Choose a category for this note.");
			newNote.setCategory(catResults);
			
			
		});
		JButton editBt = new JButton ("Edit");
		
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;


		notesCreationPanel.add(editBt, constraints);

		editBt.addActionListener( e -> {
			
			newNote.setContent(notesArea.getText());

			if (currentlyEditedNoteIndex != -1) {
				
				listOfNotes.remove(currentlyEditedNoteIndex);
				listOfNotes.add(currentlyEditedNoteIndex, newNote);

				parser.save(listOfNotes);
				
				browsePane.removeAll();
				
				browsePane = resetBrowsePane(browsePane);
				
				browsePane.setVisible(false);
				browsePane.repaint();
				browsePane.setVisible(true);
				
				
			}
			
			
		});
		
		JButton deleteBt = new JButton ("Delete");
		
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		notesCreationPanel.add(deleteBt, constraints);
		
		/* End of notes creation section */
		
		/* Start of the add note section */

		JPanel addNotePanel = new JPanel(new GridBagLayout());
		
		JButton addNoteBt = new JButton("Add Note");
		addNoteBt.setFont(notesFont);
		
		addNoteBt.addActionListener( e -> {
			
			newNote.setContent(notesArea.getText());
							
			//  Adds to the list.
			listOfNotes.add(newNote);
			
			parser.save(listOfNotes);
						
			browsePane.removeAll();
			
			browsePane = resetBrowsePane(browsePane);
			
			browsePane.setVisible(false);
			browsePane.repaint();
			browsePane.setVisible(true);
			
			
		});

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.fill = GridBagConstraints.BOTH;

		
		addNotePanel.add(addNoteBt, constraints);

		
		/* End of the add note section */

		
		homePanel = new JPanel (new GridBagLayout());
		
		constraints.insets = new Insets(0, 5, 0, 0);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;

		
		homePanel.add(homeBrowseBtPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		homePanel.add(searchPanel, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		
		homePanel.add(scrollBrowsePane, constraints);
		
		constraints.insets = new Insets(0, 20, 0, 0);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;


		homePanel.add(notesCreationPanel, constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.fill = GridBagConstraints.BOTH;


		homePanel.add(addNotePanel, constraints);
		

		this.add(homePanel);
		
	}
	
	public JPanel resetBrowsePane(JPanel newBrowsePane) {
		
		JsonParser brosweParser = new JsonParser();

		int row = 0;
		int col = 0;
		Boolean alternateColor = true;
		if (brosweParser.retrieveNotesArray() == null) {
			
		} else {
			
			listOfNotes = brosweParser.retrieveNotesArray();
			
			for (Notes note : listOfNotes) {
				
				String contentPreview;
				
				if (note.getContent().length() < 14) {
					
					contentPreview = note.getContent();
					
				} else {
					
					contentPreview = note.getContent().substring(0, 10) + "...";

				}
				
				CustomNotesComponent customComponent = new CustomNotesComponent("Title: " + note.getTitle(), "Content: " + contentPreview, alternateColor);
				
				customComponent.setID(listOfNotes.indexOf(note));
				
				customComponent.edit.addActionListener(e -> {
					
					Notes editableNote = listOfNotes.get(customComponent.getID());
					
					currentlyEditedNoteIndex = customComponent.getID();
					
					notesArea.setText(editableNote.getContent());

					//  Resets after data for newNote.
					newNote.setContent("");
					newNote.setCategory("");
					newNote.setTitle("");
					
				});
				
				newBrowsePane.add(customComponent, row, 0);
			
				System.out.print("[" + note.getContent() + "]");
			
				alternateColor = !alternateColor;
				row++;
			}
		}
		
		return newBrowsePane;
		
	}
	public void createHomeScreen () {
		
	}
	public void editNotesScreen () {
		
	}
	public void createNotesScreen () {
		
	}

	public void createOtherScreen () {
	
	}
	
	public void browseScreen () {
	
		
		//this.add(scrollBrowsePane);
		
		//  Cleanly repaints the new screen.
		this.repaint();
		this.setVisible(true);
		
	}
	
	public class CustomNotesComponent extends JPanel {
		
		JLabel noteTitle;
		JLabel noteContent;
		JButton edit;
		int ID;
		
		CustomNotesComponent(String newTitle, String newContent, Boolean alternate) {
			
			GridBagConstraints compConstraints = new GridBagConstraints();

			Insets compInsets = new Insets(0,0,0,0);
			
			compConstraints.insets = compInsets;
			
			noteTitle = new JLabel(newTitle);
			noteContent = new JLabel(newContent);
			edit = new JButton ("edit");
			
			this.setLayout(new GridBagLayout());
			
			compConstraints.gridx = 0;
			compConstraints.gridy = 0;
			compConstraints.weightx = 1;
			compConstraints.weighty = 1;
			compConstraints.gridwidth = 3;
			compConstraints.gridheight = 1;
			compConstraints.fill = GridBagConstraints.HORIZONTAL;


			this.add(noteTitle, compConstraints);

			compConstraints.gridx = 0;
			compConstraints.gridy = 1;
			compConstraints.weightx = 1;
			compConstraints.weighty = 1;
			compConstraints.gridwidth = 2;
			compConstraints.gridheight = 1;
			compConstraints.fill = GridBagConstraints.HORIZONTAL;

			this.add(noteContent, compConstraints);
			
			compConstraints.gridx = 2;
			compConstraints.gridy = 1;
			compConstraints.weightx = .2;
			compConstraints.weighty = .2;
			compConstraints.gridwidth = 1;
			compConstraints.gridheight = 1;
			compConstraints.fill = GridBagConstraints.HORIZONTAL;

			this.add(edit, compConstraints);
			
			//  Alternates color of background.
			if (alternate) {
				this.setBackground(new Color(.5f, .7f, .75f));
			} else {
				this.setBackground(new Color(.5f, .5f, .7f));
			}

		}
		
		void setID (int id) {
			
			this.ID =  id;
			
		}
		
		int getID () {
			
			return this.ID;
			
		}
		
	}
	
	public static void main (String [] args) {
		
			Notes_Interface notesScreen = new Notes_Interface();
			
			notesScreen.setVisible(true);	
		
	}

}


