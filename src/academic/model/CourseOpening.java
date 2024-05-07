package academic.model;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */
public class CourseOpening {

    private String code;
    private String academicYear;
    private String semester;
    private String Lecturer;
    
    //constructor
    public CourseOpening(String code, String academicYear, String semester, String Lecturer) {
        this.code = code;
        this.academicYear = academicYear;
        this.semester = semester;
        this.Lecturer = Lecturer;


        // //ini lanjut 100
        // // for (int i = 0; i < Lecturer.length; i++){
        // //     // di dalam array buat semua menjadi satu string dalam pisah1
        // //     if (i == 0){
        // //         this.Lecturer = Lecturer[i];
        // //     } else {
        // //         this.Lecturer = this.Lecturer + ";" + Lecturer[i];
        // //     }


        // }
    }

    //getter
    public String getCode() {
        return code;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public String getSemester() {
        return semester;
    }
    public String getLecturer() {
        return Lecturer;
    }

    @Override
    public String toString() {
        return  this.code + "|" + this.academicYear + "|" + this.semester + "|" + this.Lecturer;
    }

    
}