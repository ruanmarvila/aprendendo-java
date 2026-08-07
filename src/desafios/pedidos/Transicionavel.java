package desafios.pedidos;

public interface Transicionavel {
	boolean podeTransicionarPara(StatusPedido novoStatus);
}
