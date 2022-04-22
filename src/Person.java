import Data.PersonName;

public class Person {
    private String name;
    private int age;
    private Faculty faculty;
/**
 * 
 * @param name
 * @param age
 * @param faculty
 */
    public Person(PersonName name, int age, Faculty faculty) {
        this.name = name.getName();
        this.age = age;
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", faculty=" + faculty +
                '}';
    }



    public String getName() {
        return name;
    }

    public void setName(PersonName name) {
        this.name = name.getName();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
