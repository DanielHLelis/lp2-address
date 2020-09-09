package br.cefetmg.address.CLI;

import br.cefetmg.address.CLI.MainApp;

public class AjudaApp implements CLIApp{
    
    @Override
    public void run(MainApp main, String[] params){
        String ajudaStr = "Comandos:\n"
                + "\th: ajuda"
                + "\tq: sair"
                + "\tap: adicionar pessoa"
                + "\tdp: deletar pessoa"
                + "\tup: atualizar pessoa"
                + "\tlp: listar pessoas"
                + "\top: obter pessoa por id";
        
        System.out.println(ajudaStr);
    }
}
