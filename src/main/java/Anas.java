import java.io.*;
import java.util.HashMap;

public class Anas {
    private static final String  FILE_NAME = "DataBase.txt";
    public static void main(String[] args) throws Exception {
        writeEmptyHashMapToFile();
    }

    private static void writeEmptyHashMapToFile() throws Exception {
        HashMap<String, String> emptyMap = new HashMap<>();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(User.FILE_NAME))) {
            oos.writeObject(emptyMap);
            System.out.println("An empty HashMap has been written to " + User.FILE_NAME);
        } catch (IOException e) {
            throw new Exception("Error writing empty HashMap to file: " + e.getMessage(), e);
        }
    }

}