package desafios.optional;

public class Usuario {
    private Integer id;
    private String nome;
    private int idade;
    private Endereco endereco;

    public Usuario(Integer id, String nome, int idade, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
