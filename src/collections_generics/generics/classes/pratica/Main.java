package collections_generics.generics.classes.pratica;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Carro> carrosDisponiveis = new ArrayList<>(List.of(new Carro("Fusca"), new Carro("BMW")));
        List<Barco> barcosDisponiveis = new ArrayList<>(List.of(new Barco("Lancha"), new Barco("Canoa")));

        RentalService<Carro> rentalService = new RentalService<>(carrosDisponiveis);

        Carro carro = rentalService.buscarDisponiveis();
        rentalService.retornarAlugados(carro);

        System.out.println("-------------------------");

        RentalService<Barco> rentalService2 = new RentalService<>(barcosDisponiveis);

        Barco barco = rentalService2.buscarDisponiveis();
        rentalService2.retornarAlugados(barco);
    }
}
