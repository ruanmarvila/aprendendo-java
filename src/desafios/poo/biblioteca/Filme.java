package desafios.poo.biblioteca;

public class Filme extends Midia{
    private String diretor;
    private int duracaoMinutos;

    public Filme(String titulo, String diretor, int duracaoMinutos) {
        super(titulo, true);
        this.diretor = diretor;
        this.duracaoMinutos = duracaoMinutos;
    }

    @Override
    public String exibirFicha() {
        return "Filme '" + titulo + "' do diretor "  + diretor + " com " + duracaoMinutos + " minutos. Status: " + estaDisponivel();
    }

}
