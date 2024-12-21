import javax.swing.*;

public class GUIManager{

    public static void main(String[] args) {
       LoginFrame loginFrame = new LoginFrame();
        SwingUtilities.invokeLater(() -> {
            loginFrame.setVisible(true);
        });

    }
}



    /*
     - login panel or register if the user is not found

     --- if the user exists: open a panel which the notes at the left and the edit panel at the right
     - when clicking a note it will ask for a password
     - passed: the notes will be shown at the right, and it can be changed from the editing mode to view and vice versa
     - when a note closed it will be locked after a time
     - a button to add new note and asking for its new password

     --- user isn't exits : new user
     - same but there is no old notes there

    */