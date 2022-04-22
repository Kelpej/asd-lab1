import Data.PersonName;

import java.util.Objects;

public class Student extends Person{
    private int major;
    private int yofs;
/**
 * Студент
 * @param name
 * @param age
 * @param faculty
 * @param major
 * @param yofs
 */
    public Student(PersonName name, int age, Faculty faculty, int major, int yofs) {
        super(name, age, faculty);
        this.major = major;
        this.yofs = yofs;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name= " + getName() +
                ", age= " + getAge() +
                ", faculty= " + getFaculty() +
                ", major=" + major +
                ", yofs=" + yofs +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getName() == student.getName() && getAge() == student.getAge() && getFaculty() == student.getFaculty() &&
                getMajor() == student.getMajor() && getYofs() == student.getYofs();
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getYofs() {
        return yofs;
    }

    public void setYofs(int yofs) {
        this.yofs = yofs;
    }
}
