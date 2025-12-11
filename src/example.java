public class example {
    // 1. Поля
    private String name;
    private int age;
    // 2. Конструкто
    public example(String name, int age){
        this.name=name;
        this.age=age;
    }
    // 3. Методы

    public void approve(){
        System.out.println(name+" is approved");
    }

    // 4. Getters/Setters
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public void setName(String value){
        this.name=value;
    }

}

// 1. ПОЛЯ
// 2. КОНСТРУКТОР
// 3. МЕТОДЫ
// 4. GETTERS/SETTERS


