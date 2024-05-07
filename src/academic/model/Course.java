package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */

public class Course {

    private String code;
    private String name;
    private int credits;
    private String grade;
    // private String pisah1;

    // constructor
    public Course(String _code, String _name, int _credits, String _grade) {
        this.code = _code;
        this.name = _name;
        this.credits = _credits;
        this.grade = _grade;

    }

    // getter and setter
    public String getCode() {
        return this.code;
    }

    public Integer getCredit() {
        return this.credits;
    }


    public void setCredit(int credit){
        this.credits = credit;
    }

    // toString
    public String toString() {
        return this.code + "|" + this.name + "|" + this.credits + "|" + this.grade;
    }

}