import Data.FacultyName;

import java.util.Objects;

public class Faculty {
    private String name;
    private String acronym;
/**
 * Факультет
 * @param name
 */
    public Faculty(FacultyName name) {
        this.name = name.getName();
        setAcronym();
    }

    @Override
    public String toString() {
        return name + '(' + acronym + ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;
        Faculty faculty = (Faculty) o;
        return getName().equals(faculty.getName()) && getAcronym().equals(faculty.getAcronym());
    }

    public String getName() {
        return name;
    }

    public void setName(FacultyName name) {
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
}
