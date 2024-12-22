import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 *
 * @author IslamGomaa
 */
public class EditorFrame extends javax.swing.JFrame {

    private JTextArea textArea;
    private JEditorPane previewPane;
    private boolean isRawMode = true;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public EditorFrame() {
        initComponents();
        initializeMarkdownEditor();
    }

    private void initializeMarkdownEditor() {
        // Create the text area for raw markdown
        textArea = new JTextArea();
        textArea.setEditable(true);
        JScrollPane textScrollPane = new JScrollPane(textArea);

        // Create the preview pane
        previewPane = new JEditorPane();
        previewPane.setContentType("text/html");
        previewPane.setEditable(false);
        JScrollPane previewScrollPane = new JScrollPane(previewPane);

        // Create a CardLayout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add text area and preview pane to cardPanel
        cardPanel.add(textScrollPane, "Raw");
        cardPanel.add(previewScrollPane, "Preview");

        // Add markdown editor to editorPanel
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(cardPanel, BorderLayout.CENTER);

        // Add auto-save listener
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveToMarkdownFile();
                if (!isRawMode) {
                    updatePreview();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveToMarkdownFile();
                if (!isRawMode) {
                    updatePreview();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveToMarkdownFile();
                if (!isRawMode) {
                    updatePreview();
                }
            }
        });

        // Initialize the preview pane with default content
        updatePreview();
    }

    private void saveToMarkdownFile() {
        String content = textArea.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("note.md"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePreview() {
        String rawMarkdown = textArea.getText();
        if (rawMarkdown.isEmpty()) {
            previewPane.setText("<html><body><p>No content to display.</p></body></html>");
        } else {
            try {
                String htmlContent = convertMarkdownToHtml(rawMarkdown);
                previewPane.setText(htmlContent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String convertMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        return renderer.render(document);
    }

    private void toggleMode(JButton toggleButton) {
        isRawMode = !isRawMode;
        cardLayout.show(cardPanel, isRawMode ? "Raw" : "Preview");
        toggleButton.setText(isRawMode ? "Switch to Preview Mode" : "Switch to Raw Mode");
        if (!isRawMode) {
            updatePreview();
        }
    }

    private void initComponents() {
        editorPanel = new javax.swing.JPanel();
        notesPanel = new javax.swing.JPanel();
        notesLabel = new javax.swing.JLabel();
        notesListPane = new javax.swing.JScrollPane();
        notesList = new javax.swing.JList<>();
        logOutButton = new javax.swing.JButton();
        addImageButton = new javax.swing.JButton();
        addSketchButton = new javax.swing.JButton();
        toggleButton = new javax.swing.JButton("Switch to Preview Mode");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        editorPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout editorPanelLayout = new javax.swing.GroupLayout(editorPanel);
        editorPanel.setLayout(editorPanelLayout);
        editorPanelLayout.setHorizontalGroup(
                editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1011, Short.MAX_VALUE)
        );
        editorPanelLayout.setVerticalGroup(
                editorPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 648, Short.MAX_VALUE)
        );

        notesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        notesLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));
        notesLabel.setText("Notes");

        notesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        notesListPane.setViewportView(notesList);

        logOutButton.setText("Log Out");

        javax.swing.GroupLayout notesPanelLayout = new javax.swing.GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
        notesPanelLayout.setHorizontalGroup(
                notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(notesPanelLayout.createSequentialGroup()
                                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(notesPanelLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addGroup(notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(notesListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(notesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(notesPanelLayout.createSequentialGroup()
                                                .addGap(47, 47, 47)
                                                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        notesPanelLayout.setVerticalGroup(
                notesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(notesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(notesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(notesListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(logOutButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addImageButton.setText("Add Image");
        addSketchButton.setText("Add Sketch");

        toggleButton.addActionListener(e -> toggleMode(toggleButton));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(notesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(editorPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(320, 320, 320)
                                                .addComponent(addImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addSketchButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(toggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(notesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(editorPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(addImageButton)
                                                        .addComponent(addSketchButton)
                                                        .addComponent(toggleButton))))
                                .addContainerGap())
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new EditorFrame().setVisible(true));
    }

    protected javax.swing.JButton addImageButton;
    protected javax.swing.JButton addSketchButton;
    private javax.swing.JPanel editorPanel;
    private javax.swing.JLabel notesLabel;
    protected javax.swing.JList<String> notesList;
    protected javax.swing.JButton logOutButton;
    private javax.swing.JScrollPane notesListPane;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JButton toggleButton;
}
