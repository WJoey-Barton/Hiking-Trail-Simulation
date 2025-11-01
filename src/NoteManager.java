//Joey Barton
//This class will hold the logic required to save the Note's to a .txt file

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NoteManager {
    
    private Note note;

    //This constructor allows us to use a single instance for multiple notes
    public NoteManager() {

    }

    public void setNote(Note n) {
        this.note = n;
    }

    public void saveNote(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(note.toString());
        writer.close();
    }

    public void saveHikersReturnedNote(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(note.getName() + "\n");
        writer.close();
    }

    public void clearNotes(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
        writer.write("");
        writer.close();
    }
}
