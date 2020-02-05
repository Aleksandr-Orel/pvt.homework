package pvt.java;

public class Example {
    public  static void main(String[] args) {
        Student student = new Student("Ann", 19, "Art",
                "545-75-86");
        student.setCitizenship("Belarus");
        System.out.println(student.toString());
        System.out.println();
        System.out.println("HashCode: " + student.hashCode());
    }
}
