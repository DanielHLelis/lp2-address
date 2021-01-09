package br.cefetmg.address.cli.controller.apps;

import br.cefetmg.address.cli.controller.IOController;
import br.cefetmg.address.cli.view.QueryView;
import br.cefetmg.address.core.controller.ControllerException;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class CLIApp {
  protected final static boolean DEBUG = true;

  protected Scanner scanner = IOController.getInstance().getScanner();
  protected PrintStream out = IOController.getInstance().getOut();

  public abstract String getDescription();

  public abstract void run() throws ControllerException;

  protected String query(String question) {
    QueryView queryView = new QueryView(question);
    out.print(queryView.render());

    String answer = scanner.nextLine();
    return answer;
  }

}
