import Data.DepartmentName;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getName().equals(that.getName()) && getAcronym().equals(that.getAcronym()) && getFaculty().equals(that.getFaculty());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAcronym(), getFaculty());
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
