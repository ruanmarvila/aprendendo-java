package desafios.pedidos;

public class TransicaoInvalidaException extends RuntimeException {
    public TransicaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}
