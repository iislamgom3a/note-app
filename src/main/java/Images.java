import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.*;

public class Images {

    public static final int SCALE_SMOOTH = 4;

    protected String addImage(String userName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "bmp"));

        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedFilePath = selectedFile.getAbsolutePath();

            // Define the new image path based on the userName
            String newImagePath = User.USERs_FOLDER_PATH+ File.separator+ userName +File.separator+ "image"+ Math.random() + "."+getFileExtension(selectedFile);

            // Copy the image to the new path
            try {
                Files.copy(selectedFile.toPath(), new File(newImagePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
                return null; // If an error occurs, return null
            }

            return newImagePath; // Return the new image path
        }
        return null; // Return null if the user cancels the file chooser
    }

    // Helper method to get the file extension
    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return ""; // Default to empty if no extension
    }
}
