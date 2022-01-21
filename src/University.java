import Data.PersonName;

import javax.naming.InvalidNameException;

public class University {
    Faculty[] faculties;
    Department[] departments;
    Professor[] professors;
    Student[] students;

    public static void main(String[] args[]) {
        try {
            Person chel = new Person(new PersonName("Григоренко Сергій Сергійович"), 24, new Faculty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
