import java.util.ArrayList;
import java.util.Scanner;
//main

public class Main {
    public static void main(String[] args) {
        /*Course oop = new Course("Objected-Oriented Programming", 5);
        Person ad = new Professor("Daniyar",23,"Absadykov","Master")
        University aitu = new University("Astana IT University", "Mangilik El Avenue, 55/11");
        Person aza = new Student("Azamat", 18, 1);
        Person ali = new Student("Ali", 23, 2);

        System.out.println(System.identityHashCode(aza));
        System.out.println(ad.getSurname() + " " + ad.getName() + " academic degree is " + ad.getAcademicDegree());
        System.out.println("News: Master of ICT Absadykov Daniyar got Phd degree");
        ad.setAcademicDegree("Phd");
        System.out.println(ad.getSurname() + " " + ad.getName() + " academic degree is " + ad.getAcademicDegree());
        System.out.println("News: Period of finals is started, let's see our students results");
        oop.defense(true, aza);
        oop.grade(87, aza);
        if (aza.getAge() > ali.getAge()) {
            System.out.println(aza.getName() + " is older than " + ali.getName());
        } else if (aza.getAge() < ali.getAge()) {
            System.out.println(ali.getName() + " is older than " + aza.getName());
        } else {
            System.out.println("Students ages is equals");
        }*/
        Course oop = new Course("Objected-oriented programming", 5);
        University aitu = new University("Astana IT University", "Mangilik El Avenue, 55/11");
        Person aza = new Student("Azamat", 18, 1);
        Person ali = new Student("Ali", 20, 3);
        Person ad = new Professor("Daniyar", 35, "Absadykov", "Master");
        aitu.enroll(aza);
        aitu.enroll(ali);
        aitu.enroll(ad);
        if(aza.equals(ali)) System.out.println("true");
        else System.out.println("false");
        System.out.println("Before sort:");
        aitu.printStudents();
        aitu.SortByAge();
        System.out.println("After sort:");
        aitu.printStudents();
        oop.defense(true, (Student) aza);
        oop.grade(95,(Student) aza);
        aitu.PrintALL();
        ((Professor) ad).giveLecture(true);
        ((Professor) ad).givePractice(false);
        ArrayList<Student> firstCourse = aitu.getStudentsByCourse(1);
        System.out.println("Students in course 1:");
        for (Student s : firstCourse) {
            System.out.println(s);
        }

    }
}

