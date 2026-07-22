import java.util.List;
import java.util.ArrayList;

public class Arrays {
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
        String[] nomes = new String[3];

        nomes[0] = "Bruno";
        nomes[1] = "Amanda";
        nomes[2] = "Carlos";

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
    }
}
