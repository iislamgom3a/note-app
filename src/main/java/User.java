import java.io.*;
import java.util.HashMap;
import java.util.List;

public class User {
    private static String userName;
    private String passWord;
    private List<Note> notes;
    static final String  FILE_NAME = "DataBase.txt";

    public String register(String userName,String password) throws Exception {
        HashMap<String,String> map1 = new HashMap<>();
        String folderPath = "Users\\"+userName;
        map1 = readHashMapFromFile();
        if (!map1.containsKey(userName)) {
            if (!isValidPassword(password)) {
                throw new Exception("Valid password");
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

    public String logIn(String userName,String password) throws Exception {
        HashMap<String,String> map = readHashMapFromFile();
        if (map.containsKey(userName)){
            if (map.get(userName).equals(password)){
                return "Users\\"+userName;
            }
            else {
                throw new Exception("Wrong password");
            }
        }
        throw new Exception("Username don't exist");
    }

    private static boolean isValidPassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    public  void writeEmptyHashMapToFile() throws Exception {
        HashMap<String, String> emptyMap = new HashMap<>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.FILE_NAME))) {
            oos.writeObject(emptyMap);
            System.out.println("An empty HashMap has been written to " + User.FILE_NAME);
        } catch (IOException e) {
            throw new Exception("Error writing empty HashMap to file: " + e.getMessage(), e);
        }
    }
}
