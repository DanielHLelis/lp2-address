package br.cefetmg.address.cli.controller.apps;

import br.cefetmg.address.cli.view.HelpView;
import br.cefetmg.address.controller.ControllerException;

import java.util.Map;

public class HelpApp extends CLIApp {
  private Map<String, CLIApp> menus;

  public HelpApp(Map<String, CLIApp> menus) {
    this.menus = menus;
  }

  @Override
  public String getDescription() {
    return "este menu";
  }

  @Override
  public void run() throws ControllerException {
    HelpView view = new HelpView(menus);

    out.println(view.render());
  }
}
