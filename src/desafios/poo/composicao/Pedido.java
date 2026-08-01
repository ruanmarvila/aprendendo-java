package desafios.poo.composicao;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private Cliente cliente;
    private List<Item> items;

    public Pedido(int numero, Cliente cliente, List<Item> items) {
        this.numero = numero;
        this.cliente = cliente;
        this.items = new ArrayList<>(items);
    }

    public void adicionarItem(Item item) {
        items.add(item);
        System.out.println("Item adicionado");
    }

    public double calcularTotal() {
        double soma = 0;

        for (Item item : items) {
            soma += item.getPreco();
        }
        return soma;
    }

    @Override
    public String toString() {
        return "O pedido nº" + numero + " do(a) cliente " + cliente.getNome() + " deu no total R$" + calcularTotal();
    }
}
