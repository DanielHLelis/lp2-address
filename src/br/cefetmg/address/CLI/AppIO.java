package br.cefetmg.address;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class AppIO {
  private Scanner scanner;
  private PrintStream out;
  private InputStream in;

  private static AppIO instance;

  private AppIO(InputStream in, PrintStream out){
    this.setOut(out);
    this.setIn(in);
  }

  public boolean hasInstance(){
    return instance != null;
  }

  public static AppIO getInstance(){
    if(instance == null){
      return getInstance(System.in, System.out);
    }

    return instance;
  }

  public static AppIO getInstance(InputStream in, PrintStream out) {
    instance = new AppIO(in, out);
    return instance;
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

  private void setOut(PrintStream out) {
    this.out = out;
  }

  public InputStream getIn() {
    return in;
  }

  private void setIn(InputStream in) {
    this.in = in;
    this.scanner = new Scanner(this.in, StandardCharsets.UTF_8);
  }
}
