package poo.excecoes;

public class ContaBancaria {
    private double saldo;

    public ContaBancaria(double saldo) {
        setSaldo(saldo);
    }

    public double getSaldo() {
        return saldo;
    }

    private void setSaldo(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("Saldo não pode ser nagativo");
        }
        this.saldo = saldo;
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("O valor precisa ser maior que 0");
        }
        saldo += valor;
    }

    public void sacar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }

        if (valor > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        
        saldo -= valor;
    }
}
