package pvt.java;

public class Person extends Human{
    private String name;

    public void printName() {
        System.out.println("Name is " + this.name);
    }

    public String getName() {
        return name;
    }

    public Person (String name) {
        this.name = name;
    }
}
