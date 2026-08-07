package desafios.pedidos;

public class Pedido {
    private StatusPedido status;

    public Pedido(StatusPedido status) {
        this.status = status;
    }

    public void mudarStatus(StatusPedido novoStatus) {
        if (!status.podeTransicionarPara(novoStatus)) {
            throw new TransicaoInvalidaException(
                "Status " + status.getFormatado() + " não pode mudar para " + novoStatus.getFormatado()
            );
        }
        status = novoStatus;
        System.out.println("Pedido " + status.getFormatado());
    }
}

