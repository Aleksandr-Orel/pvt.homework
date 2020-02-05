package pvt.java;

public class Student extends Person {
    public int age;
    public String specialty;
    public String telephoneNumber;

    int getAge() {
        return this.age;
    }

    protected String getSpecialty() {
        return this.specialty;
    }

    private String getTelephoneNumber() {
        return this.telephoneNumber;
    }

    public String isAdult() {
        return this.age < 18 ? "Not adult" : "Adult";
    }

    @Override
    public void printName() {
        System.out.println("The name of this student is " + getName());
    }

    public Student(String name, int age, String specialty, String telephoneNumber) {
        super(name);
        this.age = age;
        this.specialty = specialty;
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public int hashCode() {
        return getCitizenship().hashCode() + getName().hashCode() + String.valueOf(this.age).hashCode()
                + this.specialty.hashCode() + this.telephoneNumber.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + " Name: " + getName() + "; citizenship: " + getCitizenship()
                + "; age: " + this.age + "; specialty: " + this.specialty + "; telephone number: "
                + this.telephoneNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Student student = (Student) obj;
        if (null == super.getName()) {
            return false;
        } else {
            if (!super.getName().equals(student.getName())) {
                return false;
            }
        }
        if (null == student.specialty) {
            return false;
        } else {
            if (!this.specialty.equals(student.specialty)) {
                return false;
            }
        }
        if (this.age != student.age) {
            return false;
        }
        return true;
    }
}
