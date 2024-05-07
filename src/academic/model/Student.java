package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 */
public class Student extends Person{
    
    private int year;
    private String major;

    // constructor
    public Student(String id, String _name, int _year, String _major) {
        super(id, _name);
        this.year = _year;
        this.major = _major;
    }

    // setter dan getter

    public String getName() {
        return this.name;
    }


    // toString

    public String toString() {
        return this.id + "|" + this.name + "|" + this.year + "|" + this.major;
    }

}
