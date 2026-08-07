package desafios.pedidos;

import java.util.List;
import java.util.Map;

public enum StatusPedido implements Transicionavel {
    PENDENTE,
    EM_PREPARO,
    PRONTO,
    SAIU_PARA_ENTREGA,
    ENTREGUE,
    RECUSADO,
    CANCELADO;

    private static final Map<StatusPedido, List<StatusPedido>> PROXIMOS_STATUS_VALIDOS = Map.of(
        PENDENTE, List.of(EM_PREPARO, RECUSADO, CANCELADO),
        EM_PREPARO, List.of(PRONTO, CANCELADO),
        PRONTO, List.of(SAIU_PARA_ENTREGA, CANCELADO),
        SAIU_PARA_ENTREGA, List.of(ENTREGUE, CANCELADO),
        ENTREGUE, List.of(),
        RECUSADO, List.of(),
        CANCELADO, List.of()
    );

    private static final Map<StatusPedido, String> STATUS_FORMATADO = Map.of(
        PENDENTE, "pendente",
        EM_PREPARO, "em preparo",
        PRONTO, "pronto",
        SAIU_PARA_ENTREGA, "saiu para entrega",
        ENTREGUE, "entregue",
        RECUSADO, "recusado",
        CANCELADO, "cancelado"
    );

    public List<StatusPedido> getProximosStatusValidos() {
        return PROXIMOS_STATUS_VALIDOS.getOrDefault(this, List.of());
    }

    public String getFormatado() {
        return STATUS_FORMATADO.getOrDefault(this, this.name());
    }

    @Override
    public boolean podeTransicionarPara(StatusPedido novoStatus) {
        return getProximosStatusValidos().contains(novoStatus);
    }

}
