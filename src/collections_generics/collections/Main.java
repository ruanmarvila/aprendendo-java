package collections_generics.collections;

import java.util.*; 

public class Main {
    public static void main(String[] args) {
        //List -> permite duplicatas
        List<String> list = new ArrayList<>();
        list.add("João");
        list.add("Sofia");
        list.add("Maria");

        System.out.println(list.get(1)); // Sofia

        // Set -> não permite duplicatas
        // HashSet
        Set<Integer> conjunto = new HashSet<>();

        conjunto.add(2);
        conjunto.add(0);
        conjunto.add(2); // não vai dar erro
        conjunto.add(26);

        System.out.println(conjunto); // Varia, o HashSet não mantém a ordem de inserção
        System.out.println(conjunto.contains(26)); // true

        //LinkedHashSet
        Set<String> conjuntosLinkados = new LinkedHashSet<>();

        conjuntosLinkados.add("A");
        conjuntosLinkados.add("B");
        conjuntosLinkados.add("C");

        System.out.println(conjuntosLinkados); // [A, B, C] -> LinkedHashSet mantém a ordem


        // Map -> Chave, Valor -> Chave é única, Valor pode ser repetido
        // HashMap
        Map<String, String> dicionario = new HashMap<>();

        dicionario.put("Nome", "Isadora");
        dicionario.put("Sobrenome", "Basile");

        System.out.println(dicionario.get("Nome")); // Isadora
        System.out.println(dicionario); //HashMap não mantém a ordem

        // LinkedHashMap
        Map<String, Double> produtos = new LinkedHashMap<>();

        produtos.put("Smartphone", 1999.99);
        produtos.put("Mangá", 55.69);
        produtos.put("Tênis", 350.00);

        System.out.println(produtos.get("Tênis")); // 350.0
        // LinkedHashMap mantém a ordem
        System.out.println(produtos); // {Smartphone=1999.99, Mangá=55.69, Tênis=350.0}
    }
}
