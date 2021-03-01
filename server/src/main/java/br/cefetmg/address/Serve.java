package br.cefetmg.address;

import br.cefetmg.address.view.PersonView;
import br.cefetmg.address.view.ViewMethod;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Serve {
    public static void main(String[] args) {
        Map<String, ViewMethod> commands = new HashMap<>();

        PersonView personView = new PersonView();
        commands.put("person/list", personView::list);
        commands.put("person/query", personView::query);
        commands.put("person/insert", personView::insert);
        commands.put("person/update", personView::update);
        commands.put("person/remove", personView::remove);

        try {
            ServerSocket ss = new ServerSocket(11337);

            System.out.println("Server listening at localhost:11337");

            while (true) {
                try {
                    Socket socket = ss.accept();

                    System.out.println("Incoming connection");

                    InputStream inputStream = socket.getInputStream();
                    OutputStream outputStream = socket.getOutputStream();

                    DataInputStream dis = new DataInputStream(inputStream);
                    String q = dis.readUTF();

                    System.out.println("Request at " + q);

                    commands.get(q).handle(inputStream, outputStream);
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        } catch (IOException | NullPointerException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.getLocalizedMessage());
//            System.err.println(ex);
        }
    }
}
