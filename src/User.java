import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class User {
    private String userName;
    private String passWord;
    private List<Note> notes;
    HashMap<String, String> map = new HashMap<>();

    public boolean register(String username, String password) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("hashmap.txt"))) {
            map.put(username, password);
            stream.writeObject(map);
            return true;
        } catch (IOException e) {
            System.out.println("Failed" + e.getMessage());
            return false;
        }
    }

    public boolean logIn(String username, String password) throws ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("hashmap.txt"))) {
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

    
}
