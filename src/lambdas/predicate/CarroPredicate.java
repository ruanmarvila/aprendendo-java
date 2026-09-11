package lambdas.predicate;

import lambdas.Carro;

// Interfaces Funcionais só podem ter 1 método sem corpo
@FunctionalInterface
public interface CarroPredicate {
    boolean teste(Carro carro);
    // (parametro) -> <expressão>
    // (carro) -> <carro.getNome().equals("Fusca"); -> retorna boolean>
}
