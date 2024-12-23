import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;

public class Image {

    /**
     * Opens a file chooser to select an image, copies the image to the specified directory,
     * and returns the paths for the saved image and markdown file.
     *
     * @param saveDirectoryPath The directory path where the image should be saved.
     * @return An array containing the image path and markdown file path, or null if no image is selected.
     */
    public String[] selectImage(String saveDirectoryPath, String noteTitle) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image Files (jpg, png, gif, bmp)", "jpg", "png", "gif", "bmp");
        fileChooser.setFileFilter(imageFilter);

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            File destinationDir = new File(saveDirectoryPath);
            if (!destinationDir.exists() && !destinationDir.mkdirs()) {
                JOptionPane.showMessageDialog(null, "Failed to create the specified directory.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            File savedFile = new File(destinationDir, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Image saved to: " + savedFile.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);

                // Markdown file path
                File markdownFile = new File(destinationDir, noteTitle+".md");

                return new String[]{savedFile.getAbsolutePath(), markdownFile.getAbsolutePath()};
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error copying file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No image selected.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    }

    /**
     * Adds an image path to a Markdown file.
     *
     * @param imagePath    The path of the image to add.
     * @param markdownPath The path of the markdown file to write to.
     * @return True if successful, false otherwise.
     */
    public boolean addImage(String imagePath, String markdownPath) {
        if (imagePath == null || imagePath.isEmpty() || markdownPath == null || markdownPath.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid image or markdown path.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            // Check if the image path already exists in the Markdown file
            File markdownFile = new File(markdownPath);
            if (markdownFile.exists()) {
                String content = Files.readString(markdownFile.toPath());
                if (content.contains(imagePath)) {
                    JOptionPane.showMessageDialog(null, "Image path already exists in the Markdown file.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }

            // Append the image path to the Markdown file
            try (FileWriter writer = new FileWriter(markdownPath, true)) {
                writer.write("![Image](" + imagePath + ")\n");
                JOptionPane.showMessageDialog(null, "Image path added to Markdown file: " + markdownPath, "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to Markdown file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Test the class functionality
    public static void main(String[] args) {

    }
}
