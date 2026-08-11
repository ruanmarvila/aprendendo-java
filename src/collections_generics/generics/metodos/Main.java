package collections_generics.generics.metodos;

import java.util.ArrayList;
import java.util.List;

import collections_generics.generics.classes.pratica.Carro;

public class Main {
    public static void main(String[] args) {
        criarArray(new Carro("Fusca"));
    }

    private static <T> void criarArray(T obj) {
        List<T> lista = new ArrayList<>(List.of(obj));
        System.out.println(lista);
    }
}
