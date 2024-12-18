import java.io.*;
import java.util.HashMap;

public class Anas {
    private static final String  FILE_NAME = "DataBase.txt";
    public static void main(String[] args) throws Exception {
        System.out.println(logIn("anas","000"));
    }
    public static String register(String userName,String password){
        HashMap<String,String> map1 = (HashMap<String, String>) readHashMapFromFile(FILE_NAME);
        String folderPath = "Users\\"+userName;
        if (!map1.containsKey(userName)){
            map1.put(userName,password);
            writeHashMapToFile(map1,FILE_NAME);
            File folder = new File(folderPath);
            if (folder.mkdir()) {
                return folder.getAbsolutePath();
            }
        }
        System.out.println("Username already exists");
        return null;
    }

    private static void writeHashMapToFile(HashMap<String, String> map, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(map);
            System.out.println("HashMap has been serialized and written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error serializing HashMap to file: " + e.getMessage());
        }
    }

    private static HashMap<String, String> readHashMapFromFile(String filePath) {
        HashMap<String, String> map = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            map = (HashMap<String, String>) ois.readObject();
            System.out.println("HashMap has been deserialized from " + filePath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing HashMap from file: " + e.getMessage());
        }
        return map == null ? new HashMap<>() : map;
    }



    public  static String logIn(String userName,String password) throws Exception {
        HashMap<String,String> map = readHashMapFromFile(FILE_NAME);
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