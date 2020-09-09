package br.cefetmg.address.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
TO-DO

- Adicionar arquivo .conf e/ou variável
*/

public class ConnectionFactory {
    
    private final static String dbDriver = "org.postgresql.Driver";
    private final static String protocol = "jdbc:postgresql";    
    private final static String host = "localhost";
    private final static String port = "5432";
    private final static String database = "address";
    private final static String user = "postgres";
    private final static String pass = "postgres";
    
    private static String getUrl() {
        return String.format("%s://%s:%s/%s",protocol, host, port, database);
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(getUrl(), user, pass);
    }
}
