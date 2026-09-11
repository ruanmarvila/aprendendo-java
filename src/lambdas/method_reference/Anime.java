package lambdas.method_reference;

public class Anime {
    private String titulo;
    private Integer episodios;

    public Anime(String titulo, Integer episodios) {
        this.titulo = titulo;
        this.episodios =  episodios;
    }

    @Override
    public String toString() {
        return "Título: "+titulo+", Episodios: "+episodios;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getEpisodios() {
        return episodios;
    }
}
