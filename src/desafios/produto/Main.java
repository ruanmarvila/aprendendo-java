package desafios.produto;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProdutoService service = new ProdutoService();
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n === MENU ===");
            System.out.println("1 - CRIAR PRODUTO");
            System.out.println("2 - BUSCAR PRODUTO");
            System.out.println("3 - LISTAR PRODUTOS");
            System.out.println("4 - ATUALIZAR PRODUTO");
            System.out.println("5 - DELETAR PRODUTO");
            System.out.println("0 - SAIR");
            
            System.out.print("Escolha uma das opções: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 0 -> System.out.println("Encerrando programa...");
                case 1 -> {
                    System.out.print("Digite um nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite um preço: ");
                    double preco = sc.nextDouble();


                    Produto produto = service.criar(nome, preco);
                    System.out.println(produto);
                }
                case 2 -> {
                    System.out.print("Digite um id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    Produto produto = service.buscarPorId(id);
                    System.out.println(produto);
                }
                case 3 -> {
                    List<Produto> lista = service.listar();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhuma produto cadastrado.");
                    } else {
                        for (Produto p : lista) {
                            System.out.println(p);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Digite um id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Digite um nome: ");
                    String nome = sc.nextLine();

                    System.out.print("Digite um preco: ");
                    double preco = sc.nextDouble();

                    boolean atualizado = service.atualizar(id, nome, preco);
                    System.out.println(atualizado ? "Atualizado" : "Produto não encontrado");
                }
                case 5 -> {
                    System.out.print("Digite um id: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    service.deletar(id);
                }
                default -> System.out.println("Opção inválida.");
            }

            if (opcao == 0) {
                break;
            }
        }
        sc.close();
    }
}
