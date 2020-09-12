package br.cefetmg.address.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConnectionFactory {

  private final static String dbDriver = "org.postgresql.Driver"; // The driver class to use
  private final static String protocol = "jdbc:postgresql"; // The driver protocol
  private final static String urlParams = "useUnicode=true&amp;characterEncoding=utf8"; // URL params

  private final static String DEFAULT_HOST = "localhost";
  private final static String DEFAULT_PORT = "5432";
  private final static String DEFAULT_DB = "address";
  private final static String DEFAULT_USER = "postgres";
  private final static String DEFAULT_PASS = "postgres";

  private final static Map<String, String> PARAMS = getParams();

  private static String getUrl() {
    return String.format("%s://%s:%s/%s?%s", protocol, PARAMS.get("host"), PARAMS.get("port"), PARAMS.get("db"), urlParams);
  }

  private static Map<String, String> getParams() {
    Map<String, String> params = new HashMap<>();

    // DEFAULT

    params.put("host", DEFAULT_HOST);
    params.put("port", DEFAULT_PORT);
    params.put("db", DEFAULT_DB);
    params.put("user", DEFAULT_USER);
    params.put("pass", DEFAULT_PASS);

    // ENV

    String envHost = System.getenv("DB_HOST");
    String envPort = System.getenv("DB_PORT");
    String envDb = System.getenv("DB_DATABASE");
    String envUser = System.getenv("DB_USER");
    String envPass = System.getenv("DB_PASS");

    if (envHost != null && !envHost.equals("")) {
      params.put("host", envHost);
    }

    if (envPort != null && !envPort.equals("")) {
      params.put("port", envPort);
    }

    if (envDb != null && !envDb.equals("")) {
      params.put("db", envDb);
    }

    if (envUser != null && !envUser.equals("")) {
      params.put("user", envUser);
    }

    if (envPass != null && !envPass.equals("")) {
      params.put("pass", envPass);
    }

    return params;
  }

  /**
   * Return's a database connection based on the environment configuration
   *
   * @return the database connection
   * @throws ClassNotFoundException if the driver isn't present
   * @throws SQLException from {@link DriverManager}
   */
  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName(dbDriver);
    return DriverManager.getConnection(getUrl(), PARAMS.get("user"), PARAMS.get("pass"));
  }
}
