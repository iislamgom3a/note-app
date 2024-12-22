//import javax.swing.*;
//import java.io.File;
//
public class Anas {
    public static void main(String[] args) {
        User user = new User();
        user.writeEmptyHashMapToFile("X:\\Programming\\noteTakingApp\\Users");
    }
}

//private static void openFolderInFrame(String userName) {
//    String folderPath = "Users\\" + userName;
//    File folder = new File(folderPath);
//
//    if (folder.exists() && folder.isDirectory()) {
//        JFrame folderFrame = new JFrame("Folder: " + userName);
//        folderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        folderFrame.setSize(500, 400);
//
//        // Get list of files in the folder
//        String[] files = folder.list();
//        if (files == null) files = new String[]{};
//
//        JList<String> fileList = new JList<>(files);
//        JScrollPane scrollPane = new JScrollPane(fileList);
//
//        folderFrame.add(scrollPane);
//        folderFrame.setVisible(true);
//
//    } else {
//        JOptionPane.showMessageDialog(null, "Folder does not exist: " + folderPath);
//    }
//}