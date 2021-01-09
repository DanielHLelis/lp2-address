package br.cefetmg.address.cli.controller.apps.person;

import br.cefetmg.address.cli.controller.apps.MenuApp;

public class MenuPersonApp extends MenuApp {

  public MenuPersonApp() {
    super("gerenciar pessoas");

    this.registerApp("i", new AddPersonApp());
    this.registerApp("d", new DeletePersonApp());
    this.registerApp("l", new ListPeopleApp());
    this.registerApp("o", new GetPersonApp());
    this.registerApp("a", new UpdatePersonApp());
  }
}
