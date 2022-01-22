package Data;

import Data.Exceptions.InvalidFacultyNameException;

public class FacultyName {
    private String name;

    public FacultyName(String name) throws InvalidFacultyNameException {
        if (name.split(" ")[0].equals("Факультет")) { //fix read
            this.name = name;
            fixString();
        } else {
            throw new InvalidFacultyNameException("The name of a faculty must begin with \"Факультет\".\n" +
                    name + " is not valid.");
        }
    }

    private void fixString() {
        char[] data = name.toCharArray();
        for (int i = 9; i < data.length - 1; i++) {
            if (data[i] == ' ')
                data[i + 1] = Character.toLowerCase(data[i + 1]);
        }

        this.name = String.valueOf(data);
    }

    @Override
    public String toString() {
        return "FacultyName{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
