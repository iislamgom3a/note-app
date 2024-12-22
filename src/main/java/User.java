import java.io.*;
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

    private List<Note> notes;
    static final String  FILE_NAME = "DataBase.txt";
    public String register(String userName, String password, String password1) throws PasswordException, WeakPasswordException {
        HashMap<String, String> map1 = new HashMap<>();
        String folderPath = "Users\\" + userName;
        map1 = readHashMapFromFile();

        if (!map1.containsKey(userName)) {
            // Check if the passwords match
            if (!password.equals(password1)) {
                throw new PasswordException("Passwords do not match!");
            }

            // Check for weak password (example: length < 8, no numbers, etc.)
            if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
                throw new WeakPasswordException("Password is too weak! It must be at least 8 characters long and contain both letters and numbers.");
            }

            map1.put(userName, password);
            writeHashMapToFile(map1);
            File folder = new File(folderPath);
            if (folder.mkdir()) {
                return folder.getAbsolutePath();
            }
        }
        System.out.println("Username already exists");
        return null;
    }

    private static HashMap<String, String> readHashMapFromFile() {
        HashMap<String, String> map = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(User.FILE_NAME))) {
            map = (HashMap<String, String>) ois.readObject();
            System.out.println("HashMap has been deserialized from " + User.FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing HashMap from file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
    }

    private static void writeHashMapToFile(HashMap<String, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.FILE_NAME))) {
            oos.writeObject(map);
            System.out.println("HashMap has been serialized and written to " + User.FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error serializing HashMap to file: " + e.getMessage());
        }
    }

    public String logIn(String userName, String password) throws Exception {
        // Check for empty fields
        if (userName == null || userName.isEmpty()) {
            throw new Exception("Username cannot be empty");
        }
        if (password == null || password.isEmpty()) {
            throw new Exception("Password cannot be empty");
        }

        HashMap<String, String> map = readHashMapFromFile();

        if (map.containsKey(userName)) {
            if (map.get(userName).equals(password)) {
                return "Users\\" + userName;
            } else {
                throw new Exception("Wrong password");
            }
        }
        throw new Exception("Username doesn't exist");
    }




    public void writeEmptyHashMapToFile() {

        HashMap<String, String> emptyHashMap = new HashMap<>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(emptyHashMap);
            System.out.println("Empty HashMap written to file successfully.");
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }

    public static boolean validateAndComparePasswords(String password1, String password2) {
        // Password validation pattern
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

        // Validate that neither password is null
        if (password1 == null || password2 == null) {
            System.out.println("Passwords cannot be null.");
            return false;
        }

        // Validate the first password
        if (!password1.matches(passwordPattern)) {
            System.out.println("First password does not meet security criteria.");
            return false;
        }

        // Validate the second password
        if (!password2.matches(passwordPattern)) {
            System.out.println("Second password does not meet security criteria.");
            return false;
        }

        // Check if both passwords are the same
        if (password1.equals(password2)) {
            System.out.println("Passwords match and are valid.");
            return true;
        } else {
            System.out.println("Passwords do not match.");
            return false;
        }
    }
}
