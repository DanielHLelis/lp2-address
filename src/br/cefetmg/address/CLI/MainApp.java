package br.cefetmg.address.CLI;

import br.cefetmg.address.CLI.subapps.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
TO-DO

CLI multi camada
    Entrar em um app que é outra "main"
Adicionar apps por pacote, ao invés de manual
*/

public class MainApp {
    private final Scanner scanner;

    public MainApp() {
        scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        // CLI: Command Line Interface

        (new MainApp()).run();
    }

    public String query(String question) {
        System.out.print(question + " ");
        return scanner.nextLine();
    }

    public void run() {
        Map<String, CLIApp> apps = new HashMap<>();

        apps.put("h", new AjudaApp());
        apps.put("ap", new AdicionarPessoaApp());
        apps.put("dp", new DeletarPessoaApp());
        apps.put("up", new AtualizarPessoaApp());
        apps.put("op", new ObterPessoaApp());
        apps.put("lp", new ListarPessoasApp());

        while (true) {
            String comando = query("Digite o comando (h para ajuda):");
            String[] params = comando.split(" ");

            if (params[0].equals("q")) {
                break;
            }

            for (String k : apps.keySet()) {
                if (k.equals(params[0])) {
                    apps.get(k).run(this, params);
                }
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }


}
