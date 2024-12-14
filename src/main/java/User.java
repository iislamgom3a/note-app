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
    private static String  FILE_NAME = "hashmap.txt";

    public static boolean register (String userName , String password)throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME));
            @SuppressWarnings("unchecked")
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

    public boolean logIn(String username, String password) throws ClassNotFoundException {
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
    

    
}
