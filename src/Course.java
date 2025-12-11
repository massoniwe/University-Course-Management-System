public class Course {
    private String name;
    private int credits_quantity;

    public Course(String name, int credits_quantity){
        this.name = name;
        this.credits_quantity = credits_quantity;
    }

    public void grade(int grade, Student s){
        System.out.println(s.getName() + " received " + grade + " in " + name);
    }
    public void defense(boolean def, Student s){
        if(def) System.out.println(s.getName() + " successfully defended " + name);
        else System.out.println(s.getName() + " did not pass the defense in " + name);
    }
    public String getName(){ return name;}
    public int getCredits_quantity(){ return credits_quantity;}
    public void setCredits_quantity(int credits_quantity){ this.credits_quantity = credits_quantity;}
    public void setName(String name){ this.name = name;}
}
