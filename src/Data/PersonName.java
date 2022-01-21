package Data;

import javax.naming.InvalidNameException;

public class PersonName {
    private String name;

    public PersonName(String name) throws InvalidNameException {
        if (name.split(" ").length == 3)
            this.name = name;
        else
            throw new InvalidNameException(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                '}';
    }
}
