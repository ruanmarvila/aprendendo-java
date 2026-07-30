package desafios.produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public Produto criar(String nome, double preco) {
        Produto produto = new Produto(proximoId++, nome, preco);
        produtos.add(produto);
        return produto;
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Produto> listar() {
        return produtos;
    }

    public boolean atualizar(int id, String nome, double preco) {
        Produto produto = buscarPorId(id);
        if (produto == null) {
            return false;
        }

        produto.setNome(nome);
        produto.setPreco(preco);
        return true;
    }

    public void deletar(int id) {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        produtos.remove(produto);
        System.out.println("Produto removido com sucesso!");
    }
}
