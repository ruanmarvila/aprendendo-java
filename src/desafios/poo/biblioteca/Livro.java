package desafios.poo.biblioteca;

public class Livro extends Midia {
    private String autor;
    private int numeroPaginas;

    public Livro(String titulo, String autor, int numeroPaginas) {
        super(titulo, true);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String exibirFicha() {
        return "Livro '" + titulo + "' do autor " + autor + " tem " + numeroPaginas + " páginas. Status: " + estaDisponivel();
    }
}
