package enums.construtor;

public enum Moeda {
    REAL(1.0),
    DOLAR(5.20),
    EURO(5.87);

    private final double cotacaoEmReais;

    Moeda(double cotacaoEmReais) {
        this.cotacaoEmReais = cotacaoEmReais;
    }

    public double getCotacaoEmReais() {
        return cotacaoEmReais;
    }

    public double converterParaReais(double valor) {
        return valor * getCotacaoEmReais();
    }
}
