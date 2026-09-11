package streams;

import java.util.Objects;

public class LightNovel {
    private String titulo;
    private double preco;
    private Categoria categoria;

    public LightNovel(String titulo, double preco) {
        this.titulo = titulo;
        this.preco = preco;
    }

    public LightNovel(String titulo, double preco, Categoria categoria) {
        this.titulo = titulo;
        this.preco = preco;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Título: "+titulo+", Preço: R$"+preco+", Categoria: "+categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LightNovel ln = (LightNovel) obj;
        return titulo.equals(ln.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(titulo);
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
