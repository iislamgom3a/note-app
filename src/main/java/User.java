import java.io.*;
import java.util.HashMap;
import java.util.List;

public class User {
    private static String userName;
    private String passWord;
    private List<Note> notes;
    private static final String  FILE_NAME = "DataBase.txt";

    public String register(String userName,String password){
        HashMap<String,String> map1 = (HashMap<String, String>) readHashMapFromFile();
        String folderPath = "Users\\"+userName;
        if (!map1.containsKey(userName)){
            map1.put(userName,password);
            writeHashMapToFile(map1);
            File folder = new File(folderPath);
            if (folder.mkdir()) {
                return folder.getAbsolutePath();
            }
        }
        System.out.println("Username already exists");
        return null;
    }

    private static void writeHashMapToFile(HashMap<String, String> map) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.FILE_NAME))) {
            oos.writeObject(map);
            System.out.println("HashMap has been serialized and written to " + User.FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error serializing HashMap to file: " + e.getMessage());
        }
    }

    private static HashMap<String, String> readHashMapFromFile() {
        HashMap<String, String> map = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(User.FILE_NAME))) {
            map = (HashMap<String, String>) ois.readObject();
            System.out.println("HashMap has been deserialized from " + User.FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing HashMap from file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
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

    
}
