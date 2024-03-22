public class Main {
    public static void main(String[] args) {
        CourseManagement courseManagement = new CourseManagement();

        Course math = new Course("CS101","Integral Calculus");
        Course english = new Course("CS102","English b2: intercultural trip");

        Student student0101 = new Student(1,"Fulanito","fulanito@gmail.com");
        Student student0102 = new Student(1,"Perinejo","fulanito@gmail.com");

        math.inscribirEstudiante(student0101);
        english.inscribirEstudiante(student0102);

        courseManagement.addCourse(math);
        courseManagement.addCourse(english);

        courseManagement.showStudents("CS101");
        courseManagement.showStudents("CS102");
    }
}
