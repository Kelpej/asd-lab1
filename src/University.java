import Data.DataInput;
import Data.DepartmentName;
import Data.Exceptions.*;
import Data.FacultyName;
import Data.PersonName;

import javax.naming.InvalidNameException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class University {
    private static Faculty[] faculties;
    private static Department[] departments;
    private static Professor[] professors;
    private static Student[] students;

    private static final File fauclt = new File("input/faculties");
    private static final File depart = new File("input/departments");
    private static final File stud = new File("input/students");

    public static void main(String[] args) {
        try {
            readFaculties();
            System.out.println(Arrays.toString(faculties));
            readStudents();
            //readFaculties();
            Professor vykladach = new Professor(new PersonName("Митник Юрій Васильович"), 69, new Faculty(new FacultyName("Факультет інформатики")),
                    new Department(new DepartmentName("Кафедра математики")));
            System.out.println(vykladach);
            Person chel2 = new Person(new PersonName("Григоренко Сергій"), 4, new Faculty(new FacultyName("Факультет інформатики")));
            System.out.println(chel2);
            Person chel = new Person(new PersonName("Григоренко Сергій Сергійович"), 24, new Faculty(new FacultyName("Факультет інформатики")));
            System.out.println(chel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readFaculties() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidFacultyNameException {
        String[] input = DataInput.readFile(fauclt);
        int length = input.length;

        faculties = new Faculty[length];

        for (int i = 0; i < length; i++) {
            String[] facultyData = input[i].split(" ");

            if (facultyData.length < 2)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper faculty data.");

            Faculty faculty = new Faculty(new FacultyName(facultyData[0]));

            faculties[i] = faculty;
        }
    }

    private static void readDepartments() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidDepartmentNameException {
        String[] input = DataInput.readFile(depart);
        int length = input.length;

        departments = new Department[length];

        for (int i = 0; i < length; i++) {
            String[] departmentData = input[i].split(" ");

            if (departmentData.length < 2)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper department data.");

            Department faculty = new Department(new DepartmentName(departmentData[0]));

            departments[i] = faculty;
        }
    }

    private static void readStudents() throws FileNotFoundException, InvalidStudentDataFormat, InvalidNameException, FacultyDoesNotExist {
        String[] input = DataInput.readFile(stud);
        int length = input.length;

        students = new Student[length];

        for (int i = 0; i < length; i++) {
            String[] studentData = input[i].split(" ");

            if (studentData.length != 7)
                throw new InvalidStudentDataFormat("\"" + input[i] + "\"" + " is not proper student data.");

            StringBuffer name = new StringBuffer();
            name.append(studentData[0]).append(" ").append(studentData[1]).append(" ").append(studentData[2]);
            Student student = new Student(new PersonName(name.toString()), Integer.parseInt(studentData[3]),
                    findFaculty(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));

            students[i] = student;
        }

    }

    private static Faculty findFaculty(String facultyName) throws FacultyDoesNotExist {
        for (Faculty faculty : faculties)
            if (facultyName.equals(faculty.getName()) || facultyName.equals(faculty.getAcronym()))
                return faculty;
        throw new FacultyDoesNotExist("\"" + facultyName + "\" does not exist.");
    }

    private static Department findDepartment(String departmentName) throws DepartmentDoesNotExist {
        for (Department department : departments)
            if (departmentName.equals(department.getName()))
                return department;
        throw new DepartmentDoesNotExist("\"" + departmentName + "\" does not exist.");
    }
}
