package desafios.collections.gerenciador;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeNomes gerenciador = new GerenciadorDeNomes();

        int opt = 0;

        do {
            opt = Integer.parseInt(JOptionPane.showInputDialog(
                "1 - Adicionar Nome\n"
                + "2 - Verificar Nome\n"
                + "3 - Remover Nome\n"
                + "4 - Listar Nomes\n"
                + "0 - Sair"
            ));

            switch(opt) {
                case 0 -> {System.out.println("Saindo...");}
                case 1 -> {
                    System.out.println(gerenciador.adicionarNome(JOptionPane.showInputDialog("Digite um nome")));
                }
                case 2 -> {
                    System.out.println(gerenciador.existeNome(JOptionPane.showInputDialog("Digite um nome")));
                }
                case 3 -> {
                    System.out.println(gerenciador.removerNome(JOptionPane.showInputDialog("Digite um nome")));
                }
                case 4 -> {
                    gerenciador.listarNomes();
                }
                default -> {System.out.println("Opção Inválida");}
            }
        } while (opt != 0);
    }
}
