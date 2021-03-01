package br.cefetmg.address.view;

import br.cefetmg.address.controller.PersonController;
import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.model.serializer.PersonSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class PersonView {

    public void update(InputStream in, OutputStream out) throws ViewException {
        try {
            DataInputStream dis = new DataInputStream(in);

            PersonController personController = new PersonController();
            PersonSerializer personSerializer = new PersonSerializer();

            String updatedData = dis.readUTF();
            Person newPerson = personSerializer.deserialize(updatedData).get(0);

            personController.updatePerson(newPerson);
        } catch (Exception ex) {
            throw new ViewException(ex);
        }
    }

    public void insert(InputStream in, OutputStream out) throws ViewException {
        try {
            DataOutputStream dos = new DataOutputStream(out);
            DataInputStream dis = new DataInputStream(in);

            PersonController personController = new PersonController();
            PersonSerializer personSerializer = new PersonSerializer();

            String newData = dis.readUTF();
            Person newPerson = personSerializer.deserialize(newData).get(0);

            personController.addPerson(newPerson);

            dos.writeUTF(personSerializer.serialize(newPerson));
        } catch (Exception ex) {
            throw new ViewException(ex);
        }
    }

    public void list(InputStream in, OutputStream out) throws ViewException {
        try {
            DataOutputStream dos = new DataOutputStream(out);

            PersonController personController = new PersonController();
            PersonSerializer personSerializer = new PersonSerializer();
            List<Person> personList = personController.getAllPeople();

            dos.writeUTF(personSerializer.serialize(personList));
        } catch (Exception ex) {
            throw new ViewException(ex);
        }
    }

    public void query(InputStream in, OutputStream out) throws ViewException {
        try {
            DataInputStream dis = new DataInputStream(in);
            DataOutputStream dos = new DataOutputStream(out);

            PersonController personController = new PersonController();
            PersonSerializer personSerializer = new PersonSerializer();

            long id = dis.readLong();

            Person person = personController.getPersonById(id);

            dos.writeUTF(personSerializer.serialize(person));
        } catch (Exception ex) {
            throw new ViewException(ex);
        }
    }

    public void remove(InputStream in, OutputStream out) throws ViewException {
        try {
            DataInputStream dis = new DataInputStream(in);
            DataOutputStream dos = new DataOutputStream(out);

            PersonController personController = new PersonController();
            PersonSerializer personSerializer = new PersonSerializer();

            long id = dis.readLong();

            Person person = personController.deletePerson(id);

            dos.writeUTF(personSerializer.serialize(person));
        } catch (Exception ex) {
            throw new ViewException(ex);
        }
    }
}
