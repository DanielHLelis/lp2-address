package br.cefetmg.address.cli.controller;

import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * IO centralizer, manages the app's IO in a central place as a singleton
 */
public class IOController {
  private static IOController instance;
  private Scanner scanner;
  private PrintStream out;
  private InputStream in;

  private IOController(InputStream in, PrintStream out) {
    this.setOut(out);
    this.setIn(in);
  }

  /**
   * Recover/creates the IOController instance
   *
   * @return IOController current instance. If there's not one, creates one with STDIN and STDOUT
   */
  public static IOController getInstance() {
    if (instance == null) {
      return getInstance(System.in, System.out);
    }

    return instance;
  }

  /**
   * Creates/modify the IOController instance based over the given params
   *
   * @param in  the InputStream
   * @param out the OutputStream
   * @return a new/current instance with the given streams
   */
  public static IOController getInstance(InputStream in, PrintStream out) {
    if (instance == null) {
      instance = new IOController(in, out);
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
