import java.io.*;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

class WeakPasswordException extends Exception {
    public WeakPasswordException(String message) {
        super(message);
    }
}

public class User {

    private List<Note> notes; // Assuming Note is another class in your project
    static final String FILE_NAME = "DataBase.txt";
    protected static final String USER_FOLDER_PATH = "P:\\codeRepo\\noteTakingApp\\Users";

    public String register(String userName, String password, String password1) throws PasswordException, WeakPasswordException, Exception {
        HashMap<String, String> userDatabase = readHashMapFromFile();

        if (userDatabase.containsKey(userName)) {
            throw new Exception("Username already exists.");
        }

        if (!password.equals(password1)) {
            throw new PasswordException("Passwords do not match!");
        }

        if (!isPasswordValid(password)) {
            throw new WeakPasswordException("Password is too weak! It must be at least 8 characters long, contain both upper and lower case letters, a number, and a special character.");
        }

        String hashedPassword = hashPassword(password);
        userDatabase.put(userName, hashedPassword);
        writeHashMapToFile(userDatabase);

        String userFolderPath = USER_FOLDER_PATH + File.separator + userName;
        File userFolder = new File(userFolderPath);
        if (userFolder.mkdir()) {
            return userFolder.getAbsolutePath();
        } else {
            throw new Exception("Failed to create user folder.");
        }
    }

    public String logIn(String userName, String password) throws Exception {
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
                return userName;
            } else {
                throw new Exception("Invalid credentials.");
            }
        }
        throw new Exception("User not found.");
    }

    private static HashMap<String, String> readHashMapFromFile() {
        HashMap<String, String> map = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            map = (HashMap<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found. A new one will be created.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading database file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
    }

    private static void writeHashMapToFile(HashMap<String, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(map);
            System.out.println("Database updated successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to database file: " + e.getMessage());
        }
    }

    public static boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

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

    public void writeEmptyHashMapToFile() {
        HashMap<String, String> emptyHashMap = new HashMap<>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(emptyHashMap);
            System.out.println("Empty database created successfully.");
        } catch (IOException e) {
            System.err.println("Error writing empty database file: " + e.getMessage());
        }
    }
}
