package br.cefetmg.address.CLI;

/**
 * Simple interface that defines a runnable CLI app/sub-app
 */
public interface CLIApp {

  /**
   * run the CLI app/sub-app
   * @param params list of pre-sent params, often not used
   */
  void run(String[] params);

}
