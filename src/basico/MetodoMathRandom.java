package src.basico;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MetodoMathRandom {
    public static void main(String[] args) {
        int maior = Math.max(10, 20); // escolhe o maior entre dois números
        int menor = Math.min(-2, 5); // escolhe o menor entre dois números
        int modulo = Math.abs(-6); // a distância até zero, na matemática é representado por |x|
        double raizQuadrada = Math.sqrt(25); // a raiz quadrada do número
        double raizCubica = Math.cbrt(27); // a raiz cúbica do número
        double potencia = Math.pow(2, 3); // potênciação 2^3

        System.out.println(maior);
        System.out.println(menor);
        System.out.println(modulo);
        System.out.println(raizQuadrada);
        System.out.println(raizCubica);
        System.out.println(potencia);
        System.out.println(Math.round(6.7)); // Arredonda pro inteiro mais próximo -> 7
        System.out.println(Math.floor(6.9)); // Arredonda pra baixo -> 6
        System.out.println(Math.ceil(4.1)); // Arredonda pra cima -> 5
        System.out.println(Math.PI); // PI (3.14159...)
        System.out.println(Math.E); // Número de Euler (2.71828...)

        System.out.println("=====================");

        Random random = new Random();

        int numero = random.nextInt(); // gera um número inteiro
        int numeroComLimite = random.nextInt(10); // gera um número de 0 a 9
        double decimal = random.nextDouble(); // 0.0 <= x <= 1.0
        boolean booleano = random.nextBoolean(); // true ou false

        System.out.println(numero);
        System.out.println(numeroComLimite);
        System.out.println(decimal);
        System.out.println(booleano);
        System.out.println(random.nextInt(51) + 50); // número entre 50 e 100

        System.out.println("=====================");

        int numeroThread = ThreadLocalRandom.current().nextInt();
        int dado = ThreadLocalRandom.current().nextInt(1, 7); // intervalo melhor
        double nota = ThreadLocalRandom.current().nextDouble(0, 11);

        System.out.println(numeroThread);
        System.out.println(dado);
        System.out.println(nota);

    }
}
