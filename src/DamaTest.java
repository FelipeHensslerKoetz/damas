public class DamaTest {
    public static void main(String[] args) {
        Jogo novoJogo = new Jogo();
        while (novoJogo.isStatusJogo()){
            novoJogo.imprimirTabuleiro();
            novoJogo.realizaJogada();
        }
    }
}
