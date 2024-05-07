package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */

public class Lecturer extends Person{
    private String initial;
    private String email;
    private String studyProgram;

    // constructor
    public Lecturer(String id, String _name, String _initial, String _email, String _studyProgram) {
        super(id, _name);
        this.initial = _initial;
        this.email = _email;
        this.studyProgram = _studyProgram;
    }


    public String getInitial() {
        return this.initial;
    }

    public String getEmail() {
        return this.email;
    }

    // toString
    public String toString() {
        return this.id + "|" + this.name + "|" + this.initial + "|" + this.email + "|" + this.studyProgram;
    }
    
}
