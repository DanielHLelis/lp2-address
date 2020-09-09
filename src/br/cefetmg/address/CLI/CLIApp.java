package br.cefetmg.address.CLI;

import br.cefetmg.address.CLI.MainApp;

public interface CLIApp {
    public abstract void run(MainApp main, String[] params);
}
