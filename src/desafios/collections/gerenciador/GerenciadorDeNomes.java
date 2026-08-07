package desafios.collections.gerenciador;

import java.util.HashSet;

public class GerenciadorDeNomes {
    private HashSet<String> nomes;

    public GerenciadorDeNomes() {
        nomes = new HashSet<>();
    }

    public String adicionarNome(String nome) {
        if (nomes.add(nome)) {
            return "Nome adicionado com sucesso!";
        }
        return "Nome já existente";
    }

    public boolean existeNome(String nome) {
        return nomes.contains(nome);
    }

    public String removerNome(String nome) {
        if (nomes.remove(nome)) {
            return "Nome removido com sucesso!";
        }
        return "Nome não encontrado";
    }

    public void listarNomes() {
        if (nomes.isEmpty()) {
            System.out.println("Lista vazia");
        } else {
            for (String nome : nomes) {
                System.out.println("Nome: " + nome);
            }
        }
    }
}
