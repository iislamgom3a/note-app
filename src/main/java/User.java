import java.io.*;
import java.util.HashMap;
import java.util.List;

public class User {
    private static String userName;
    private String passWord;
    private List<Note> notes;
    private static final String  FILE_NAME = "DataBase.txt";

    public String register(String userName,String password){
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (HashMap.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "-->" + entry.getValue());
                writer.newLine();
            }
            System.out.println("HashMap has been written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing HashMap to file: " + e.getMessage());
        }
    }
    private static HashMap<String, String> readHashMapFromFile(String filePath) {
        HashMap<String, String> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-->", 2);
                if (parts.length == 2) {
                    map.put(parts[0], parts[1]);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading HashMap from file: " + e.getMessage());
        }
        return map;
    }


    public  boolean logIn(String userName,String password) throws Exception {
        HashMap<String,String> map = readHashMapFromFile(FILE_NAME);
        if (map.containsKey(userName)){
            if (map.get(userName).equals(password)){
                return true;
            }
            else {
                throw new Exception("Wrong password");
            }
        }
        throw new Exception("Username don't exist");
    }

    
}
