package poo.abstracao;

public class Cachorro extends Animal implements Corredor{

    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    void fazerBarulho() {
        System.out.println("Au au");
    }

    @Override
    public void correr() {
        System.out.println(nome + " está correndo");
    }
}
