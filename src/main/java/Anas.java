import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class Anas {

    public static void main(String[] args) {
        // Create components
        JPanel notesPanel = new JPanel();
        JLabel notesLabel = new JLabel("Notes");
        JScrollPane notesListPane = new JScrollPane();  // Assume this is a JScrollPane for a list
        JButton addNoteButton = new JButton("Add Note");
        JButton logOutButton = new JButton("Log Out");

        // Layout for notes panel
        GroupLayout notesPanelLayout = new GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
                notesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(notesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(notesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(notesLabel)
                                        .addComponent(notesListPane)
                                        .addGroup(notesPanelLayout.createSequentialGroup()
                                                .addComponent(addNoteButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(logOutButton)))
                                .addContainerGap())
        );
        notesPanelLayout.setVerticalGroup(
                notesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(notesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(notesLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notesListPane)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(notesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addNoteButton)
                                        .addComponent(logOutButton))
                                .addContainerGap())
        );

        // Wrap the notesPanel in a JScrollPane to make it scrollable
        JScrollPane scrollableNotesPanel = new JScrollPane(notesPanel);
        scrollableNotesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create a frame and add the JScrollPane
        JFrame frame = new JFrame("Scrollable Notes Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(scrollableNotesPanel);
        frame.setVisible(true);
    }
}
