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
    private static Student[] students;
    private static Professor[] professors;

    private static final File fauclt = new File("input/faculties");
    private static final File depart = new File("input/departments");
    private static final File stud = new File("input/students");
    private static final File prof = new File("input/professors");

    private static final String mainMenuText = "1. Створити/видалити/редагувати факультет." +
            "\n2. Створити/видалити/редагувати кафедру факультета." +
            "\n3. Додати/видалити/редагувати студента/викладача до кафедри." +
            "\n4. Знайти студента/викладача за ПІБ, курсом або групою." +
            "\n5. Вивести всіх студентів впорядкованих за курсами." +
            "\n6. Вивести всіх студентів/викладачів факультета впорядкованих за алфавітом." +
            "\n7. Вивести всіх студентів кафедри впорядкованих за курсами." + //??????????????
            "\n8. Вивести всіх студентів/викладачів кафедри впорядкованих за алфавітом." +
            "\n9. Вивести всіх студентів кафедри вказаного курсу." + //must be changed to faculty
            "\n10. Вивести всіх студентів кафедри вказаного курсу впорядкованих за алфавітом.\n"; //must be changed to faculty
    private static final String changeMenu = "1. Create.\n2. Edit.\n3. Delete.";
    private static final String studentOrProfessor = "1. Student.\n2. Professor.";
    
    public static void main(String[] args) {
        try {
            readFaculties();
            readDepartments();
            readStudents();
            readProfessors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(professors));
    }
    private static void mainMenu() {
        System.out.println(mainMenuText);
        int option = DataInput.checkInt(DataInput.getInt("Choose an option: "), 1, 10);

        switch(option) {
            case 1:
                System.out.println("What exactly do you want to do with a faculty?");
                System.out.println(changeMenu);
                break;
            case 2:
                System.out.println("What exactly do you want to do with a department?");
                System.out.println(changeMenu);
                break;
            case 3:
                System.out.println("Choose a class you want to change.");
                System.out.println(studentOrProfessor);
                break;
            case 4:
                System.out.println("Choose a class where to find.");
                System.out.println(studentOrProfessor);
                break;
            case 5:
                System.out.println("Here are the students by year of studying ascending: ");
                break;
            case 6:
                System.out.println("Choose a class to print out by alphabet: ");
                System.out.println(studentOrProfessor);
                break;
            case 7:
                //хз що тут писати, завдання єбаніна якась
                break;
            case 8:
                System.out.println("Choose a department: ");
                break;
            case 9:
                System.out.println("Enter a name of a faculty: ");
                System.out.println("Enter a year of studying: ");
                break;
            case 10:
                System.out.println("Enter a name of a faculty: ");
                System.out.println("Here they are by alphabet ascending: ");
                break;
        }
    }
    private static void readFaculties() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidFacultyNameException {
        String[] input = DataInput.readFile(fauclt);
        int length = input.length;

        faculties = new Faculty[length];

        for (int i = 0; i < length; i++) {
            if (input[i].split(" ").length < 2)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper faculty data.");

            Faculty faculty = new Faculty(new FacultyName(input[i]));

            faculties[i] = faculty;
        }
    }

    private static void readDepartments() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidDepartmentNameException {
        String[] input = DataInput.readFile(depart);
        int length = input.length;

        departments = new Department[length];

        for (int i = 0; i < length; i++) {
            if (input[i].split(" ").length < 2)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper department data.");

            Department faculty = new Department(new DepartmentName(input[i]));

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

    private static void readProfessors() throws FileNotFoundException, InvalidNameException, FacultyDoesNotExist, InvalidProfessorDataFormat, DepartmentDoesNotExist {
        String[] input = DataInput.readFile(prof);
        int length = input.length;

        professors = new Professor[length];

        for (int i = 0; i < length; i++) {
            String[] professorData = input[i].split(" ");

            if (professorData.length != 6)
                throw new InvalidProfessorDataFormat("\"" + input[i] + "\"" + " is not proper professor data.");

            StringBuffer name = new StringBuffer();
            name.append(professorData[0]).append(" ").append(professorData[1]).append(" ").append(professorData[2]);
            Professor professor = new Professor(new PersonName(name.toString()), Integer.parseInt(professorData[3]),
                    findFaculty(professorData[4]), findDepartment(professorData[5]));

            professors[i] = professor;
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
            if (departmentName.equals(department.getName()) || departmentName.equals(department.getAcronym()))
                return department;
        throw new DepartmentDoesNotExist("\"" + departmentName + "\" does not exist.");
    }

    private static Student findStudent(String studentName) throws StudentDoesNotExist{
        for (Student student : students)
            if (studentName.equals(student.getName()))
                return student;
        throw new StudentDoesNotExist("\"" + studentName + "\" does not exist.");
    }

    private static void createFaculty() {
        try {
            String name = DataInput.getString("Enter the name of the new faculty: ");
            Faculty newFaculty = new Faculty(new FacultyName(name));
            Faculty[] newFaculties = Arrays.copyOf(faculties, faculties.length + 1);
            newFaculties[newFaculties.length - 1] = newFaculty;
            faculties = newFaculties;
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
