package lambdas.optionals.exc;

public class Manga {
    private Integer id;
    private String titulo;
    private int capitulos;

    public Manga(Integer id, String titulo, int capitulos) {
        this.id = id;
        this.titulo = titulo;
        this.capitulos = capitulos;
    }

    @Override
    public String toString() {
        return "ID: "+id+", Título: "+titulo+", Capítulos: "+capitulos;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

}
