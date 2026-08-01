package desafios.poo.composicao;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Maria");

        Item item1 = new Item("Coca-Cola 2L", 12.50);
        Item item2 = new Item("2 X-Egg", 50.00);

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        Pedido pedido1 = new Pedido(1, cliente1, items);
        System.out.println(pedido1.toString());
        
        Item item3 = new Item("Batata Frita", 19.99);
        pedido1.adicionarItem(item3);
        System.out.println(pedido1.toString());

    }
}
