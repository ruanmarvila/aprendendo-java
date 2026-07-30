package poo.polimorfismo;

public class Barco extends Veiculo{

    public Barco(String nome) {
        super(nome);
    }

    @Override
    public void locomover() {
        System.out.println("Indo pela água");
    }

}
