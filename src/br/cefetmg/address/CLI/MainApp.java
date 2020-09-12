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

    apps.put("h", new AjudaApp());
    apps.put("ap", new AdicionarPessoaApp());
    apps.put("dp", new DeletarPessoaApp());
    apps.put("up", new AtualizarPessoaApp());
    apps.put("op", new ObterPessoaApp());
    apps.put("lp", new ListarPessoasApp());

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
