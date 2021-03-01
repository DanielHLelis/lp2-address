package br.cefetmg.address.cli.view;

import br.cefetmg.address.cli.controller.apps.CLIApp;

import java.util.Map;

public class HelpView extends View {

  private Map<String, CLIApp> menus;

  public HelpView(Map<String, CLIApp> menus) {
    this.menus = menus;
  }

  @Override
  public String render() {
    StringBuilder out = new StringBuilder();

    for (var key : menus.keySet()) {
      out.append("\t");
      out.append(key);
      out.append(": ");
      out.append(menus.get(key).getDescription());
      out.append("\n");
    }

    return out.toString();
  }
}
