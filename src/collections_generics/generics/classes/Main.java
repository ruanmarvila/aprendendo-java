package collections_generics.generics.classes;

public class Main {
    public static void main(String[] args) {
        Caixa<String> caixaTexto = new Caixa<>("Davi");
        Caixa<Integer> caixaNumero = new Caixa<>(23);

        System.out.println(caixaTexto.getConteudo()); // Davi
        System.out.println(caixaNumero.getConteudo()); // 23

        Par<String, Integer> pessoa = new Par<>("Heitor", 30);

        System.out.println(pessoa.getChave());
        System.out.println(pessoa.getValor());
    }
}
