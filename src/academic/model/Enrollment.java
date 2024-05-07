package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */

public class Enrollment  {

    private String code;
    private String nim;
    private String year;
    private String semester;
    private String grade;
    private String Before;


    // constructor
    public Enrollment(String _nim, String _code, String _year, String _semester, String _grade) {
        this.nim = _nim;
        this.code = _code;
        this.year = _year;
        this.semester = _semester;
        this.grade = _grade;
        this.Before = "";

    }
       


    public String getCode() {
        return this.code;
    }

    public String getNim() {
        return this.nim;
    }

    public String getGrade() {
        return this.grade;
    }
    public String getYear() {
        return this.year;
    }
    public String getSemester() {
        return this.semester;
    }

    public String setGrade(String Grade) {
        return this.grade = Grade;
    }
    public String setBefore(String Before) {
        return this.Before = Before;
    }
    public String getBefore(){
        return this.Before;
    }

    // toString
    public String toString() {
        //percabangan if untuk menset grade, jika grade diambil dua kali maka grade akan berubah menjadi hasil remedial
        if (Before == "") {
            return this.nim + "|" + this.code + "|" + this.year + "|" + this.semester + "|" + this.grade;
        } else {
            return this.nim + "|" + this.code + "|" + this.year + "|" + this.semester + "|" + this.grade + "(" + setBefore(Before) + ")";
        }
    }


}