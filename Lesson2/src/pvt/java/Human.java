package pvt.java;

public abstract class Human {
    private String citizenship;

    public void hasHunger() {
        System.out.println("I'm going to find some food");
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCitizenship() {
        return citizenship;
    }
}
