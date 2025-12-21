public class Professor extends Person{
    private String surname;
    private String academicDegree;

    public Professor(String name, int age, String surname, String academicDegree) {
        super(name,age);
        this.surname=surname;
        this.academicDegree=academicDegree;
    }
    public void giveLecture(boolean l){
        if(l) System.out.println("Lecture by "+name+" "+surname+" was successful");
        else System.out.println("Lecture by "+name+" "+surname+" was cancelled");
    }
    public void givePractice(boolean l){
        if(l) System.out.println("Practice by "+name+" "+surname+" was successful");
        else System.out.println("Practice by "+name+" "+surname+" was cancelled");
    }
    public String getSurname() {return surname;}
    public String getAcademicDegree() {return academicDegree;}
    public void setSurname(String surname){ this.surname=surname;}
    public void setAcademicDegree(String academicDegree){this.academicDegree=academicDegree;}
    @Override
    public String getRole(){
        return "Professor";
    }
}
