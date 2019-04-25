package model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Organization extends Contact {
    private String organizationName;
    private String address;

    public Organization(String name, String number, String address) {
        super(number);
        organizationName = name;
        this.address = address;
    }

    @Override
    public String getName() {
        return organizationName;
    }

    @Override
    public void setName(String name) {
        organizationName = name;
        this.timeLastEdit = new java.util.Date();
    }

    @Override
    public Map<String, Object> getExtraFields() {
        return new HashMap<String, Object>() {{
            put("address", address);
        }};
    }

    @Override
    public void setExtraFields(Map<String, Object> extraFields) {
        extraFields.forEach((k, v) -> {
            switch (k) {
                case "address":
                    address = (String) v;
                    this.timeLastEdit = new java.util.Date();
                    break;
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Organization name: %s\n", organizationName));
        result.append(String.format("Number: %s\n", getNumber()));
        result.append(String.format("Address: %s\n", address));
        result.append(String.format("Create time: %s\n", getTimeCreated()));
        result.append(String.format("Last edit time: %s\n", getTimeLastEdit()));
        return result.toString();
    }
}
