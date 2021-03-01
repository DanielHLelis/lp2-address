package br.cefetmg.address.cli.controller.apps;

import br.cefetmg.address.cli.view.ErrorView;
import br.cefetmg.address.cli.view.QueryView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MenuApp extends CLIApp {

  private String description;
  private Map<String, CLIApp> menus;
  private boolean help;

  public MenuApp(String description) {
    this(description, new HashMap<>());
  }

  public MenuApp(String description, Map<String, CLIApp> menus) {
    this(description, menus, true);
  }

  public MenuApp(String description, Map<String, CLIApp> menus, boolean help) {
    this.description = description;
    this.menus = menus;
    this.help = help;

    this.registerApp("q", new KillApp());

    if (help) {
      this.registerApp("h", new HelpApp(this.menus));
    }
  }

  public void unregisterApp(String command) {
    menus.remove(commandFormat(command));
  }

  public void registerApp(String command, CLIApp app) {
    menus.put(commandFormat(command), app);
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void run() {
    String query = "Digite um comando";
    if (help) {
      query += " (h para ajuda)";
    }
    QueryView queryView = new QueryView(query);

    while (true) {
      out.print(queryView);

      String option = scanner.nextLine();
      option = commandFormat(option);

      CLIApp app = menus.get(option);

      if (app == null) {
        ErrorView errorView = new ErrorView("Opção não encontrada");
        out.println(errorView);
        continue;
      }

      if (app instanceof KillApp) {
        break;
      }
      try {
        app.run();
      } catch (Exception e) {
        String mensagem = e.getMessage();

        if(e instanceof IllegalArgumentException){
          mensagem = "entrada inválida (" + e.getMessage() + ")";
        }

        ErrorView errorView = new ErrorView(mensagem);
        out.println(errorView);

        if(DEBUG){
          String q = query("Mostrar stack trace (y/N)").trim();
          if(q.equals("y")){
            e.printStackTrace(out);
          }
        }
        continue;
      }
    }
  }

  private String commandFormat(String command) {
    return command.trim().toLowerCase(Locale.ROOT);
  }
}
