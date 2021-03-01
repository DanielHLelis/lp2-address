package br.cefetmg.address.model.serializer;

import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonSerializer implements Serializer<Person> {
    private static final int LINES = 7;

    public String serialize(Person p) {
        StringBuilder b = new StringBuilder();

        b.append(Utils.stringOr(p.getId(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getBirthday(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getCity(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getFirstName(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getLastName(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getPostalCode(), ""));
        b.append('\n');

        b.append(Utils.stringOr(p.getStreet(), ""));
        b.append('\n');

        return b.toString();
    }

    public String serialize(List<Person> ps) {
        StringBuilder b = new StringBuilder();

        for (var p : ps) {
            b.append(serialize(p));
        }

        return b.toString();
    }

    public List<Person> deserialize(String sd) {
        List<Person> lp = new ArrayList<>();

        String[] parts = sd.split("\n");

        for (int i = 0; i < parts.length; i += LINES) {
            if (i + LINES - 1 >= parts.length) {
                break;
            }

            Long id = Long.parseLong(parts[i]);
            LocalDate birthday = LocalDate.parse(parts[i + 1]);
            String city = parts[i + 2];
            String firstName = parts[i + 3];
            String lastName = parts[i + 4];
            Integer postalCode = Integer.parseInt(parts[i + 5]);
            String street = parts[i + 6];

            Person p = new Person();

            p.setId(id);
            p.setBirthday(birthday);
            p.setCity(city);
            p.setFirstName(firstName);
            p.setLastName(lastName);
            p.setPostalCode(postalCode);
            p.setStreet(street);

            lp.add(p);
        }

        return lp;
    }

}
