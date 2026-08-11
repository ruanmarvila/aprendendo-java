package collections_generics.generics.wildcards;

import java.util.ArrayList;
import java.util.List;

abstract class Animal {
    public abstract void consulta();
}

class Cachorro extends Animal {
    @Override
    public void consulta() {
        System.out.println("Consultando cão");
    }
}

class Gato extends Animal {
    @Override
    public void consulta() {
        System.out.println("Consultando gato");
    }
}

public class Main {
    public static void main(String[] args) {
         List<Cachorro> cachorros = List.of(new Cachorro());
         List<Gato> gatos = List.of(new Gato(), new Gato());
         List<Animal> animais = new ArrayList<>();

         printConsulta(cachorros);
         printConsulta(gatos);
         addAnimal(animais);
    }

    // só para leitura
    private static void printConsulta(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.consulta();
        }
    }

    // pode modificar
    private static void addAnimal(List<? super Animal> animais) {
        animais.add(new Cachorro());
        animais.add(new Gato());
    }
}
