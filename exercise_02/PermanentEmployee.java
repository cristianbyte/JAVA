public class PermanentEmployee extends Worker{
    int id;
    String startDate;

    public PermanentEmployee(int id, int age, String fname, int salary, int id1, String startDate) {
        super(id, age, fname, salary);
        this.id = id1;
        this.startDate = startDate;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return  super.toString() +
                " id: " + id +
                " startDate: " + startDate;
    }
}
