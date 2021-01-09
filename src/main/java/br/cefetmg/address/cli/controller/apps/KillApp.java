package br.cefetmg.address.cli.controller.apps;

import br.cefetmg.address.core.controller.ControllerException;

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
