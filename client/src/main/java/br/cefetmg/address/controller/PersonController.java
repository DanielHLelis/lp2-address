package br.cefetmg.address.controller;

import br.cefetmg.address.model.domain.Person;
import br.cefetmg.address.model.serializer.PersonSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

public class PersonController {
    private static final String HOST = "localhost";
    private static final int PORT = 11337;

    /*
     * "person/list"
     * "person/query"
     * "person/insert"
     * "person/update"
     * "person/remove"
     * */

    public void addPerson(Person p) throws ControllerException {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            PersonSerializer personSerializer = new PersonSerializer();

            dos.writeUTF("person/insert");
            dos.writeUTF(personSerializer.serialize(p));

            Person newPerson = personSerializer.deserialize(dis.readUTF()).get(0);
            p.setId(newPerson.getId());
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    public Person deletePerson(Long id) throws ControllerException {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            PersonSerializer personSerializer = new PersonSerializer();

            dos.writeUTF("person/remove");
            dos.writeLong(id);

            String removedData = dis.readUTF();

            return personSerializer.deserialize(removedData).get(0);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    public Person deletePerson(Person p) throws ControllerException {
        return deletePerson(p.getId());
    }

    public void updatePerson(Person p) throws ControllerException {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            PersonSerializer personSerializer = new PersonSerializer();

            dos.writeUTF("person/update");
            dos.writeUTF(personSerializer.serialize(p));
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    public Person getPersonById(Long id) throws ControllerException {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            PersonSerializer personSerializer = new PersonSerializer();

            dos.writeUTF("person/query");
            dos.writeLong(id);

            String queriedData = dis.readUTF();

            return personSerializer.deserialize(queriedData).get(0);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }

    public List<Person> getAllPeople() throws ControllerException {
        try {
            Socket socket = new Socket(HOST, PORT);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            PersonSerializer personSerializer = new PersonSerializer();

            dos.writeUTF("person/list");

            String listData = dis.readUTF();

            return personSerializer.deserialize(listData);
        } catch (Exception e) {
            throw new ControllerException(e);
        }
    }
}
