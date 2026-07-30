package poo.polimorfismo;

public class Veiculo {
    protected String nome;

    public Veiculo(String nome) {
        this.nome = nome;
        }

    void locomover() {
        System.out.println("Locomovendo-se");
    };

}
