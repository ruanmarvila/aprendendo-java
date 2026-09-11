package lambdas;

public class Carro {
    private String nome = "Fusca";
    private String cor;
    private int ano;

    public Carro(String cor, int ano) {
        this.cor = cor;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "Nome: "+nome+" | cor: "+cor+" | ano: "+ano;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public int getAno() {
        return ano;
    }
}
