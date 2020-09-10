package br.cefetmg.address.CLI;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AppIO {
  private static AppIO instance;
  private Scanner scanner;
  private PrintStream out;
  private InputStream in;

  private AppIO(InputStream in, PrintStream out) {
    this.setOut(out);
    this.setIn(in);
  }

  public static AppIO getInstance() {
    if (instance == null) {
      return getInstance(System.in, System.out);
    }

    return instance;
  }

  public static AppIO getInstance(InputStream in, PrintStream out) {
    if (instance == null) {
      instance = new AppIO(in, out);
    }

    if (in != instance.in || out != instance.out) {
      instance.setIn(in);
      instance.setOut(out);
    }

    return instance;
  }

  public boolean hasInstance() {
    return instance != null;
  }

  public String query(String question) {
    out.print(question + " ");
    return scanner.nextLine();
  }

  public Scanner getScanner() {
    return scanner;
  }

  public PrintStream getOut() {
    return out;
  }

  public void setOut(PrintStream out) {
    this.out = out;
  }

  public InputStream getIn() {
    return in;
  }

  public void setIn(InputStream in) {
    this.in = in;
    this.scanner = new Scanner(this.in, StandardCharsets.UTF_8);
  }
}
