public class University {
    private String name;
    private String address;

    public University(String name, String address){
        this.name = name;
        this.address = address;
    }
    public void enroll(boolean b, Student s){
        if(b) System.out.println(s.getName() + " is enrolled");
        else System.out.println(s.getName() + " is not enrolled");
    }
    public void expel(boolean b, Student s){
        if(b) System.out.println(s.getName() + " is expelled");
        else System.out.println(s.getName() + " is not expelled");
    }
    public String getName(){ return name;}
    public String getAddress(){ return address;}
    public void setName(String name){this.name = name;}
    public void setAddress(String address){this.address = address;}
}

