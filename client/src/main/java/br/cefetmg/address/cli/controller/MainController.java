package br.cefetmg.address.cli.controller;

import br.cefetmg.address.cli.controller.apps.person.MenuPersonApp;

public class MainController {
  public static void main(String[] args) {
    MenuPersonApp menuPersonApp = new MenuPersonApp();

    menuPersonApp.run();
  }
}
