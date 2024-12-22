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
}
