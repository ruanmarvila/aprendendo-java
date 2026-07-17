public class Operadores {
    public static void main(String[] args) {
        // Soma ou Concatenção (+)
        int numero1 = 10;
        int numero2 = 2;

        String texto1 = "Olá, ";
        String texto2 = "Mundo!";

        System.out.println(numero1 + numero2); // 12
        System.out.println(texto1 + texto2); // "Olá, Mundo!"
        System.out.println("Adeus, " + texto2); // "Adeus, Mundo!"

        // Subtração (-)
        int numero3 = 6;
        int numero4 = 3;

        System.out.println(numero3 - numero4); // 3

        // Multiplicação (*)
        int numero5 = 20;
        int numero6 = 5;

        System.out.println(numero5 - numero6); // 100

        // Divisão (/)
        int numero7 = 7;
        int numero8 = 2;
        double numero9 = 9;

        System.out.println(numero7 / numero8); // 3 -> a divisão é inteira com dois int
        System.out.println(numero9 / numero8); // 4.5 -> a divisão é completa quando há um double

        // Resto da Divisão (%)
        int numero10 = 10;
        int numero11 = 2;
        int numero12 = 15;
        int numero13 = 3;

        System.out.println(numero10 % numero11); // 0 -> logo 10 é par
        System.out.println(numero12 % numero13); // 0 -> logo 15 é ímpar

        /*
        O Java tem alguns operadores contraídos
        numero += 2 -> numero = numero + 2
        numero -= 2 -> numero = numero - 2
        numero *= 2 -> numero = numero * 2
        numero /= 3 -> numero = numero / 3
        numero %= 3 -> numero = numero % 3

        Operadores de incremento e decremento
        numero++ -> numero = numero + 1 (utilizados muito em loops)
        numero-- -> numero = numero - 1 (utilizado muito em loops)
        */
    }
}
