public class Worker extends Person {
    private int salary;

    public Worker(int id, int age, String fname, int salary) {
        super(id, age, fname);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return  " id: " + this.getId()+
                " full name: " + this.getFname()+
                " age: " + this.getAge()+
                " salary: " + salary;
    }
}
