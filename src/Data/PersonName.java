package Data;

import javax.naming.InvalidNameException;

public class PersonName {
    private String name;
/**
 * Ім'я людини
 * @param name
 * @throws InvalidNameException
 */
    public PersonName(String name) throws InvalidNameException {
        if (name.split(" ").length == 3)
            this.name = name;
        else
            throw new InvalidNameException("The name of a person must consist of three parts.\n" +
                    name + " is not valid.");
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
