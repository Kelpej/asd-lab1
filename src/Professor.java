import Data.PersonName;

import java.util.Objects;

public class Professor extends Person{
    private Department department;
/**
 * Викладач
 * @param name
 * @param age
 * @param faculty
 * @param department
 */
    public Professor(PersonName name, int age, Faculty faculty, Department department) {
        super(name, age, faculty);
        this.department = department;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name= " + getName() +
                ", age= " + getAge() +
                ", faculty= " + getFaculty() +
                ", department=" + department +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        Professor professor = (Professor) o;
        return getName() == professor.getName() && getAge() == professor.getAge() &&
                getDepartment().equals(professor.getDepartment()) && getFaculty().equals(professor.getFaculty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment());
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
