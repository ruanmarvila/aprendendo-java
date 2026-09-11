package arquivos.csv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Aluno> alunos = List.of(
            new Aluno("Ana", 16, 9.5),
            new Aluno("Sofia", 16, 7.2),
            new Aluno("Júlia", 17, 6.0)
        );

        // Escrevendo
        List<String> linhas = new ArrayList<>();
        linhas.add("nome,idade,nota");

        for (Aluno aluno : alunos) {
            linhas.add(aluno.nome()+","+aluno.idade()+","+aluno.nota());
        }
        Files.write(Path.of("alunos.csv"), linhas);

        // Lendo
        List<String> linhasArquivo = Files.readAllLines(Path.of("alunos.csv"));

        List<Aluno> alunosLidos = new ArrayList<>();

        for (int i = 1; i < linhasArquivo.size(); i++) {
            String[] campos = linhasArquivo.get(i).split(",");

            String nome = campos[0];
            int idade = Integer.parseInt(campos[1]);
            double nota = Double.parseDouble(campos[2]);

            alunosLidos.add(new Aluno(nome, idade, nota));
        }
        alunosLidos.forEach(System.out::println);
    }
}
