public class Person {
    private int id;
    private int age;
    private String fname;

    public Person(int id, int age, String fname) {
        this.id = id;
        this.age = age;
        this.fname = fname;
    }

    public int getId() {
        return id;
    }
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFname() {
        return fname;
    }
}
