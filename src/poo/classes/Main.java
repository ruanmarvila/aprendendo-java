package poo.classes;

public class Main {
    public static void main(String[] args) {
        Carro carro = new Carro(); // criando um objeto Carro com "new"
        carro.nome = "Mercedes GLA"; // atribuindo valor à nome
        carro.ano = 2018; // atribuindo valor à ano

        System.out.println(carro.ano); // 2018

        carro.darPartida(); // chamando o método
    }
}
