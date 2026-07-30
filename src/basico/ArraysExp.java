package basico;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class ArraysExp {
    public static void main(String[] args) {
        // lista de inteiros com tamanho fixo
        int[] nums = new int[5]; // [0, 0, 0, 0, 0]

        // usa o indice pra adicionar o valor
        nums[0] = 1;
        nums[1] = 3;
        nums[2] = 8;
        nums[3] = 5;
        nums[4] = 2;
       
        for (int n : nums) {
            System.out.println(n);
        }

        System.out.println("==========");
        
        // array multidimensionado, matrizes
        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
        };

        // percorrendo a matriz, usa matriz[linha][coluna] para acessar o valor
        for (int l = 0; l < matriz.length; l++){
            for (int c = 0; c < matriz[l].length; c++) {
                System.out.print(matriz[l][c] + " ");
            }
            System.out.println();
        }

        matriz[0][0] = 40; // altera o valor para 40

        System.out.println("==========");
        // lista de inteiros com tamanho flexível
        List<Integer> numsArrayList = new ArrayList<>();

        // add pra adicionar um valor no final da lista
        numsArrayList.add(2);
        numsArrayList.add(1);
        numsArrayList.add(3);
        numsArrayList.add(20);

        int total = 0;
        for (int i = 0; i < numsArrayList.size(); i++) {
            total += numsArrayList.get(i);
        }
        System.out.println("Total: " + total);

        // lista de string com tamanho fixo
        String[] nomes = {"Amanda", "Carlos", "Maria"};

        for (String nome : nomes) {
            System.out.println("Olá, " + nome);
        }

        // lista de String flexível
        List<String> frutas = new ArrayList<>();

       frutas.add("Banana");
       frutas.add("Maçã");
       frutas.add("Morango");

       for (String fruta : frutas) {
        System.out.println("Eu gosto de " + fruta);
       }

       int[] numeros = {8, 5, 1, 9, 3};

        // Ordena em ordem crescente
        Arrays.sort(numeros); // {1, 3, 5, 8, 9}

        // Exibe o conteúdo do array e não o espaço na mémoria
        System.out.println(Arrays.toString(numeros));

        // Busca - O array precisa estar ordenado
        int indice = Arrays.binarySearch(numeros, 5);
        System.out.println("Índice: " + indice);

        // Copia
        int[] copia = Arrays.copyOf(numeros, numeros.length);

        // Compara
        System.out.println(Arrays.equals(numeros, copia));

        // Preenche
        Arrays.fill(copia, 0);

        System.out.println(Arrays.toString(copia));
    }
}
