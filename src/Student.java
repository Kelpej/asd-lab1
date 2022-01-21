import Data.PersonName;

public class Student extends Person{
    private int major;
    private int yofs;

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
