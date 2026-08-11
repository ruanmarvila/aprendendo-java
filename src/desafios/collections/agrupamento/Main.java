package desafios.collections.agrupamento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> nomes = List.of("Ana", "Maria", "Vitória", "José", "João", "Sofia", "Matheus", "Mateus", "Gustavo", "Bianca", "Geovana", "Giovanna", "Amanda", "Victor", "Ruan", "Kauan", "Bruno");
    
        Map<Character, List<String>> agrupaMap = new HashMap<>();
    
        for (String nome : nomes) {
            char firstChar = nome.charAt(0);
            agrupaMap.computeIfAbsent(firstChar, k -> new ArrayList<>()).add(nome);
        } 

        System.out.println(agrupaMap);
    }
}
