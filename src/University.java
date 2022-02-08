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

    /*
    private static final File facult = new File("input/faculties");
    private static final File depart = new File("input/departments");
    private static final File stud = new File("input/students");
    private static final File prof = new File("input/professors");
    */

    private static final File facult = new File("C:\\Users\\temak\\Desktop\\LABA 1\\asd-lab1\\input\\faculties");
    private static final File depart = new File("C:\\Users\\temak\\Desktop\\LABA 1\\asd-lab1\\input\\departments");
    private static final File stud = new File("C:\\Users\\temak\\Desktop\\LABA 1\\asd-lab1\\input\\students");
    private static final File prof = new File("C:\\Users\\temak\\Desktop\\LABA 1\\asd-lab1\\input\\professors");


    private static final String mainMenuText = 
            "\n1. Створити/видалити/редагувати факультет.\n"+
            "2. Створити/видалити/редагувати кафедру факультета.\n"+
            "3. Додати/видалити/редагувати студента/викладача до кафедри.\n"+
            "4. Знайти студента/викладача за ПІБ, курсом або групою.\n"+
            "5. Вивести всіх студентів впорядкованих за курсами.\n"+
            "6. Вивести всіх студентів/викладачів факультета впорядкованих за алфавітом.\n"+
            "7. Вивести всіх студентів кафедри впорядкованих за курсами.\n"+
            "8. Вивести всіх студентів/викладачів кафедри впорядкованих за алфавітом.\n"+
            "9. Вивести всіх студентів кафедри вказаного курсу.\n"+
            "10. Вивести всіх студентів кафедри вказаного курсу впорядкованих за алфавітом.\n"
            ;
    private static final String changeMenu = "1. Create.\n2. Edit.\n3. Delete.\n4. Exit";
    private static final String studentOrProfessor = "1. Student.\n2. Professor.\n3. Exit";

    public static void main(String[] args) {
        try {
            readFaculties();
            readDepartments();
            readStudents();
            readProfessors();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //printOutArray(departments);
        mainMenu();
    }

    private static void mainMenu() {
        System.out.println(mainMenuText);
        int option = DataInput.checkInt(DataInput.getInt("Choose an option: "), 0, 10);
        int choice;
        switch (option) {
            case 1:
                System.out.println("What exactly do you want to do with a faculty?");
                System.out.println(changeMenu);
                choice = DataInput.getInt("> ");
                switch (choice) {
                    case 1:
                        printOutArray(faculties);
                        createFaculty();
                        printOutArray(faculties);
                        break;
                    case 2:
                        try {
                            printOutArray(faculties);
                            Faculty toEdit = findFaculty(DataInput.getString("Enter the name of a faculty you want to change: "));
                            toEdit.setName(new FacultyName(DataInput.getString("Enter a new name for a faculty: ")));
                            printOutArray(faculties);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 3:
                        try {
                            Faculty toDelete = findFaculty(DataInput.getString("Enter the name of a faculty you want to delete: "));
                            deleteFaculty(toDelete);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    default:
                        System.out.println("Exit");
                }
                break;
            case 2:
                System.out.println("What exactly do you want to do with a department?");
                System.out.println(changeMenu);
                choice = DataInput.getInt("> ");
                switch (choice) {
                    case 1:
                        createDepartment();
                        break;
                    case 2:
                        try {
                            printOutArray(departments);
                            Department toEdit = findDepartment(DataInput.getString("Enter the name of a department you want to change: "));
                            toEdit.setName(new DepartmentName(DataInput.getString("Enter a new name for a department: ")));
                            if (DataInput.getChar("Do you want to edit faculty information? (y/a): ") == 'y')
                                toEdit.setFaculty(findFaculty(DataInput.getString("Enter a name of an existing faculty: ")));
                            printOutArray(departments);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 3:
                        try {
                            Department toDelete = findDepartment(DataInput.getString("Enter the name of a faculty you want to delete: "));
                            deleteDepartment(toDelete);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    default:
                        System.out.println("Exit");
                }
                break;
            case 3:
                System.out.println("Choose a class you want to change.");
                System.out.println(studentOrProfessor);
                choice = DataInput.checkInt(DataInput.getInt("> "), 0, 3);
                System.out.println(changeMenu);
                switch (choice) {
                    case 1:
                        choice = DataInput.checkInt(DataInput.getInt("> "), 0, 4);
                        switch (choice) {
                            case 1:
                                createStudent();
                                break;
                            case 2:
                                try {
                                    printOutArray(students);
                                    Student toEdit = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    if (DataInput.getChar("Do you want to change name? (y/a): ") == 'y')
                                        toEdit.setName(new PersonName(DataInput.getString("> ")));
                                    if (DataInput.getChar("Do you want to change age? (y/a): ") == 'y')
                                        toEdit.setAge(DataInput.checkInt(DataInput.getInt("> "), 14, 99));
                                    if (DataInput.getChar("Do you want to change faculty? (y/a): ") == 'y') {
                                        printOutArray(faculties);
                                        toEdit.setFaculty(findFaculty(DataInput.getString("Enter a name of an existing faculty: ")));
                                    }
                                    if (DataInput.getChar("Do you want to change major? (y/a): ") == 'y')
                                        toEdit.setMajor(DataInput.checkInt(DataInput.getInt("> "), 100, 300));
                                    if (DataInput.getChar("Do you want to change year of studying? (y/a): ") == 'y')
                                        toEdit.setYofs(DataInput.checkInt(DataInput.getInt("> "), 0, 6));
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            case 3:
                                try {
                                    printOutArray(students);
                                    Student toDelete = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    deleteStudent(toDelete);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            default:
                                System.out.println("Exit");
                        }
                    case 2: //professors
                        choice = DataInput.checkInt(DataInput.getInt("> "), 0, 4);
                        switch (choice) {
                            case 1:
                                createProfessor();
                                break;
                            case 2:
                                try {
                                    printOutArray(professors);
                                    Professor toEdit = findProfessor(DataInput.getString("Enter a name of an existing professor: "));
                                    if (DataInput.getChar("Do you want to change name? (y/a): ") == 'y')
                                        toEdit.setName(new PersonName(DataInput.getString("> ")));
                                    if (DataInput.getChar("Do you want to change age? (y/a): ") == 'y')
                                        toEdit.setAge(DataInput.checkInt(DataInput.getInt("> "), 14, 99));
                                    if (DataInput.getChar("Do you want to change a faculty? (y/a): ") == 'y') {
                                        printOutArray(faculties);
                                        toEdit.setFaculty(findFaculty(DataInput.getString("Enter a name of an existing faculty: ")));
                                    }
                                    if (DataInput.getChar("Do you want to change a department? (y/a): ") == 'y') {
                                        printOutArray(departments);
                                        toEdit.setDepartment(findDepartment(DataInput.getString("Enter a name of an existing department: ")));
                                    }
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            case 3:
                                try {
                                    printOutArray(professors);
                                    Student toDelete = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    deleteStudent(toDelete);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            default:
                                System.out.println("Exit");
                        }
                }
                break;
            case 4:
                System.out.println("Choose a class where to find.");
                System.out.println(studentOrProfessor);
                choice = DataInput.checkInt(DataInput.getInt("> "), 0, 2);
                switch (choice) {
                    case 1:
                        try {
                            System.out.println(findStudent(DataInput.getString("Enter a name of an existing student: ")));
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    case 2:
                        try {
                            System.out.println(findProfessor(DataInput.getString("Enter a name of an existing professor: ")));
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        break;
                    default:
                        System.out.println("Exit");
                }
                break;
            case 5:
                for(int i = 1; i < 5; i++)
                {
                    System.out.println("\nStudents of " + i + " year of studying:");
                    for (Student student : students) 
                    {
                        if(student.getYofs() == i)
                        {
                            System.out.println(student);
                        }
                    }
                }
                break;
            case 6:
                printOutArray(faculties);
                Faculty fac = null;
                while(fac == null)
                {
                    try {
                        fac = findFaculty(DataInput.getString("Enter the name/acronym of a faculty you want to access: "));
                    } catch (Exception e) {System.err.println(e);}
                }

                System.out.println("\nStudents of " + fac.getName() + ":");
                Sort.stringSortLH(students);
                for (Student student : students) 
                {
                    if(student.getFaculty().equals(fac))
                    {
                         System.out.println(student);
                    }
                }
                Sort.stringSortLH(professors);
                System.out.println("\nProfessors of " + fac.getName()+ ":");
                for (Professor professor : professors) 
                {
                    if(professor.getFaculty().equals(fac))
                    {
                         System.out.println(professor);
                    }
                }

                break;
            case 7:
                //Вивести всіх студентів кафедри впорядкованих за курсами.
                ///студент <---> кафедра ???
                break;
            case 8:
            //Вивести всіх студентів/викладачів кафедри впорядкованих за алфавітом.
            //студент <---> кафедра ???
                printOutArray(departments);
                Department dep = null;
                while(dep == null)
                {
                    try {
                        dep = findDepartment(DataInput.getString("\nEnter the name/acronym of a department you want to access: "));
                    } catch (Exception e) {System.err.println(e);}
                }
                Sort.stringSortLH(professors);
                System.out.println("\nProfessors of " + dep.getName()+ ":");
                for (Professor professor : professors) 
                {
                    if(professor.getDepartment().equals(dep))
                    {
                         System.out.println(professor);
                    }
                }
                break;
            case 9:
            //Вивести всіх студентів кафедри вказаного курсу.
            //студент <---> кафедра ???
                //System.out.println("Enter a name of a faculty: ");
                //System.out.println("Enter a year of studying: ");
                break;
            case 10:
            //Вивести всіх студентів кафедри вказаного курсу впорядкованих за алфавітом.
            //студент <---> кафедра ???
                //System.out.println("Enter a name of a faculty: ");
                //System.out.println("Here they are by alphabet ascending: ");
                break;
        }
        mainMenu();
    }

    private static void readFaculties() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidFacultyNameException {
        String[] input = DataInput.readFile(facult);
        int length = input.length;

        faculties = new Faculty[length];

        for (int i = 0; i < length; i++) {
            if (input[i].split(" ").length < 2)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper faculty data.");

            Faculty faculty = new Faculty(new FacultyName(input[i]));

            faculties[i] = faculty;
        }
    }

    private static void readDepartments() throws FileNotFoundException, InvalidFacultyDataFormat, InvalidDepartmentNameException, FacultyDoesNotExist {
        String[] input = DataInput.readFile(depart);
        int length = input.length;

        departments = new Department[length];

        for (int i = 0; i < length; i++) {
            String[] departmentData = input[i].split(" ");
            if (departmentData.length < 3)
                throw new InvalidFacultyDataFormat("\"" + input[i] + "\"" + " is not proper department data.");

            StringBuffer facultyName = new StringBuffer();
            for (int j = 0; j < departmentData.length - 1; j++) {
                facultyName.append(departmentData[j]);
                if (j < departmentData.length - 1)
                    facultyName.append(' ');
            }

            Department department = new Department(new DepartmentName(facultyName.toString()),
                    findFaculty(departmentData[departmentData.length - 1]));

            departments[i] = department;
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

            Student student = new Student(new PersonName(studentData[0] + " " + studentData[1] + " " + studentData[2]),
                    Integer.parseInt(studentData[3]), findFaculty(studentData[4]), Integer.parseInt(studentData[5]), Integer.parseInt(studentData[6]));

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

            Professor professor = new Professor(new PersonName(professorData[0] + " " + professorData[1] + " " + professorData[2]), Integer.parseInt(professorData[3]),
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

    private static Student findStudent(String studentName) throws StudentDoesNotExist {
        for (Student student : students)
            if (studentName.equals(student.getName()))
                return student;
        throw new StudentDoesNotExist("\"" + studentName + "\" does not exist.");
    }

    private static Professor findProfessor(String professorName) throws ProfessorDoesNotExist {
        for (Professor professor : professors)
            if (professorName.equals(professor.getName()))
                return professor;
        throw new ProfessorDoesNotExist("\"" + professorName + "\" does not exist.");
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

    private static void deleteFaculty(Faculty toDelete) {
        Faculty[] newFaculties = new Faculty[faculties.length - 1];
        for (int i = 0; i < faculties.length; i++)
            if (faculties[i] != toDelete)
                newFaculties[i] = faculties[i];
        faculties = newFaculties;
    }

    private static void createDepartment() {
        try {
            DepartmentName name = new DepartmentName(DataInput.getString("Enter the name of the new department: "));
            Faculty faculty = findFaculty(DataInput.getString("Enter a name of an existing faculty: "));
            Department newDepartment = new Department(name, faculty);
            Department[] newDepartments = Arrays.copyOf(departments, departments.length + 1);
            newDepartments[newDepartments.length - 1] = newDepartment;
            departments = newDepartments;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void deleteDepartment(Department toDelete) {
        Department[] newFaculties = new Department[departments.length - 1];
        for (int i = 0; i < departments.length; i++)
            if (departments[i] != toDelete)
                newFaculties[i] = departments[i];
        departments = newFaculties;
    }

    private static void createStudent() {
        try {
            PersonName name = new PersonName(DataInput.getString("Enter the name of the new student: "));
            int age = DataInput.getInt("Enter age of a new student: ");
            Faculty faculty = findFaculty("Enter a name of an existing faculty");
            int major = DataInput.getInt("Enter age of a new student: ");
            int yofs = DataInput.getInt("Enter a year of studying of a new student: ");

            Student newStudent = new Student(name, age, faculty, major, yofs);
            Student[] newStudents = Arrays.copyOf(students, students.length + 1);
            newStudents[newStudents.length - 1] = newStudent;
            students = newStudents;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void deleteStudent(Student toDelete) {
        Student[] newStudents = new Student[students.length - 1];
        for (int i = 0; i < students.length; i++)
            if (students[i] != toDelete)
                newStudents[i] = students[i];
        students = newStudents;
    }

    private static void createProfessor() {
        try {
            PersonName name = new PersonName(DataInput.getString("Enter the name of a new professor: "));
            int age = DataInput.getInt("Enter age of a new professor: ");
            Faculty faculty = findFaculty(DataInput.getString("Enter the name of an existing faculty: "));
            Department department = findDepartment(DataInput.getString("Enter the name of an existing department: "));

            Professor newProfessor = new Professor(name, age, faculty, department);
            Professor[] newProfessors = Arrays.copyOf(professors, professors.length + 1);
            newProfessors[newProfessors.length - 1] = newProfessor;
            professors = newProfessors;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static void printOutArray(Faculty[] array) {
        for (Faculty element : array)
            System.out.println(element);
    }
    private static void printOutArray(Department[] array) {
        for (Department element : array)
            System.out.println(element);
    }
    private static void printOutArray(Professor[] array) {
        for (Professor element : array)
            System.out.println(element);
    }
    private static void printOutArray(Student[] array) {
        for (Student element : array)
            System.out.println(element);
    }
    
}