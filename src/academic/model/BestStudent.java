package academic.model;

public class BestStudent {

    private String nim;
    private String grade;
    
    //constructor
    public BestStudent(String nim, String grade) {
        this.nim = nim;
        this.grade = grade;

    }

    //getter
    public String getNim() {
        return nim;
    }
    public String getGrade() {
        return grade;
    }


    @Override
    public String toString() {
        return  this.nim + "|" + this.grade ;
    }

    
}
