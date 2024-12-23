import java.io.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

class SecureNote extends Note {
    private boolean unlocked;
    private String title;

    public SecureNote(String title) {
        super(title);
        this.title = title;
        this.unlocked = false;
    }

    public void createNote(String userName, String password) throws Exception {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }

        String userFolderPath = User.USERs_FOLDER_PATH + File.separator + userName;
        File userDir = new File(userFolderPath);

        // Ensure user directory exists
        if (!userDir.exists()) {
            userDir.mkdirs();
        }

        File titlesFile = new File(userFolderPath, "TitlesAndPasswords.txt");
        HashMap<String, String> titlesAndPasswords = readTitlesAndPasswords(titlesFile.getPath());

        if (titlesAndPasswords.containsKey(title)) {
            throw new Exception("Note title already exists.");
        }

        titlesAndPasswords.put(title, User.hashPassword(password));
        writeTitlesAndPasswords(titlesFile.getPath(), titlesAndPasswords);

        try (FileWriter writer = new FileWriter(new File(userFolderPath, title + ".md"))) {
            writer.write(""); // Optional initial content
        } catch (IOException e) {
            throw new RuntimeException("Error creating secure note file: " + e.getMessage());
        }
    }


    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock(String userName, String password) throws Exception {
        String userFolderPath = User.USERs_FOLDER_PATH + File.separator + userName;
        String filePath = userFolderPath + File.separator + "TitlesAndPasswords.txt";
        HashMap<String, String> titlesAndPasswords = readTitlesAndPasswords(filePath);

        if (!titlesAndPasswords.containsKey(title)) {
            throw new Exception("Note not found.");
        }

        String storedHash = titlesAndPasswords.get(title);
        if (storedHash.equals(User.hashPassword(password))) {
            unlocked = true;
        } else {
            throw new Exception("Invalid password.");
        }
    }

    public void lock() {
        this.unlocked = false;
    }

    private HashMap<String, String> readTitlesAndPasswords(String filePath) {
        HashMap<String, String> map = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            map = (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Titles and passwords file not found. A new one will be created.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading titles and passwords file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
    }

    private void writeTitlesAndPasswords(String filePath, HashMap<String, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
            System.out.println("Titles and passwords updated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to titles and passwords file: " + e.getMessage());
        }
    }
}
