package collections_generics.collections.list.conversao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // List -> Array
        List<Integer> inteiros = new ArrayList<>();

        inteiros.add(1);
        inteiros.add(2);
        inteiros.add(3);

        Integer[] inteirosArray = inteiros.toArray(new Integer[0]);
        System.out.println(Arrays.toString(inteirosArray)); // [1, 2, 3]

        // Array -> List
        Integer[] numerosArray = new Integer[3];

        numerosArray[0] = 4;
        numerosArray[1] = 5;
        numerosArray[2] = 6;

        List<Integer> numeros = Arrays.asList(numerosArray); // Faz uma conexão
        numeros.set(0, 13);
        System.out.println(Arrays.toString(numerosArray)); // [13, 5, 6]
        System.out.println(numeros); // [13, 5, 6]
        
        List<Integer> numerosList = new ArrayList<>(Arrays.asList(numerosArray));
        numerosList.add(22);
        System.out.println(numerosList); // [13, 5, 6, 22]
    }
}
