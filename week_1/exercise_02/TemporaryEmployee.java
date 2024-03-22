public class TemporaryEmployee extends Worker{
    int id;
    String company;
    String endDate;

    public TemporaryEmployee(int id, int age, String fname, int salary, int id1, String company, String endDate) {
        super(id, age, fname, salary);
        this.id = id1;
        this.company = company;
        this.endDate = endDate;
    }

    public String getCompany() {
        return company;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", company='" + company + '\'' +
                ", endDate='" + endDate + '\'' +
                "} " + super.toString();
    }
}
