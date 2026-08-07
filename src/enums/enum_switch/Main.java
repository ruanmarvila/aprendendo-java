package enums.enum_switch;

public class Main {
    public static void main(String[] args) {
        System.out.println(pontosPorAcerto(NivelDificuldade.MEDIO));
        System.out.println(pontosPorAcerto(NivelDificuldade.FACIL));
        System.out.println(pontosPorAcerto(NivelDificuldade.DIFICIL));
    }

    public static int pontosPorAcerto(NivelDificuldade nivel) {
        // Não precisa do 'case NivelDificuldade.FACIL' pois o switch já sabe que nivel se trata do enum
        int pontos = switch (nivel) {
            case FACIL -> 10;
            case MEDIO -> 25;
            case DIFICIL -> 50;
        };
        return pontos;
    }
}
