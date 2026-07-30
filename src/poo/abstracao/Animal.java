package poo.abstracao;

public abstract class Animal {
    protected String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    abstract void fazerBarulho();
}
