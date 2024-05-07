package academic.driver;

import java.util.*;

import academic.model.Course;
import academic.model.CourseOpening;
import academic.model.Enrollment;
import academic.model.Lecturer;
import academic.model.Student;
import academic.model.BestStudent;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */

public class Driver1 {

    // Method untuk menghitung indeks berdasarkan grade
    private static double calculateIndeks(String grade) {
        double indeks = 0.0;
        if (grade.equals("A")){
            indeks = 4.0;
        } else if (grade.equals("AB")){
            indeks = 3.5;
        } else if (grade.equals("B")){
            indeks = 3.0;
        }else if (grade.equals("BC")){
            indeks = 2.5;
        }else if (grade.equals("C")){
            indeks = 2.0;
        }else if (grade.equals("D")){
            indeks = 1.5;
        }else if (grade.equals("E")){
            indeks = 1.0;
        }
        
        return indeks;
    }

    // Method untuk menghitung total SKS dan hasil
    private static void calculateSKSandResult(ArrayList<Course> courses, ArrayList<Enrollment> enrollments,
                                              String studentNIM, double[] sksAndResult) {
        double Indeks = 0.00, beIndeks = 0.00;
        int Sks = 0;
        double result = 0.00;
        String idCourse = "";

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCode().equals(studentNIM)) {
                Indeks = calculateIndeks(enrollment.getGrade());
                for (Course course : courses) {
                    if (enrollment.getNim().equals(course.getCode()) && Indeks > 0.00) {
                        if (idCourse.equals(course.getCode())) {
                            int Kredit = course.getCredit();
                            Sks -= Kredit;
                            result -= (beIndeks * Kredit);
                        }
                        int Kredit = course.getCredit();
                        Sks += Kredit;
                        result += (Indeks * Kredit);
                        idCourse = course.getCode();
                        beIndeks = Indeks;
                        break;
                    }
                }
            }
        }

        sksAndResult[0] = Sks;
        sksAndResult[1] = result;
    }

    private static void addRemedialGrade(ArrayList<Enrollment> enrollments, String studentNIM, String courseCode, String year, String grade) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getNim().equals(studentNIM) && enrollment.getCode().equals(courseCode)
                    && enrollment.getYear().equals(year) && !enrollment.getGrade().equals("None")) {
                if (enrollment.getBefore().isEmpty()) {
                    String nilaiRemed = enrollment.getGrade();
                    enrollment.setGrade(grade);
                    enrollment.setBefore(nilaiRemed);
                    
                }
            }
        }
    }

    public static void main(String[] _args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<Enrollment> matkulMahasiswa = new ArrayList<>();
        ArrayList<CourseOpening> courseOpenings = new ArrayList<>();
        ArrayList<BestStudent> BestStudent = new ArrayList<>();

        while (scanner.hasNextLine()){
            String input = scanner.nextLine();

            if (input.equals("---")){

                for (Lecturer lecturer : lecturers){
                    System.out.println(lecturer);
                }

                for (Course course : courses){
                    System.out.println(course);
                }
                
                // print student 
                for (Student student : students){
                    System.out.println(student);
                }

                for (Enrollment enrollment : enrollments){
                     System.out.println(enrollment);
                }

                //for (BestStudent b : BestStudent){
                    //System.out.println(b);
                //}

                scanner.close();

                break;
            }

            String[] tokens = input.split("#");
            if (tokens[0].equals("course-add")){

                boolean found = false;
                for (Course course : courses) {
                    if (course.getCode().equals(tokens[1])) {
                        found = true; 
                        break;
                    }
                }
                if (!found) {
                    Course course = new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                    courses.add(course);
                    }


            } else if (tokens[0].equals("student-add")){

                boolean founded = false;
                for (Student student : students) {
                    if (student.getId().equals(tokens[1])) {
                        founded = true;
                        break;
                    }
                }
                if (!founded) {
                    Student student = new Student(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                    students.add(student);
                }

            } else if (tokens[0].equals("enrollment-add")){

                String grade = "None";
                Enrollment newEnrollment = new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], grade);
                enrollments.add(newEnrollment);
                matkulMahasiswa.add(newEnrollment);


            } else if (tokens[0].equals("lecturer-add")){
                boolean found = false;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getId().equals(tokens[1])) {
                        found = true;
                        break;
                    }
                }
                
                if (!found) {
                    Lecturer lecturer = new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                    lecturers.add(lecturer);
                }
                
            }  else if (tokens[0].equals("enrollment-grade"))   {

            
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getNim().equals(tokens[1]) && enrollment.getCode().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4]) ) {
                        enrollment.setGrade(tokens[5]);
                        break;
                    } 
                }
                for (Enrollment enrollment : matkulMahasiswa) {
                    if (enrollment.getNim().equals(tokens[1]) && enrollment.getCode().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4]) ) {
                        enrollment.setGrade(tokens[5]);
                        break;
                    }
                }

            

            } else if (tokens[0].equals("student-details")) {
                double[] sksAndResult = new double[2];
                calculateSKSandResult(courses, enrollments, tokens[1], sksAndResult);
                int Sks = (int) sksAndResult[0];
                double result = sksAndResult[1];
    
                boolean cek = false;
                for (Student student : students) {
                    if (student.getId().equals(tokens[1])) {
                        cek = true;
                    }
                    if (cek) {
                        double Indeks = (Sks == 0) ? Double.parseDouble(tokens[3]) : result / Sks;
                        System.out.println(student + "|" + String.format("%.2f", Indeks) + "|" + Sks);
                        break;
                    }
                }
            

            } else if (tokens[0].equals("enrollment-remedial")){

                for (Enrollment enrollment : enrollments){

                    if (enrollment.getNim().equals(tokens[1]) && enrollment.getCode().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4] )&&enrollment.getGrade() != "None"){
                       
                        if (enrollment.getBefore() == "" ){
                    
                            enrollment.setBefore(enrollment.getGrade());

                            enrollment.setGrade(tokens[5]);
                            break;
                        }

                        addRemedialGrade(enrollments, tokens[1], tokens[2], tokens[3], tokens[5]);
                        
                        break;
                    }
                    
                }
                
            } else if (tokens[0].equals("course-open")){
                
                    for (int i = 0; i < lecturers.size(); i++){
                        for (Lecturer lecturer : lecturers){
                            if (lecturer.getInitial().equals(tokens[4])){
                                
                                tokens[4] =  tokens [4] +   " (" + lecturer.getEmail() + ")";

                            }
                        }
                    }
                    CourseOpening courseOpening = new CourseOpening(tokens[1], tokens[2], tokens[3], tokens[4]);
                    courseOpenings.add(courseOpening);
                

            } else if (tokens[0].equals("course-history")){

                ArrayList<CourseOpening> oddSemesters = new ArrayList<>();
                ArrayList<CourseOpening> evenSemesters = new ArrayList<>();
            
                for (CourseOpening courseOpening : courseOpenings){
                    if (courseOpening.getSemester().equals("odd")) {
                        oddSemesters.add(courseOpening);
                    } else {
                        evenSemesters.add(courseOpening);
                    }
                }

                Collections.sort(oddSemesters, Comparator.comparing(CourseOpening::getSemester));
                Collections.sort(evenSemesters, Comparator.comparing(CourseOpening::getSemester));


                ArrayList<CourseOpening> sortedSemesters = new ArrayList<>();
                
                sortedSemesters.addAll(oddSemesters);
                sortedSemesters.addAll(evenSemesters);
                


                for (CourseOpening courseOpening : sortedSemesters){
                    
                    for (Course course : courses){

                        if (course.getCode().equals(tokens[1])){

                            System.out.println(course + "|" + courseOpening.getAcademicYear() + "|" + courseOpening.getSemester() + "|" + courseOpening.getLecturer());
                            
                            for (Enrollment enrollment : enrollments){

                                if (courseOpening.getCode().equals(tokens[1])  && courseOpening.getAcademicYear().equals(enrollment.getYear())  && courseOpening.getSemester().equals(enrollment.getSemester())){
                                    System.out.println(enrollment);
                                }
                               
                        }
                    }
                }

            }

        } else if (tokens[0].equals("find-the-best-student")){
            Double bestgrade = 0.00;
            String nim = "";
            String grade = "";
            boolean test = false;

            for (Enrollment enrollment : enrollments){

                //System.out.println("Helloworld");

                if (enrollment.getYear().equals(tokens[1]) && enrollment.getSemester().equals(tokens[2])){
                    String[] devided  = enrollment.getCode().split("00");

                    int takegrade =  Integer.parseInt(devided[1]);

                    int p = takegrade % 2;

                    if (p == 0){
                        test = true;
                    }
                    

                    System.out.println(takegrade);

                    if (bestgrade < calculateIndeks(enrollment.getGrade())){


                        if (test){
                            bestgrade = calculateIndeks(enrollment.getGrade());
                            grade = enrollment.getGrade();
                        }

                        
                        nim = enrollment.getCode();
                            
                    }

                }

                    
            }

            BestStudent bestStudent = new BestStudent(nim, grade);
            BestStudent.add(bestStudent);

            //System.out.println(nim +"|"+ bestgrade);


        } else if (tokens[0].equals("add-best-student")) {

        }
    
    }
}
}
    