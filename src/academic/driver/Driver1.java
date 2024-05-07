package academic.driver;

/**
 * @author 12S22014 Kezia Hutagaol
 * @author 12S22034 Mulyadi Siahaan
 */
import java.util.*;
import academic.model.*;

public class Driver1 {

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

    public static void main(String[] _args) {
        Scanner masukan = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Enrollment> enrollments = new ArrayList<>();
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ArrayList<CourseOpening<Course>> courseOpenings = new ArrayList<>();
        ArrayList<BestStudent> bestStudents = new ArrayList<>();


        while (masukan.hasNextLine()){
            String input = masukan.nextLine();

            if (input.equals("---")){

                for (Lecturer lecturer : lecturers){
                    System.out.println(lecturer);
                }

                for (Course course : courses){
                    System.out.println(course);
                }
                
                for (Student student : students){
                    System.out.println(student);
                }

                for (Enrollment e : enrollments){
                    System.out.println(e);
                } 

                for (BestStudent a: bestStudents ){
                    System.out.println(a);
                }

                break;
            }
            //SPLIT INPUTAN
            String[] tokens = input.split("#");

            //BANDINGKAN TOKEN 0
            if (tokens[0].equals("course-add")){
                //CEK APAKAH COURSE SUDAH ADA
                boolean found = false;
                for (Course course : courses) {
                    if (course.getCode().equals(tokens[1])) {
                        found = true;
                        break;
                    }
                }
                //JIKA BELUM ADA
                if (!found) {
                    //SPLIT INITIAL
                    //String[] pisah = tokens[5].split(",");
                    //MASUKAN SEMUA INITIAL YANG ADA
                    /*for (int i = 0; i < pisah.length; i++){
                        for (Lecturer lecturer : lecturers){
                            if (lecturer.getInitial().equals(pisah[i])){     
                                pisah[i] = pisah[i] + " (" + lecturer.getEmail() + ")";                        
                            }
                        }
                    }*/
                    Course course = new Course(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                    courses.add(course);
                }

            } else if (tokens[0].equals("student-add")){
                //CEK APAKAH STUDENT SUDAH ADA
                boolean founded = false;
                for (Student student : students) {
                    if (student.getNim().equals(tokens[1])) {
                        founded = true;
                        break;
                    }
                }
                //JIKA BELUM ADA
                if (!founded) {
                    Student student = new Student(tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                    students.add(student);
                }

            } else if (tokens[0].equals("enrollment-add")){
                //CEK APAKAH COURSE DAN STUDENT TERSEDIA
                boolean foundCourse = false;
                boolean foundStudent = false;
                for (Course course : courses) {
                    if (course.getCode().equals(tokens[1])) {
                        foundCourse = true;
                        break;
                    }
                }
                for (Student student : students) {
                    if (student.getNim().equals(tokens[2])) {
                        foundStudent = true;
                        break;
                    }
                }
                //JIKA TERSEDIA COURSE DAN STUDENT
                if (foundCourse && foundStudent) {
                    String grade = "None";
                    Enrollment enrollment = new Enrollment(tokens[1], tokens[2], tokens[3], tokens[4], grade);
                    enrollments.add(enrollment);
                } 

            } else if (tokens[0].equals("lecturer-add")){
                //CEK APAKAH LECTURER SUDAH ADA
                boolean found = false;
                for (Lecturer lecturer : lecturers) {
                    if (lecturer.getNim().equals(tokens[1])) {
                        found = true;
                        break;
                    }
                }
                //JIKA BELUM ADA
                if (!found) {
                    Lecturer lecturer = new Lecturer(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]);
                    lecturers.add(lecturer);
                }
                
            }  else if (tokens[0].equals("enrollment-grade"))   {
                            
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getCode().equals(tokens[1]) && enrollment.getNim().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4])){
                        enrollment.setGrade(tokens[5]);
                        break;
                    }
                }

            } else if (tokens[0].equals("student-details")){

                Double Indeks = 0.00, beIndeks = 0.00;
                Integer Sks = 0;
                Double result = 0.00;
                String idCourse = "";
                boolean cek = false;

                for (Student student : students){
                
                    if (student.getNim().equals(tokens[1])){

                        for (Enrollment enrollment : enrollments){

                            if (enrollment.getNim().equals(student.getNim())){

                                if (enrollment.getGrade().equals("A")){
                                    Indeks = 4.0;
                                } else if (enrollment.getGrade().equals("AB")){
                                    Indeks = 3.5;
                                } else if (enrollment.getGrade().equals("B")){
                                    Indeks = 3.0;
                                }else if (enrollment.getGrade().equals("BC")){
                                    Indeks = 2.5;
                                }else if (enrollment.getGrade().equals("C")){
                                    Indeks = 2.0;
                                }else if (enrollment.getGrade().equals("D")){
                                    Indeks = 1.5;
                                }else if (enrollment.getGrade().equals("E")){
                                    Indeks = 1.0;
                                }

                                for (Course course : courses){

                                    if (enrollment.getCode().equals(course.getCode()) && Indeks > 0.00){

                                        if (idCourse.equals(course.getCode())){
                                        
                                            Integer Kredit = course.getCredit();
                                            Sks = Sks - Kredit;
                                            result =  result - (beIndeks * Kredit);
                                        }

                                        Integer Kredit = course.getCredit();
                                        Sks = Sks + Kredit;
                                        result =  result + (Indeks * Kredit);

                                        idCourse = course.getCode();
                                        beIndeks = Indeks;

                                        break;  
                                    } 
                                }
                            }
                        }
                    }
                }
                
                for (Student student : students) {
                        
                    if (student.getNim().equals(tokens[1])){
                        cek = true;
                    }

                    if (cek){
                        if (Sks == 0){
                            System.out.println(student +"|"+ String.format("%.2f", Indeks) +"|"+ Sks);
                            break;
                        } else {
                            System.out.println(student +"|"+ String.format("%.2f", result/Sks) +"|"+ Sks);
                            break;
                        }
                    }
                }
        
            } else if (tokens[0].equals("enrollment-remedial")){
      
                for (Enrollment enrollment : enrollments){
                    if (enrollment.getCode().equals(tokens[1]) && enrollment.getNim().equals(tokens[2]) && enrollment.getYear().equals(tokens[3]) && enrollment.getSemester().equals(tokens[4])&&enrollment.getGrade() != "None" ){
                        if (enrollment.getNilaiRemed() == "") {
                            String nilaiRemed = enrollment.getGrade();
                            enrollment.setGrade(tokens[5]);
                            enrollment.setNilaiRemed(nilaiRemed);     
                        }         
                    }
                }
            } else if (tokens[0].equals("course-open")){

                String IE="";

                //course-open#12S1101#2020/2021#odd#IUS
                for (Course course : courses){
                    if (course.getCode().equals(tokens[1])){

                        //
                        for (Lecturer lecturer : lecturers){
                            if (lecturer.getInitial().equals(tokens[4])){
                                //System.out.println(course + "|" + tokens[2] + "|" + tokens[3] + "|" + lecturer.getInitial() + " (" + lecturer.getEmail() + ")");
                                //print lalu tambahkan ke courseOpening
                                IE =  lecturer.getInitial() + " (" + lecturer.getEmail() + ")";

                                break;
                            }
                        }
                        CourseOpening<Course> courseOpening = new CourseOpening<>(course, tokens[2], tokens[3], IE);
                        courseOpenings.add(courseOpening);
                        //break;
                    }

                }

            } else if (tokens[0].equals("course-history")){
                // Urutkan ArrayList berdasarkan semester
                Collections.sort(courseOpenings, new Comparator<CourseOpening<Course>>() {
                    @Override
                    public int compare(CourseOpening<Course> co1, CourseOpening<Course> co2) {
                        // Ambil angka dari semester (misalnya "odd" menjadi 1, "even" menjadi 2)
                        int semester1 = co1.getSemester().equalsIgnoreCase("odd") ? 1 : 2;
                        int semester2 = co2.getSemester().equalsIgnoreCase("odd") ? 1 : 2;

                        // Bandingkan angka semester untuk mendapatkan urutan yang diinginkan
                        return Integer.compare(semester1, semester2);
                    }
                });

                
                //print course opening
                for (CourseOpening<Course> a : courseOpenings){
                    System.out.println(a);

                    for (Enrollment e : enrollments){
                        if (e.getYear().equals(a.getYear()) && e.getSemester().equals(a.getSemester())){
                            System.out.println(e);
                        }
                    }
                    
                }

            } else if (tokens[0].equals("find-the-best-student")){
                Double bestgrade = 0.00;
                String nim = "";
                String grade = "";
                boolean test = false;

                Double othergrade = 0.00;
                String anothergrade = "";
    
                for (Enrollment enrollment : enrollments){
    
                    //System.out.println("Helloworld");
    
                    if (enrollment.getYear().equals(tokens[1]) && enrollment.getSemester().equals(tokens[2])){
                        String[] devided  = enrollment.getNim().split("00");
    
                        int takegrade =  Integer.parseInt(devided[1]);
    
                        int p = takegrade % 2;
    
                        if (p == 0){
                            test = true;
                        }
                        
    
                        //System.out.println(takegrade);
    
                        if (bestgrade < calculateIndeks(enrollment.getGrade())){
    
    
                            if (test){
                                bestgrade = calculateIndeks(enrollment.getGrade());
                                grade = enrollment.getGrade();
                            }
    
                            
                            nim = enrollment.getNim();
                                
                        }
    
                    } else if (enrollment.getYear().equals(tokens[1])){
                        String[] devided  = enrollment.getNim().split("00");
    
                        int takegrade =  Integer.parseInt(devided[1]);
    
                        int p = takegrade % 2;
    
                        if (p == 0){
                            test = true;
                        }
                        
    
                        //System.out.println(takegrade);
    
                        if (bestgrade < calculateIndeks(enrollment.getGrade())){
    
    
                            if (test){
                                othergrade = calculateIndeks(enrollment.getGrade());
                                anothergrade = enrollment.getGrade();
                            }
                                
                        }

                    }
    
                        
                }

                if (tokens[2].equals("odd")){
                    grade = grade +"/"+ anothergrade;
                    BestStudent bestStudent = new BestStudent(nim, grade);
                    bestStudents.add(bestStudent);

                } else if (tokens[2].equals("even")){
                    anothergrade = anothergrade +"/"+ grade;
                    BestStudent bestStudent = new BestStudent(nim, anothergrade);
                    bestStudents.add(bestStudent);
                }

    
                //System.out.println(nim +"|"+ bestgrade);
    
    
            } else if (tokens[0].equals("add-best-student")) {
    
            }
            
        }
        masukan.close();
    }
}