package desafios.collections.estoque;

import java.util.HashMap;
import java.util.Map;

public class GerenciadorDeEstoque {
    private HashMap<String, Integer> produtos;

    public GerenciadorDeEstoque() {
        produtos = new HashMap<>();
    }

    public String adicionarOuAtualizar(String produto, Integer quantidade) {
        if (quantidade <= 0) {
            return "Quantidade inválida";
        }

        if (produtos.containsKey(produto)) {
            produtos.put(produto, produtos.get(produto) + quantidade);
            return "Produto atualizada com sucesso!";
        }

        produtos.put(produto, quantidade);
        return "Produto adicionado ao estoque.";
    }

    public String removerProduto(String produto) {
        if (produtos.remove(produto) != null) {
            return "Produto removido com sucesso!";
        }
        return "Produto não encontrado";
    }

    public void exibirProdutos() {
        for (Map.Entry<String, Integer> mapa : produtos.entrySet()) {
            System.out.println("Produto: " + mapa.getKey() + " | Quantidade: " + mapa.getValue());
        }
    }

    public void consultarProduto(String produto) {
        if (produtos.containsKey(produto)) {
            Integer quantidade = produtos.get(produto);
            System.out.println("Produto: " + produto + " | Quantidade: " + quantidade);
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}
