package desafios.poo.interfaces;

public class Pix implements Pagavel{
    public void pagar(double valor) {
        System.out.println("Pix de R$" + valor + " enviado");
    }
}
