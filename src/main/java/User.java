import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Custom exception for password mismatch
class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

// Custom exception for weak passwords
class WeakPasswordException extends Exception {
    public WeakPasswordException(String message) {
        super(message);
    }
}

// User class with Serializable for storing user data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String userName;
    protected List<Note> notes = new ArrayList<>();

    static final String FILE_NAME = "DataBase.txt";
    protected static final String USERs_FOLDER_PATH = "P:\\codeRepo\\noteTakingApp\\Users";

    @Override
    public String toString() {
        return "User{" + "userName='" + userName + '\'' + ", notes=" + notes + '}';
    }

    // Register a new user
    public static void register(String userName, String password, String password1) throws Exception {
        HashMap<String, String> userDatabase = readHashMapFromFile();

        // Check if username already exists
        if (userDatabase.containsKey(userName)) {
            throw new Exception("Username already exists.");
        }

        // Check if passwords match
        if (!password.equals(password1)) {
            throw new PasswordException("Passwords do not match!");
        }

        // Check if password is valid
        if (isPasswordValid(password)) {
            throw new WeakPasswordException("Password is too weak! It must be at least 8 characters long, contain both upper and lower case letters, a number, and a special character.");
        }

        // Hash the password and save it
        String hashedPassword = hashPassword(password);
        userDatabase.put(userName, hashedPassword);
        writeHashMapToFile(userDatabase);

        // Create user folder
        File usersFolder = new File(USERs_FOLDER_PATH);
        if (!usersFolder.exists() && !usersFolder.mkdirs()) {
            throw new Exception("Failed to create Users folder.");
        }

        String userFolderPath = USERs_FOLDER_PATH + File.separator + userName;
        File userFolder = new File(userFolderPath);
        if (userFolder.mkdir()) {
            String FilePath = "Users\\"+userName+"\\TitlesAndPasswords.txt";
            File file = new File(FilePath);
            if (file.createNewFile()){}
            return userFolder.getAbsolutePath();
            String filePath = userFolderPath + File.separator + "TitlesAndPasswords.txt";
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                throw new Exception("Failed to create TitlesAndPasswords file.");
            }

        } else {
            throw new Exception("Failed to create user folder.");
        }
    }

    // Log in an existing user
    public static void logIn(String userName, String password) throws Exception {
        if (userName == null || userName.isEmpty()) {
            throw new Exception("Username cannot be empty.");
        }

        if (password == null || password.isEmpty()) {
            throw new Exception("Password cannot be empty.");
        }

        HashMap<String, String> userDatabase = readHashMapFromFile();

        if (userDatabase.containsKey(userName)) {
            String storedHash = userDatabase.get(userName);
            if (storedHash.equals(hashPassword(password))) {
                return;
            } else {
                throw new Exception("Invalid credentials.");
            }
        }
        throw new Exception("User not found.");
    }

    // Read the user database from file
    protected static HashMap<String, String> readHashMapFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("Database file is empty or missing. Initializing new user database.");
            return new HashMap<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (HashMap<String, String>) ois.readObject();
        } catch (EOFException e) {
            System.err.println("Database file is corrupted or incomplete. Reinitializing new user database.");
            initializeDatabaseFile(); // Reinitialize the file
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading database file: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Write the user database to file
    protected static void writeHashMapToFile(HashMap<String, String> map) {
        try {
            FileManager.saveToFile(FILE_NAME, map);
            System.out.println("Database updated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to database file: " + e.getMessage());
        }
    }

    // Validate password strength
    public static boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password == null || !password.matches(passwordPattern);
    }

    // Hash the password using SHA-256
    public static String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Initialize the database file
    protected static void initializeDatabaseFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                FileManager.saveToFile(FILE_NAME, new HashMap<String, String>());
                System.out.println("Initialized database file.");
            } catch (IOException e) {
                System.err.println("Failed to initialize database file: " + e.getMessage());
            }
        }
    }

    // Get the username of the logged-in user
    public String getUserName() {
        return userName;
    }
}
