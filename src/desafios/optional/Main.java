package desafios.optional;

import java.util.List;
import java.util.Optional;

public class Main {
    private static List<Usuario> usuarios = List.of(
        new Usuario(1, "Ana", 25, new Endereco("RJ", "Niterói")),
        new Usuario(2, "Douglas", 37, new Endereco("SP", "Campinas")),
        new Usuario(3, "Vitória", 19, new Endereco("ES", "Vitória"))
    );

    public static void main(String[] args) {
        Optional<String> cidade = getUserById(1)
            .map(Usuario::getEndereco)
            .map(Endereco::getCidade);
        Optional<String> cidadeNulo = getUserById(5)
            .map(Usuario::getEndereco)
            .map(Endereco::getCidade);

        System.out.println(cidade);
        System.out.println(cidadeNulo);
    }

    private static Optional<Usuario> getUserById(Integer id) {
        Usuario usuarioEncontrado = null;

        for (Usuario u : usuarios) {
            if (u.getId().equals(id)) {
                usuarioEncontrado = u;
            }
        }
        return Optional.ofNullable(usuarioEncontrado);
    }
}

