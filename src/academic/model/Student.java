package academic.model;

//package inheritance;
/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */
public class Student extends Person {

    // nim, nama, angkatan, dan prodi
    private int year;

    // constructor
    public Student(String _nim, String _name, int _year, String _studyProgram) {
        this.nim = _nim;
        this.name = _name;
        this.year = _year;
        this.studyProgram = _studyProgram;
    }



    // toString

    public String toString() {
        return this.nim + "|" + this.name + "|" + this.year + "|" + this.studyProgram;
    }

}
