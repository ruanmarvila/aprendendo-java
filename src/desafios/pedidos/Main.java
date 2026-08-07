package desafios.pedidos;

public class Main {
    public static void main(String[] args) {
        Pedido pedido1 = new Pedido(StatusPedido.PENDENTE);

        try {
            pedido1.mudarStatus(StatusPedido.CANCELADO);
            pedido1.mudarStatus(StatusPedido.ENTREGUE);
        } catch (TransicaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }
}
