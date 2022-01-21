import Data.PersonName;

public class Person {
    private String name;
    private int age;
    private Faculty faculty;

    public Person(PersonName name, int age, Faculty faculty) {
        this.name = name.getName();
        this.age = age;
        this.faculty = faculty;
    }
}
