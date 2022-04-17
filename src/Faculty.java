import Data.FacultyName;

public class Faculty {
    private String name;
    private String acronym;
/**
 * 
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
