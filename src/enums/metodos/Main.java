package enums.metodos;

public class Main {
    public static void main(String[] args) {
        double areaQuadrado = FormaGeometrica.QUADRADO.calcularArea(4);
        double areaTrinagulo = FormaGeometrica.TRIANGULO.calcularArea(4, 10);
        double areaCirculo = FormaGeometrica.CIRCULO.calcularArea(2);

        System.out.println(areaQuadrado);
        System.out.println(areaTrinagulo);
        System.out.println(areaCirculo);
    }
}
