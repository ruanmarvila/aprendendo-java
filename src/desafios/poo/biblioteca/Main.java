package desafios.poo.biblioteca;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Midia filme = new Filme("Cidade Rosa", "João Neto Jr.", 101);
        Midia livro = new Livro("Vizinhança", "Maria Rodriguez", 321);
        Midia fantasia = new Livro("Terra Mágica", "Tom Von Bault", 451);

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.adicionar(filme);
        biblioteca.adicionar(livro);
        biblioteca.adicionar(fantasia);

        try {
            biblioteca.emprestar("Vizinhança");
            biblioteca.emprestar("Vizinhança");
        } catch (MidiaIndisponivelException e) {
            System.out.println(e.getMessage());
        } finally {
            List<Midia> lista = biblioteca.listarDisponiveis();
            for (Midia midia : lista) {
                System.out.println(midia.exibirFicha());
            }
        }
    }
}
