import java.util.List;

public class Note {
    private int noteID;
    private String title;
    // Constructor with parameters
    public Note(int noteID, String title) {
        this.noteID = noteID;
        this.title = title;
    }

    // Getter for noteID
    public int getNoteID() {
        return noteID;
    }

    // Setter for noteID
    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }


}
