package desafios.poo.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Midia> midias;

    public Biblioteca() {
        this.midias = new ArrayList<>();
    }

    public void adicionar(Midia midia) {
        midias.add(midia);
    }

    public void emprestar(String titulo) {
        for (Midia midia : midias) {
            if (titulo.equals(midia.getTitulo())) {
                if (!midia.getDisponivel()){
                    throw new MidiaIndisponivelException("Mída indisponível.");
                }
                midia.setDisponivel(false);
                System.out.println("Mídia emprestada com sucesso!");
                return;
            }
        }
        throw new MidiaIndisponivelException("Mídia não encontrada.");
    }

    public List<Midia> listarDisponiveis() {
        List<Midia> midiasDisponiveis = new ArrayList<>();

        for (Midia midia : midias) {
            if (midia.getDisponivel() == true) {
                midiasDisponiveis.add(midia);
            }
        }
        return midiasDisponiveis;
    }
}
