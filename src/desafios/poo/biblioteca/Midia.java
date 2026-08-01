package desafios.poo.biblioteca;

public abstract class Midia {
    protected String titulo;
    protected boolean disponivel;

    public Midia(String titulo, boolean disponivel) {
        this.titulo = titulo;
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public String estaDisponivel() {
        return disponivel ? "Disponível" : "Indisponível";
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public abstract String exibirFicha();
}
