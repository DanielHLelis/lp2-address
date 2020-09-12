package br.cefetmg.address.CLI.subapps;

import br.cefetmg.address.CLI.AppIO;
import br.cefetmg.address.CLI.CLIApp;

/**
 * CLIApp to print the help guide
 */
public class AjudaApp implements CLIApp {

  @Override
  public void run(String[] params) {
    AppIO appIO = AppIO.getInstance();

    String ajudaStr = "Comandos:\n"
            + "\th: ajuda"
            + "\tq: sair"
            + "\tap: adicionar pessoa"
            + "\tdp: deletar pessoa"
            + "\tup: atualizar pessoa"
            + "\tlp: listar pessoas"
            + "\top: obter pessoa por id";

    appIO.getOut().println(ajudaStr);
  }
}
