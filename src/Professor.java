import Data.PersonName;

public class Professor extends Person{
    private Department department;
/**
 * 
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
