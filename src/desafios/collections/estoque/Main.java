package desafios.collections.estoque;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeEstoque gerenciador = new GerenciadorDeEstoque();

        int opt = 0;

        do {
            opt = Integer.parseInt(JOptionPane.showInputDialog(
                "1 - Adicionar/Atualizar Produto\n"
                + "2 - Remover Produto\n"
                + "3 - Consultar Produto\n"
                + "4 - Listar Produtos\n"
                + "0 - Sair"
            ));

            switch (opt) {
                case 0 -> {System.out.println("Saindo...");}
                case 1 -> {
                    String nome = JOptionPane.showInputDialog("Digite o nome do produto");
                    Integer qntd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade"));

                    System.out.println(gerenciador.adicionarOuAtualizar(nome, qntd));
                }
                case 2 -> {
                    System.out.println(gerenciador.removerProduto(JOptionPane.showInputDialog("Digite o nome do produto")));
                }
                case 3 -> {
                    gerenciador.consultarProduto(JOptionPane.showInputDialog("Digite o nome do produto"));
                }
                case 4 -> {
                    gerenciador.exibirProdutos();
                }
                default -> {System.out.println("Opção inválida");}
            }
        } while (opt != 0);
    }
}
