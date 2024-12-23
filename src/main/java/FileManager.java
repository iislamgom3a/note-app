import java.io.*;

public class FileManager {
    public static void saveToFile(String filePath, Object data) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(data);
        }
    }

    public static Object loadFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return file.createNewFile();
        }
        return false;
    }
}
