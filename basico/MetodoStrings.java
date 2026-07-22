public class MetodoStrings {
    public static void main(String[] args) {
        String texto = "  Ruan Carlos  ";

        System.out.println(texto.toUpperCase()); // "  RUAN CARLOS  "
        System.out.println(texto.toLowerCase()); // "  ruan carlos  "
        System.out.println(texto.trim()); // "Ruan Carlos" -> remove espaços do começo e fim
        System.out.println(texto.length()); // 15
        System.out.println(texto.contains("Ruan")); // true
        System.out.println(texto.replace("Carlos", "Marvila")); // "  Ruan Marvila  "
    }
}
