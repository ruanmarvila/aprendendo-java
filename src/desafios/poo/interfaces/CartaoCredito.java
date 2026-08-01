package desafios.poo.interfaces;

public class CartaoCredito implements Pagavel {
    public void pagar(double valor) {
        System.out.println("Pagamento de R$" + valor + " aprovado no cartão");
    }
}
