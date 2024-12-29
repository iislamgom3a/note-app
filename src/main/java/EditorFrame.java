import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// API for markdown format rendering
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class EditorFrame extends javax.swing.JFrame {
    // Component declarations
    protected javax.swing.JButton addImageButton;
    protected javax.swing.JButton addSketchButton;
    protected javax.swing.JButton addNoteButton;
    protected javax.swing.JPanel editorPanel;
    private javax.swing.JLabel notesLabel;
    protected JList<String> notesList;
    protected javax.swing.JButton logOutButton;
    private javax.swing.JPanel notesPanel;
    private javax.swing.JButton toggleButton;
    protected JTextArea textArea;
    private JEditorPane previewPane;
    protected boolean isRawMode = true;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    protected String currentNote;
    protected JPanel imagePanel;
    private JSplitPane mainSplitPane;
    private JSplitPane editorImageSplitPane;
    private final JPanel imageDisplayPanel;
    protected String currnetUserName;
    JScrollPane notesListPane ;
    // End of Component declarations

    public EditorFrame() {
        this.imageDisplayPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 1 column, 3 rows
        initComponents();
        initializeMarkdownEditor();
        setSize(1600, 900);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    private void initializeMarkdownEditor() {
        imagePanel = new JPanel();
        imagePanel.setBorder(BorderFactory.createTitledBorder("Images & Sketches"));

        textArea = new JTextArea();
        textArea.setEditable(true);
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Monospace", Font.PLAIN, 15));

        JScrollPane textScrollPane = new JScrollPane(textArea);
        textScrollPane.setPreferredSize(new Dimension(800, 600));

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

        // Create split panes for layout
        editorImageSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, cardPanel, imagePanel);
        editorImageSplitPane.setResizeWeight(0.7);

        // Add to editor panel
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(editorImageSplitPane, BorderLayout.CENTER);

        // Add auto-save listener
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                saveToMarkdownFile(currentNote,currnetUserName);
                if (!isRawMode) {
                    updatePreview();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveToMarkdownFile(currentNote, currnetUserName);
                if (!isRawMode) {
                    updatePreview();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveToMarkdownFile(currentNote, currnetUserName);
                if (!isRawMode) {
                    updatePreview();
                }
            }
        });
        // Initialize the preview pane with default content
        updatePreview();
    }

    private void saveToMarkdownFile(String title, String currnetUserName) {
        String content = textArea.getText();
        File noteFile = new File(User.USERs_FOLDER_PATH + File.separator + currnetUserName+ File.separator+title + ".md");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(noteFile))) {
            writer.write(content); // Overwrite the file
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(),
                    "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void loadNoteContent(String noteName, String currnetUserName) {
        File noteFile = new File(User.USERs_FOLDER_PATH + File.separator + currnetUserName+ File.separator+ noteName + ".md");
        if (noteFile.exists()) {
            try {
                String content = new String(java.nio.file.Files.readAllBytes(noteFile.toPath()));
                textArea.setText(content);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading note: " + e.getMessage(),
                        "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            textArea.setText(""); // Clear textArea if file doesn't exist
        }
    }

    protected void updatePreview() {
        String rawMarkdown = textArea.getText();
        if (rawMarkdown.isEmpty()) {
            previewPane.setText("<html><body><p>No content to display.</p></body></html>");
        } else {
            try {
                String htmlContent = convertMarkdownToHtml(rawMarkdown);
                previewPane.setText(htmlContent);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error updating preview: " + e.getMessage(),
                        "Preview Error", JOptionPane.ERROR_MESSAGE);
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
        notesList = new JList<>();
        logOutButton = new javax.swing.JButton();
        addImageButton = new javax.swing.JButton();
        addSketchButton = new javax.swing.JButton();
        toggleButton = new javax.swing.JButton("Switch to Preview Mode");
        addNoteButton = new javax.swing.JButton();


        notesList = new JList<>(new DefaultListModel<>());
        notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        notesList.setVisibleRowCount(-1);
        notesListPane.setViewportView(notesList);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);

        editorPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        // Set preferred width for notes panel (adjust as needed)
        notesPanel.setPreferredSize(new Dimension(200, getHeight()));
        notesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        notesLabel.setFont(new java.awt.Font("Segoe UI", 1, 14));
        notesLabel.setText("Notes");

        logOutButton.setText("Log Out");
        addNoteButton.setText("Add Note");
        addImageButton.setText("Add Image");
        addSketchButton.setText("Add Sketch");

        notesListPane.setViewportView(notesList);
        toggleButton.addActionListener(e -> toggleMode(toggleButton));
        GroupLayout notesPanelLayout = new GroupLayout(notesPanel);
        notesPanel.setLayout(notesPanelLayout);
// Horizontal Group
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

// Vertical Group
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

        JScrollPane scrollableNotesPanel = new JScrollPane(notesPanel);
        scrollableNotesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addImageButton);
        buttonPanel.add(addSketchButton);
        buttonPanel.add(toggleButton);

        // Main content layout with BorderLayout
        mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollableNotesPanel, editorPanel);
        mainSplitPane.setResizeWeight(0.3); // Adjust weight as needed (0 for fixed size)

        // Main frame layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainSplitPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }


}