package collections_generics.generics.classes.pratica;

import java.util.List;

public class RentalService<T> {
    private List<T> objetosDisponiveis;

    public RentalService(List<T> objetosDisponiveis) {
        this.objetosDisponiveis = objetosDisponiveis;
    }

    public T buscarDisponiveis() {
        return objetosDisponiveis.remove(0);
    }

    public void retornarAlugados(T t) {
        objetosDisponiveis.add(t);
    }
}