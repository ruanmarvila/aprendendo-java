public class Inferencia {
    public static void main(String[] args) {
        // O compilador faz a inferência do tipo baseado no valor
        // usa-se o "var" pra isso
        var numero = 20; // int
        var texto = "Idade: "; // String

        System.out.println(texto + numero); // "Idade: 20"
    }
}
