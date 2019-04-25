package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Person extends Contact {
    private String name;
    private String surname;
    private Date birthDate;
    private String gender;

    public Person(String name, String surname, Date birthDate, String gender, String number) {
        super(number);
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, Object> getExtraFields() {
        return new HashMap<String, Object>() {{
            put("surname", surname);
            put("birthDate", birthDate);
            put("gender", gender);
        }};
    }

    @Override
    public void setExtraFields(Map<String, Object> extraFields) {
        extraFields.forEach((k, v) -> {
            switch (k) {
                case "surname":
                    surname = (String) v;
                    timeLastEdit = new java.util.Date();
                    break;
                case "birthDate":
                    birthDate = (Date) v;
                    timeLastEdit = new java.util.Date();
                    break;
                case "gender":
                    gender = (String) v;
                    timeLastEdit = new java.util.Date();
                    break;
            }
        });

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Name: %s\n", name));
        result.append(String.format("Surname: %s\n", surname));
        result.append(String.format("Number: %s\n", getNumber()));
        result.append(String.format("Birth date: %s\n", birthDate));
        result.append(String.format("Gender: %s\n", gender));
        result.append(String.format("Create time: %s\n", getTimeCreated()));
        result.append(String.format("Last edit time: %s\n", getTimeLastEdit()));
        return result.toString();
    }
}
