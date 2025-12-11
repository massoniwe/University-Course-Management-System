import java.util.Scanner;
//main

public class Main {
    public static void main(String[] args) {
        Course oop = new Course("Objected-Oriented Programming", 5);
        Professor ad = new Professor("Daniyar", "Absadykov", "Master");
        University aitu = new University("Astana IT University","Mangilik El Avenue, 55/11");
        Student aza = new Student("Azamat", 18, 1);
        Student ali = new Student("Ali", 20, 3);
        System.out.println(ad.getSurname()+" "+ad.getName()+" academic degree is "+ad.getAcademicDegree());
        System.out.println("News: Master of ICT Absadykov Daniyar got Phd degree");
        ad.setAcademicDegree("Phd");
        System.out.println(ad.getSurname()+" "+ad.getName()+" academic degree is "+ad.getAcademicDegree());
        System.out.println("News: Period of finals is started, let's see our students results");
        oop.defense(true, aza);
        oop.grade(87,aza);
        if(aza.getAge()>ali.getAge()){
            System.out.println(aza.getName()+" is older than "+ali.getName());
        }
        else if(aza.getAge()<ali.getAge()){
            System.out.println(ali.getName()+" is older than "+aza.getName());
        }
        else{
            System.out.println("Students ages is equals");
        }


    }
}
