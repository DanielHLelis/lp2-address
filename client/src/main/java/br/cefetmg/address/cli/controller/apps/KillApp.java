package br.cefetmg.address.cli.controller.apps;

import br.cefetmg.address.controller.ControllerException;

public class KillApp extends CLIApp {

  @Override
  public String getDescription() {
    return "sair";
  }

  @Override
  public void run() throws ControllerException {
    System.exit(0);
  }
}
