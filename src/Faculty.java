import Data.FacultyName;

public class Faculty {
    private String name;
    private String acronym;

    public Faculty(FacultyName name) {
        this.name = name.getName();

        StringBuffer acronymBuf = new StringBuffer();
        for (String s : name.getName().split(" "))
            acronymBuf.append(Character.toUpperCase(s.charAt(0)));

        this.acronym = acronymBuf.toString();
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", acronym='" + acronym + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }
}
