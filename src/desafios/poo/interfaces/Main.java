package desafios.poo.interfaces;

public class Main {
    public static void main(String[] args) {
        Pagavel cartao = new CartaoCredito();
        Pagavel pix = new Pix();

        processarPagamento(cartao, 1000);
        processarPagamento(pix, 1000, 10);
    }

    public static void processarPagamento(Pagavel formaPagamento, double valor) {
        formaPagamento.pagar(valor);
    }

    public static void processarPagamento(Pagavel formaPagamento, double valor, int descontoPercentual) {
        double desconto = valor * descontoPercentual / 100;
        valor -= desconto;
        formaPagamento.pagar(valor);
    }
}
