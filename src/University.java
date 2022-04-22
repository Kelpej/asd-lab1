/*
 * File: University.java
 * -------------------
 *
 * Section Leader: Olena Pechkurova
 *
 * Authors: Serhii Hryhorenko and Artemii Kolomiichuk
 *
 * This file will eventually implement "Лабораторна робота 1".
 */

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
    /**
     * Arrays that store data
     */
    private static Faculty[] faculties;
    private static Department[] departments;
    private static Student[] students;
    private static Professor[] professors;

    /**
     * Premade files
     */
    final static String path = "";//"C:\\Users\\temak\\Desktop\\asd-lab1-main\\";
    private static final File facult = new File(path + "input/faculties");
    private static final File depart = new File(path + "input/departments");
    private static final File stud = new File(path + "input/students");
    private static final File prof = new File(path + "input/professors");

    /**
     * Messages for the user
     */
    private static final String mainMenuText = """
        
        1. Створити/видалити/редагувати факультет.
        2. Створити/видалити/редагувати кафедру факультета.
        3. Додати/видалити/редагувати студента/викладача до кафедри.
        4. Знайти студента/викладача за ПІБ, курсом або групою.
        5. Вивести всіх студентів впорядкованих за курсами.
        6. Вивести всіх студентів/викладачів факультета впорядкованих за алфавітом.
        7. Вивести всіх студентів спеціальності впорядкованих за курсами.
        8. Вивести всіх студентів спеціальності/викладачів кафедри впорядкованих за алфавітом.
        9. Вивести всіх студентів спеціальності вказаного курсу.
        10. Вивести всіх студентів спеціальності вказаного курсу впорядкованих за алфавітом.
        """;
    private static final String changeMenu = """
        1. Create.
        2. Edit.
        3. Delete.
        4. Exit
        """;
    private static final String studentOrProfessor = """
        1. Student.
        2. Professor.
        3. Exit
        """;

    /**
     * Runs the program
     */
    public static void main(String[] args) {
        try {
            readFaculties();
            readDepartments();
            readStudents();
            readProfessors();
            printOutArray(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainMenu();
    }

    /**
     * Main "loop"
     */
    private static void mainMenu() {
        System.out.println(mainMenuText);
        int option = DataInput.checkInt(DataInput.getInt("Choose an option: "), 0, 10);
        int choice;
        switch (option) {
            case 1 -> {
                System.out.println("What exactly do you want to do with a faculty?");
                System.out.println(changeMenu);
                choice = DataInput.getInt("> ");
                switch (choice) {
                    case 1 -> {
                        printOutArray(faculties);
                        createFaculty();
                        printOutArray(faculties);
                    }
                    case 2 -> {
                        try {
                            printOutArray(faculties);
                            Faculty toEdit = findFaculty(DataInput.getString("Enter the name of a faculty you want to change: "));
                            toEdit.setName(new FacultyName(DataInput.getString("Enter a new name for a faculty: ")));
                            printOutArray(faculties);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    case 3 -> {
                        try {
                            Faculty toDelete = findFaculty(DataInput.getString("Enter the name of a faculty you want to delete: "));
                            deleteFaculty(toDelete);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    default -> System.out.println("Exit");
                }
            }
            case 2 -> {
                System.out.println("What exactly do you want to do with a department?");
                System.out.println(changeMenu);
                choice = DataInput.getInt("> ");
                switch (choice) {
                    case 1 -> {
                        printOutArray(departments);
                        createDepartment();
                        printOutArray(departments);
                    }
                    case 2 -> {
                        try {
                            printOutArray(departments);
                            Department toEdit = findDepartment(DataInput.getString("Enter the name of a department you want to change: "));
                            toEdit.setName(new DepartmentName(DataInput.getString("Enter a new name for a department: ")));
                            if (DataInput.getChar("Do you want to edit information about a faculty ? (y/a): ") == 'y')
                                toEdit.setFaculty(findFaculty(DataInput.getString("Enter a name of an existing faculty: ")));
                            printOutArray(departments);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    case 3 -> {
                        try {
                            Department toDelete = findDepartment(DataInput.getString("Enter the name of a department you want to delete: "));
                            deleteDepartment(toDelete);
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    default -> System.out.println("Exit");
                }
            }
            case 3 -> {
                System.out.println("Choose a class you want to change.");
                System.out.println(studentOrProfessor);
                choice = DataInput.checkInt(DataInput.getInt("> "), 0, 3);
                System.out.println(changeMenu);
                switch (choice) {
                    case 1 -> {
                        choice = DataInput.checkInt(DataInput.getInt("> "), 0, 4);
                        switch (choice) {
                            case 1 -> createStudent();
                            case 2 -> {
                                try {
                                    printOutArray(students);
                                    Student toEdit = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    System.out.println(toEdit);
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
                                    System.out.println(toEdit);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                            case 3 -> {
                                try {
                                    printOutArray(students);
                                    Student toDelete = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    System.out.println(toDelete);
                                    deleteStudent(toDelete);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                printOutArray(students);
                            }
                            default -> System.out.println("Exit");
                        }
                    }
                    case 2 -> { //professors
                        choice = DataInput.checkInt(DataInput.getInt("> "), 0, 4);
                        switch (choice) {
                            case 1 -> createProfessor();
                            case 2 -> {
                                try {
                                    printOutArray(professors);
                                    Professor toEdit = findProfessor(DataInput.getString("Enter a name of an existing professor: "));
                                    System.out.println(toEdit);
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
                                    System.out.println(toEdit);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                            case 3 -> {
                                try {
                                    printOutArray(professors);
                                    Student toDelete = findStudent(DataInput.getString("Enter a name of an existing student: "));
                                    deleteStudent(toDelete);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                            default -> System.out.println("Exit");
                        }
                    }
                }
            }
            case 4 -> {
                System.out.println("Choose a class where to find: ");
                System.out.println(studentOrProfessor);
                choice = DataInput.checkInt(DataInput.getInt("> "), 0, 2);
                switch (choice) {
                    case 1 -> {
                        printOutArray(students);
                        try {
                            System.out.println(findStudent(DataInput.getString("Enter a name of an existing student: ")));
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    case 2 -> {
                        printOutArray(professors);
                        try {
                            System.out.println(findProfessor(DataInput.getString("Enter a name of an existing professor: ")));
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    default -> System.out.println("Exit");
                }
            }
            case 5 -> {
                for (int i = 1; i < 5; i++) {
                    System.out.println("\nStudents of " + i + " year of studying:");
                    for (Student student : students) {
                        if (student.getYofs() == i) {
                            System.out.println(student);
                        }
                    }
                }
            }
            case 6 -> {
                printOutArray(faculties);
                Faculty fac = null;
                while (fac == null) {
                    try {
                        fac = findFaculty(DataInput.getString("Enter the name/acronym of a faculty you want to access: "));
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }

                System.out.println("\nStudents of " + fac.getName() + ":");
                Sort.stringSortLH(students);
                for (Student student : students) {
                    if (student.getFaculty() == fac) {
                        System.out.println(student);
                    }
                }
                Sort.stringSortLH(professors);
                System.out.println("\nProfessors of " + fac.getName() + ":");
                for (Professor professor : professors) {
                    if (professor.getFaculty() == fac) {
                        System.out.println(professor);
                    }
                }
            }
            case 7 -> {
                int major = 0;
                while (major == 0) {
                    try {
                        major = DataInput.getInt("\nEnter the number of major you want to access: ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                Student[] studentsSameMajor = new Student[0];
                for (Student student : students) {
                    if (student.getMajor() == major) {
                        var temp = studentsSameMajor;
                        studentsSameMajor = new Student[studentsSameMajor.length + 1];
                        for (int i = 0; i < temp.length; i++) {
                            studentsSameMajor[i] = temp[i];
                        }
                        studentsSameMajor[studentsSameMajor.length - 1] = student;
                    }
                }
                if (studentsSameMajor.length == 0) {
                    System.out.println("No students found");
                } else {
                    for (int i = 1; i < 5; i++) {
                        System.out.println("\nStudents of " + i + " year of studying and major " + major + ":");
                        for (Student student : studentsSameMajor) {
                            if (student.getYofs() == i) {
                                System.out.println(student);
                            }
                        }
                    }
                }
            }
            case 8 -> {
                int choice2 = 0;
                while (choice2 != 1 && choice2 != 2) {
                    try {
                        choice2 = DataInput.getInt("\n1.Студентів спеціальності\n2.Викладачів кафедри ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                if (choice2 == 2) {
                    printOutArray(departments);
                    Department department = null;
                    while (department == null) {
                        try {
                            department = findDepartment(DataInput.getString("\nEnter the name/acronym of a department you want to access: "));
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    Sort.stringSortLH(professors);
                    System.out.println("\nProfessors of " + department.getName() + ":");
                    for (Professor professor : professors) {
                        if (professor.getDepartment() == department) {
                            System.out.println(professor);
                        }
                    }
                } else {
                    int major = 0;
                    while (major == 0) {
                        try {
                            major = DataInput.getInt("\nEnter the number of major you want to access: ");
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                    }
                    Student[] studentsSameMajor = new Student[0];
                    for (Student student : students) {
                        if (student.getMajor() == major) {
                            var temp = studentsSameMajor;
                            studentsSameMajor = new Student[studentsSameMajor.length + 1];
                            for (int i = 0; i < temp.length; i++) {
                                studentsSameMajor[i] = temp[i];
                            }
                            studentsSameMajor[studentsSameMajor.length - 1] = student;
                        }
                    }
                    if (studentsSameMajor.length == 0) {
                        System.out.println("No students found");
                    } else {
                        System.out.println("Students of major " + major + ":");
                        Sort.stringSortLH(studentsSameMajor);
                        for (Student student : studentsSameMajor) {
                            System.out.println(student);
                        }
                        System.out.println("");
                    }
                }
            }
            case 9 -> {
                int major = 0;
                while (major == 0) {
                    try {
                        major = DataInput.getInt("\nEnter the number of major you want to access: ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                int yos = 0;
                while (yos < 1 || yos > 6) {
                    try {
                        yos = DataInput.getInt("\nEnter the year of study: ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                Student[] studentsSameMajor = new Student[0];
                for (Student student : students) {
                    if (student.getMajor() == major) {
                        var temp = studentsSameMajor;
                        studentsSameMajor = new Student[studentsSameMajor.length + 1];
                        for (int i = 0; i < temp.length; i++) {
                            studentsSameMajor[i] = temp[i];
                        }
                        studentsSameMajor[studentsSameMajor.length - 1] = student;
                    }
                }
                if (studentsSameMajor.length == 0) {
                    System.out.println("No students found");
                } else {

                    System.out.println("\nStudents of " + yos + " year of studying and major " + major + ":");
                    for (Student student : studentsSameMajor) {
                        if (student.getYofs() == yos) {
                            System.out.println(student);
                        }
                    }

                    System.out.println("");
                }
            }
            case 10 -> {
                int major = 0;
                while (major == 0) {
                    try {
                        major = DataInput.getInt("\nEnter the number of major you want to access: ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                int yos = 0;
                while (yos < 1 || yos > 6) {
                    try {
                        yos = DataInput.getInt("\nEnter the year of study: ");
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                }
                Student[] studentsSameMajor = new Student[0];
                for (Student student : students) {
                    if (student.getMajor() == major) {
                        var temp = studentsSameMajor;
                        studentsSameMajor = new Student[studentsSameMajor.length + 1];
                        for (int i = 0; i < temp.length; i++) {
                            studentsSameMajor[i] = temp[i];
                        }
                        studentsSameMajor[studentsSameMajor.length - 1] = student;
                    }
                }
                System.out.print(studentsSameMajor.length);
                Sort.stringSortLH(studentsSameMajor);
                System.out.println("\nStudents of " + yos + " year of studying and major " + major + ":");
                for (Student student : studentsSameMajor) {
                    if (student.getYofs() == yos) {
                        System.out.println(student);
                    }
                }
            }
        }
        mainMenu();
    }

    /**
     * @throws FileNotFoundException
     * @throws InvalidFacultyDataFormat
     * @throws InvalidFacultyNameException
     */
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

    /**
     * @throws FileNotFoundException
     * @throws InvalidFacultyDataFormat
     * @throws InvalidDepartmentNameException
     * @throws FacultyDoesNotExist
     */
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

    /**
     * @throws FileNotFoundException
     * @throws InvalidStudentDataFormat
     * @throws InvalidNameException
     * @throws FacultyDoesNotExist
     */
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

    /**
     * @throws FileNotFoundException
     * @throws InvalidNameException
     * @throws FacultyDoesNotExist
     * @throws InvalidProfessorDataFormat
     * @throws DepartmentDoesNotExist
     */
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

    /**
     * @param facultyName
     * @return
     * @throws FacultyDoesNotExist
     */
    private static Faculty findFaculty(String facultyName) throws FacultyDoesNotExist {
        for (Faculty faculty : faculties)
            if (facultyName.equalsIgnoreCase(faculty.getName()) ||
                    facultyName.equalsIgnoreCase(faculty.getAcronym()))
                return faculty;
        throw new FacultyDoesNotExist("\"" + facultyName + "\" does not exist.");
    }

    /**
     * @param departmentName
     * @return
     * @throws DepartmentDoesNotExist
     */
    private static Department findDepartment(String departmentName) throws DepartmentDoesNotExist {
        for (Department department : departments)
            if (departmentName.equalsIgnoreCase(department.getName()) ||
                    departmentName.equalsIgnoreCase(department.getAcronym()))
                return department;
        throw new DepartmentDoesNotExist("\"" + departmentName + "\" does not exist.");
    }

    /**
     * @param studentName
     * @return
     * @throws StudentDoesNotExist
     */
    private static Student findStudent(String studentName) throws StudentDoesNotExist {
        for (Student student : students)
            if (studentName.regionMatches(true, 0, student.getName(), 0, studentName.length()))
                return student;
        throw new StudentDoesNotExist("\"" + studentName + "\" does not exist.");
    }

    /**
     * @param professorName
     * @return
     * @throws ProfessorDoesNotExist
     */
    private static Professor findProfessor(String professorName) throws ProfessorDoesNotExist {
        for (Professor professor : professors)
            if (professorName.regionMatches(true, 0, professor.getName(), 0, professorName.length()))
                return professor;
        throw new ProfessorDoesNotExist("\"" + professorName + "\" does not exist.");
    }

    /**
     *
     */
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

    /**
     * @param toDelete
     */
    private static void deleteFaculty(Faculty toDelete) {
        Faculty[] newFaculties = new Faculty[faculties.length - 1];
        for (int i = 0; i < faculties.length; i++)
            if (faculties[i] != toDelete)
                newFaculties[i] = faculties[i];
        faculties = newFaculties;
    }

    /**
     *
     */
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

    /**
     * @param toDelete
     */
    private static void deleteDepartment(Department toDelete) {
        Department[] newFaculties = new Department[departments.length - 1];
        for (int i = 0; i < departments.length; i++)
            if (departments[i] != toDelete)
                newFaculties[i] = departments[i];
        departments = newFaculties;
    }

    /**
     *
     */
    private static void createStudent() {
        try {
            PersonName name = new PersonName(DataInput.getString("Enter the name of the new student: "));
            int age = DataInput.getInt("Enter age of a new student: ");
            printOutArray(faculties);
            Faculty faculty = findFaculty(DataInput.getString("Enter a name of an existing faculty: "));
            int major = DataInput.getInt("Enter major a new student: ");
            int yofs = DataInput.getInt("Enter a year of studying of a new student: ");

            Student newStudent = new Student(name, age, faculty, major, yofs);
            System.out.println(newStudent);
            Student[] newStudents = Arrays.copyOf(students, students.length + 1);
            if (Arrays.asList(students).contains(newStudent))
                throw new IllegalArgumentException(newStudent + " already exists.");

            newStudents[newStudents.length - 1] = newStudent;
            students = newStudents;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * @param toDelete
     */
    private static void deleteStudent(Student toDelete) {
        Student[] newStudents = new Student[students.length - 1];
        for (int i = 0; i < students.length; i++)
            if (students[i] != toDelete)
                newStudents[i] = students[i];
        students = newStudents;
    }

    /**
     *
     */
    private static void createProfessor() {
        try {
            PersonName name = new PersonName(DataInput.getString("Enter the name of a new professor: "));
            int age = DataInput.getInt("Enter age of a new professor: ");
            printOutArray(faculties);
            Faculty faculty = findFaculty(DataInput.getString("Enter the name of an existing faculty: "));
            System.out.println();
            Arrays.stream(departments).filter(department -> department.getFaculty()== faculty).forEach(System.out::println);
            System.out.println();
            Department department = findDepartment(DataInput.getString("Enter the name of an existing department: "));

            Professor newProfessor = new Professor(name, age, faculty, department);
            System.out.println(newProfessor);
            Professor[] newProfessors = Arrays.copyOf(professors, professors.length + 1);
            newProfessors[newProfessors.length - 1] = newProfessor;
            professors = newProfessors;
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * @param <T>
     * @param array
     */
    private static <T> void printOutArray(T[] array) {
        System.out.println();
        Arrays.stream(array).forEach(System.out::println);
        System.out.println();
    }

}
