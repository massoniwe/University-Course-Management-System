import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class University {
    private ArrayList<Person> people = new ArrayList<>();
    private String name;
    private String address;

    public University(String name, String address){
        this.name = name;
        this.address = address;
    }
    public void enroll(Person s){
            System.out.println(s.getName() + " is enrolled");
            people.add(s);
    }
    public void expel(Student s){
        System.out.println(s.getName() + " is expelled");
    }
    public void PrintALL(){
        for(Person p:people){
            System.out.println(p.getRole()+" "+p.getName());
        }
    }
    public void printStudents(){
        for(Person p:people){
            if(p instanceof Student){
                System.out.println(p.getName());
            }
        }
    }
    public Person findByName(String name){
        for(Person p:people){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
    public void SortByAge(){
        Collections.sort(people,Comparator.comparing(Person::getAge).reversed());
    }
    public String getName(){ return name;}
    public String getAddress(){ return address;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
    public ArrayList<Student> getStudentsByCourse(int course) {
        ArrayList<Student> result = new ArrayList<>();
        for (Person p : people) {
            if (p instanceof Student) {
                Student s = (Student) p;
                if (s.getCourse() == course) {
                    result.add(s);
                }
            }
        }
        return result;
    }

}

