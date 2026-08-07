package collections_generics.collections.list.comparacao;

import java.util.Objects;

public class Anime implements Comparable<Anime> {
    private Long id;
    private String nome;
    private double nota;
    private int quantidadeEp;

    public Anime(Long id, String nome, double nota) {
        this.id = id;
        this.nome = nome;
        this.nota = nota;
    }

    public Anime(Long id, String nome, double nota, int quantidadeEp) {
        this(id, nome, nota);
        this.quantidadeEp = quantidadeEp;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getNota() {
        return nota;
    }

    public int getQuantidadeEp() {
        return quantidadeEp;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Anime outroAnime = (Anime) obj;
        return Double.compare(outroAnime.nota, nota) == 0 && id.equals(outroAnime.id) && nome.equals(outroAnime.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, nota);
    }

    @Override
    public String toString() {
       return "id: "+id+" | nome: "+nome+" | nota: "+nota;
    }

    @Override
    public int compareTo(Anime outroAnime) {
        return this.nome.compareTo(outroAnime.getNome());
    }
}
