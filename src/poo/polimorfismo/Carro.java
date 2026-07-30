package poo.polimorfismo;

public class Carro extends Veiculo {
    private int qntdRodas;

    public Carro(String nome, int qntdRodas) {
        super(nome);
        this.qntdRodas = qntdRodas;
    }

    public int getQntdRodas() {
        return qntdRodas;
    }

    @Override
    public void locomover() {
        System.out.println("Indo por terra");
    }

}
