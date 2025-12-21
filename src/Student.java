public class Student extends Person{
    private int course;
    public Student(String name, int age, int course) {
        super(name,age);
        this.course = course;
    }
    public int getCourse(){
        return course;
    }
    public void setCourse(int course){
        this.course = course;
    }

    @Override
    public String getRole(){
        return "Student";
    }
}
