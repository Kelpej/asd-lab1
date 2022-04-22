package Data;

import Data.Exceptions.InvalidDepartmentNameException;

public class DepartmentName {
    String name;
/**
 * Назва кафедри
 * @param name
 * @throws InvalidDepartmentNameException
 */
    public DepartmentName(String name) throws InvalidDepartmentNameException {
        if (name.split(" ")[0].equals("Кафедра")) {
            this.name = name;
            fixString(this.name);
        } else {
            throw new InvalidDepartmentNameException("The name of a department must begin with \"Кафедра\".\n" +
                    name + " is not valid.");
        }
    }
/**
 * корегує назву кафедри з урахуванням входження під час написання слова «кафедра» 
 * @param name
 */
    private void fixString(String name) {
        char[] data = name.toCharArray();

        for (int i = 7; i < data.length - 1; i++) {
            if (data[i] == ' ')
                data[i + 1] = Character.toLowerCase(data[i + 1]);
        }

        this.name = String.valueOf(data);
    }

    @Override
    public String toString() {
        return "DepartmentName{" +
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
