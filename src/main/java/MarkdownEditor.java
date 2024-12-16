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

public class MarkdownEditor extends JFrame {
    
    private final JTextArea textArea;
    private final JEditorPane previewPane;
    private boolean isRawMode = true; // Toggle state
    private final JPanel cardPanel;
    private final CardLayout cardLayout;

    public MarkdownEditor() {
        setTitle("Markdown Editor");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the text area for raw markdown
        textArea = new JTextArea();
        textArea.setEditable(true); // Ensure the text area is editable
        JScrollPane textScrollPane = new JScrollPane(textArea);

        // Create the preview pane
        previewPane = new JEditorPane();
        previewPane.setContentType("text/html");        
        previewPane.setEditable(false); // Read-only for preview
        JScrollPane previewScrollPane = new JScrollPane(previewPane);

        // Create a CardLayout panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add text area and preview pane to cardPanel
        cardPanel.add(textScrollPane, "Raw");
        cardPanel.add(previewScrollPane, "Preview");

        // Toggle button
        JButton toggleButton = new JButton("Switch to Preview Mode");
        toggleButton.addActionListener(e -> toggleMode(toggleButton));

        // Auto-save listener
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

        // Layout
        setLayout(new BorderLayout());
        add(cardPanel, BorderLayout.CENTER);
        add(toggleButton, BorderLayout.SOUTH);

        // Initialize the preview pane with default content
        updatePreview();
    }

    // Save markdown to a file
    // to do: pass a path for the function to save the file at the user's folder
    private void saveToMarkdownFile() {
        String content = textArea.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("note.md"))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update preview content by converting Markdown to HTML
    private void updatePreview() {
        String rawMarkdown = textArea.getText();
        if (rawMarkdown.isEmpty()) {
            previewPane.setText("<html><body><p>No content to display.</p></body></html>");
        } else {
            try {
                String htmlContent = convertMarkdownToHtml(rawMarkdown);
                previewPane.setText(htmlContent); // Render HTML content
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Convert Markdown to HTML using CommonMark
    private String convertMarkdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        return renderer.render(document); // HTML output
    }

    // Toggle between raw and preview mode
    private void toggleMode(JButton toggleButton) {
        isRawMode = !isRawMode;

        // Switch between the two cards (Raw and Preview)
        cardLayout.show(cardPanel, isRawMode ? "Raw" : "Preview");

        toggleButton.setText(isRawMode ? "Switch to Preview Mode" : "Switch to Raw Mode");

        if (!isRawMode) {
            updatePreview(); // Update preview when switching
        }
    }
}
