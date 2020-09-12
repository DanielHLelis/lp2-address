package br.cefetmg.address.CLI;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * IO centralizer, manages the app's IO in a central place as a singleton
 */
public class AppIO {
  private static AppIO instance;
  private Scanner scanner;
  private PrintStream out;
  private InputStream in;

  private AppIO(InputStream in, PrintStream out) {
    this.setOut(out);
    this.setIn(in);
  }

  /**
   * Recover/creates the AppIO instance
   *
   * @return AppIO current instance. If there's not one, creates one with STDIN and STDOUT
   */
  public static AppIO getInstance() {
    if (instance == null) {
      return getInstance(System.in, System.out);
    }

    return instance;
  }

  /**
   * Creates/modify the AppIO instance based over the given params
   *
   * @param in the InputStream
   * @param out the OutputStream
   * @return a new/current instance with the given streams
   */
  public static AppIO getInstance(InputStream in, PrintStream out) {
    if (instance == null) {
      instance = new AppIO(in, out);
      return instance;
    }

    if (in != instance.in || out != instance.out) {
      instance.setIn(in);
      instance.setOut(out);
    }

    return instance;
  }

  /**
   * @return if there's an existing instance
   */
  public boolean hasInstance() {
    return instance != null;
  }

  /**
   * asks a question and returns the answer as a string (with spaces, without line break)
   * @param question the query's question
   * @return the input given
   */
  public String query(String question) {
    out.print(question + " ");
    return scanner.nextLine();
  }

  /**
   * @return the current Scanner instance
   */
  public Scanner getScanner() {
    return scanner;
  }

  /**
   * @return the current PrintStream
   */
  public PrintStream getOut() {
    return out;
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  /**
   * @return the current InputStream
   */
  public InputStream getIn() {
    return in;
  }

  public void setIn(InputStream in) {
    this.in = in;
    this.scanner = new Scanner(this.in, StandardCharsets.UTF_8);
  }
}
