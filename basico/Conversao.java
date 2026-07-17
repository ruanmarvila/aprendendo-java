public class Conversao {
    public static void main(String[] args) {
        // int para double
        int numero = 5;
        double decimal = numero;

        System.out.println("Número Inteiro: " + numero); // 5
        System.out.println("Número Decimal: " + decimal); // 5.0

        // double para int
        // a parte depois do ponto some
        double preco = 7.99;
        int intPreco = (int) preco;

        System.out.println("Preço: " + preco); // 7.99
        System.out.println("Preço sem decimal: " + intPreco); // 7

        // conversão pra String
        // usa-se String.valueOf()
        int numero1 = 2;
        double numero2 = 5.5;
        boolean falso = false;

        String texto1 = String.valueOf(numero1);
        String texto2 = String.valueOf(numero2);
        String texto3 = String.valueOf(falso);

        System.out.println("Int para String: " + texto1); // "2"
        System.out.println("double para String: " + texto2); // "5.5"
        System.out.println("boolean para String: " + texto3); // "false"
        
        // String para int
        // usa-se Integer.parseInt()
        String numeroTexto = "123";
        int numeroConvertido = Integer.parseInt(numeroTexto);

        System.out.println("String para int: " + numeroConvertido); // 123

        // String para double
        // usa-se o Double.parseDouble()
        String doubleTexto = "12.50";
        double decimalConvertido = Double.parseDouble(doubleTexto);

        System.out.println("String para double: " + decimalConvertido); // 12.50

        // String para boolean
        // Boolean.parseBoolean()
        String verdadeiroTexto = "true";
        boolean verdadeiroConvertido = Boolean.parseBoolean(verdadeiroTexto);

        System.out.println("String para boolean: " + verdadeiroConvertido); // true
    }
}
