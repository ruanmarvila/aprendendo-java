package desafios.collections.alunos;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<Aluno> alunos = new HashSet<>();

        alunos.add(new Aluno("Ana", "1B89CH"));
        alunos.add(new Aluno("Gaspar", "1B89CH"));
        alunos.add(new Aluno("José", "GB69W3"));

       for (Aluno aluno : alunos) {
        System.out.println(aluno.toString());
       }
    }
}
