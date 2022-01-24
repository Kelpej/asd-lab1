import Data.DepartmentName;

public class Department {
    private String name;
    private String acronym;
    private Faculty faculty;

    public Department(DepartmentName name, Faculty faculty) {
        this.name = name.getName();
        setAcronym();
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return name + '(' + acronym + "), " + faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(DepartmentName name) {
        this.name = name.getName();
        setAcronym();
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym() {
        StringBuffer acronymBuf = new StringBuffer();
        for (String s : this.name.split(" "))
            acronymBuf.append(Character.toUpperCase(s.charAt(0)));
        this.acronym = acronymBuf.toString();
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
