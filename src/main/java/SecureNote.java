import java.util.HashMap;

public class SecureNote extends Note{
    private String password;
    private boolean unlocked;
    public SecureNote( String title) {
        super(title);
    }
    // method: ceate note:
    // title, password
    public String setPassword(String password){
        return password;
    }
public static boolean isPasswordValid(String password) {
    String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    return password != null && password.matches(passwordPattern);
    }
    public void saveTitleAndPassword(String title , String password) throws Exception {
        HashMap<String,String> titlesAndPasswords = User.readHashMapFromFile();
        String hashPassword = User.hashPassword(password);
        if (titlesAndPasswords.containsKey(title)) {
            throw new Exception("Username already exists.");
        }
        if (!User.isPasswordValid(password)) {
            throw new WeakPasswordException("Password is too weak! It must be at least 8 characters long, contain both upper and lower case letters, a number, and a special character.");
        }
        String hashedPassword = User.hashPassword(password);
        titlesAndPasswords.put(title, hashedPassword);
        User.writeHashMapToFile(titlesAndPasswords);
    }

}
