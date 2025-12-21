import java.util.Objects;

public abstract class Person {
    protected String name;
    protected int age;
    public Person(String name, int age){
        this.name = name;
        this.age=age;
    }

    public abstract String getRole();

    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void celebrateBirthday(){
        age++;
    }
    @Override
    public String toString() {
        return getRole() + " " + name + ", age " + age;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true; //same objects
        if(o == null) return false; //null
        if(getClass() != o.getClass()) return false; //different classes

        Person p = (Person)o;
        return age == p.age && name.equals(p.name);
    }
    @Override
    public int hashCode() {
        return name.hashCode()+age;
    }


}
