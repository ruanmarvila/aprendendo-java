package desafios.optional;

public class Endereco {
    private String estadoSigla;
    private String cidade;

    public Endereco(String estadoSigla, String cidade) {
        this.estadoSigla = estadoSigla;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return cidade+"-"+estadoSigla;
    }

    public String getEstado() {
        return estadoSigla;
    }

    public String getCidade() {
        return cidade;
    }
}
