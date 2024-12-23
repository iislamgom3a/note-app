import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Image {

    /**
     * Opens a file chooser to select an image, copies the image to the specified directory,
     * and returns the paths for the saved image and markdown file.
     *
     * @param saveDirectoryPath The directory path where the image should be saved.
     * @return An array containing the image path and markdown file path, or null if no image is selected.
     */
    public String[] selectImage(String saveDirectoryPath) {
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
                System.out.println("Failed to create the specified directory.");
                return null;
            }

            File savedFile = new File(destinationDir, selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image saved to: " + savedFile.getAbsolutePath());

                // Markdown file path
                File markdownFile = new File(destinationDir, "imagePaths.md");

                return new String[]{savedFile.getAbsolutePath(), markdownFile.getAbsolutePath()};
            } catch (IOException e) {
                System.out.println("Error copying file: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("No image selected.");
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
            System.out.println("Invalid image or markdown path.");
            return false;
        }

        try (FileWriter writer = new FileWriter(markdownPath, true)) {
            writer.write("![Image](" + imagePath + ")\n");
            System.out.println("Image path added to markdown file: " + markdownPath);
            return true;
        } catch (IOException e) {
            System.out.println("Error writing to markdown file: " + e.getMessage());
            return false;
        }
    }

    // Test the class functionality
    public static void main(String[] args) {
        Image handler = new Image();
        String saveDirectoryPath = System.getProperty("P:\\codeRepo") + "/CustomImages"; // Example user-specified path
        String[] paths = handler.selectImage(saveDirectoryPath);

        if (paths != null && paths.length == 2) {
            String imagePath = paths[0];
            String markdownPath = paths[1];
            handler.addImage(imagePath, markdownPath);
        }
    }
}
