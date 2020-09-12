package br.cefetmg.address.CLI;

import br.cefetmg.address.CLI.subapps.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The CLI entry point
 */
public class MainApp implements CLIApp {
  public static void main(String[] args) {
    (new MainApp()).run(args);
  }

  @Override
  public void run(String[] params) {
    AppIO appIO = AppIO.getInstance();

    Map<String, CLIApp> apps = new HashMap<>();

    apps.put("h", new HelpApp());
    apps.put("ap", new AddPersonApp());
    apps.put("dp", new DeletePersonApp());
    apps.put("up", new UpdatePersonApp());
    apps.put("op", new GetPersonApp());
    apps.put("lp", new ListPersonsApp());

    while (true) {
      String command = appIO.query("Digite o comando (h para ajuda):");
      String[] newParams = command.split(" ");

      if (newParams[0].equals("q")) {
        break;
      }

      for (String k : apps.keySet()) {
        if (k.equals(newParams[0])) {
          apps.get(k).run(newParams);
        }
      }
    }
  }
}
