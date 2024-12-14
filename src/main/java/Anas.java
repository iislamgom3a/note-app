import java.io.*;
import java.util.HashMap;

public class Anas {

    private static final String FILE_NAME = "Anas.txt";

    public static void main(String[] args) throws Exception{
        
        System.out.println(logIn("Anas", "1234"));
    }

    // Method to load the HashMap from a file
    // private static HashMap<String, String> loadHashMapFromFile() {
    //     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream( "hashmap1.txt"))) {
    //         return (HashMap<String, String>) ois.readObject();
    //     } catch (IOException | ClassNotFoundException e) {
    //         System.out.println("Error loading HashMap (or file not found). Starting with an empty map.");
    //         return new HashMap<>();
    //     }
    // }

    // Method to save the HashMap to a file
    // private static void saveHashMapToFile(HashMap<String, String> map) {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream( "hashmap1.txt"))) {
    //         oos.writeObject(map);
    //     } catch (IOException e) {
    //         System.out.println("Error saving HashMap: " + e.getMessage());
    //     }
    // }

    // Method to add an entry to the HashMap
    // private static void addEntryToHashMap(HashMap<String, String> map, String key, String value) {
    //     if (map.containsKey(key)) {
    //         System.out.println("Key '" + key + "' already exists. Updating its value.");
    //     } else {
    //         System.out.println("Adding new key-value pair: " + key + " -> " + value);
    //     }
    //     map.put(key, value);
    // }
    public static boolean register (String userName , String password)throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            HashMap <String,String> map1 = (HashMap<String, String>) ois.readObject();
            if (!map1.containsKey(userName)) {
                map1.put(userName, password);
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
                oos.writeObject(map1);
                oos.close();
                ois.close();
                return true;
            }
            else{
                System.out.println("Username already exists");
                ois.close();
                return false;
            }

         
    }
    public static boolean logIn(String username, String password) throws ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            @SuppressWarnings("unchecked")
            HashMap<String, String> map1 = (HashMap<String, String>) stream.readObject();
            if (map1.containsKey(username)){
                return map1.get(username).equals(password);
            }
            else{
                return false;
            }
        } catch (IOException e) {
            System.out.println("Faild " + e.getMessage());
            return false;
        }
    }
    // private static void saveHashMapToFile(HashMap<String, String> map) {
    //     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
    //         oos.writeObject(map);
    //     } catch (IOException e) {
    //         System.out.println("Error saving HashMap: " + e.getMessage());
    //     }
    // }
    // private static HashMap<String, String> loadHashMapFromFile() {
    //     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
    //         return (HashMap<String, String>) ois.readObject();
    //     } catch (IOException | ClassNotFoundException e) {
    //         System.out.println("Error loading HashMap (or file not found). Starting with an empty map.");
    //         return new HashMap<>();
    //     }
    // }
}
