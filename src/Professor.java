import Data.PersonName;

public class Professor extends Person{
    private Department department;

    public Professor(PersonName name, int age, Faculty faculty, Department department) {
        super(name, age, faculty);
        this.department = department;
    }
}
