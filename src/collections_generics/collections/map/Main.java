package collections_generics.collections.map;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        // HashMap é baseado em hashCode(), por isso é ordenado
        Map<String, Integer> nomes = new HashMap<>();

        nomes.put("Ana", 18);
        nomes.put("Ruan", 19);
        nomes.put("Gabriel", 24);

        for (Map.Entry<String, Integer> i : nomes.entrySet()) {
            System.out.println(i.getKey()+": "+i.getValue());
        } 

        System.out.println("------------------");

        // LinkedHashMap mantém a ordem de inserção e é duplamente ligado
        Map<String, Double> produtos = new LinkedHashMap<>();

        produtos.put("Celular", 2500.0);
        produtos.put("RAM", 1_000_000.0);
        produtos.put("RAM", 355.99); // Vai sobresecrever o valor
        produtos.put("Kit de pintura", 355.99);

        System.out.println(produtos);

        System.out.println("------------------");

        // TreeMap é ordenado pela compareTo da classe
        Map<String, Integer> frutas = new TreeMap<>();

        frutas.put("Banana", 10);
        frutas.put("Kiwi", 55);
        frutas.put("Goiaba", 9);

        System.out.println(frutas);
    }
}
