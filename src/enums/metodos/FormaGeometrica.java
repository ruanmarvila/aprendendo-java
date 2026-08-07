package enums.metodos;

public enum FormaGeometrica {
    QUADRADO {
        @Override
        public double calcularArea(double... medidas) {
            double lado = medidas[0];
            return lado * lado;
        }
    },
    TRIANGULO {
        @Override
        public double calcularArea(double... medidas) {
            double base = medidas[0];
            double altura = medidas[1];
            return (base * altura) / 2; 
        }
    },
    CIRCULO {
        @Override
        public double calcularArea(double... medidas) {
            double raio = medidas[0];
            return Math.PI * (raio * raio);
        }
    };

    public abstract double calcularArea(double... medidas);
}
