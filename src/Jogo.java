import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private boolean statusJogo = true;
    private Casa[][] tabuleiro = new Casa[8][8];
    private ArrayList historicoJogadas = new ArrayList();
    private Jogada jogada = new Jogada();


    public Jogo() {
        criarJogadores();
        inicializarTabuleiro();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = new Casa();
                if ((i + j) % 2 == 0) {
                    tabuleiro[i][j].setCor("Negra");
                } else {
                    tabuleiro[i][j].setCor("Branca");
                }

                // Valor default
                tabuleiro[i][j].setConteudo("  ");

                // Preencher as peças do tabuleiro
                switch (i) {
                    case 0:
                    case 2:
                        if (j % 2 != 0) {
                            tabuleiro[i][j].setConteudo(" O");
                            tabuleiro[i][j].setDominio("Brancas");
                            tabuleiro[i][j].setOcupada(true);
                        }
                        break;
                    case 1:
                        if (j % 2 == 0) {
                            tabuleiro[i][j].setConteudo(" O");
                            tabuleiro[i][j].setDominio("Brancas");
                            tabuleiro[i][j].setOcupada(true);
                        }
                        break;
                    case 5:
                    case 7:
                        if (j % 2 == 0) {
                            tabuleiro[i][j].setConteudo("*O");
                            tabuleiro[i][j].setDominio("Negras");
                            tabuleiro[i][j].setOcupada(true);
                        }
                        break;
                    case 6:
                        if (j % 2 != 0) {
                            tabuleiro[i][j].setConteudo("*O");
                            tabuleiro[i][j].setDominio("Negras");
                            tabuleiro[i][j].setOcupada(true);

                        }

                }
            }
        }
    }

    public void imprimirTabuleiro(){
//        System.out.println("Jogadas ae o momento: ");
//        for(int i =0; i<historicoJogadas.size();i++){
//            System.out.println(historicoJogadas.get(i));
//        }
        for (int i=0;i<24;i++){
            System.out.println();
        }

        System.out.println();
        System.out.println("                 ["+jogador1.getNome()+"]");
        System.out.println();
        System.out.println("    A     B    C    D    E    F    G    H ");
        System.out.println("  +----+----+----+----+----+----+----+----+");
        for(int i =0; i<tabuleiro.length;i++){
            System.out.print((tabuleiro.length-i)+" |");
            for(int j = 0;j<tabuleiro[i].length;j++){
                System.out.print(" "+tabuleiro[i][j].getConteudo()+" |");
            }
            System.out.print(" "+(tabuleiro.length-i));
            System.out.println();
            System.out.println("  +----+----+----+----+----+----+----+----+");
        }
        System.out.println("    A     B    C    D    E    F    G    H ");
        System.out.println();
        System.out.println("                 ["+jogador2.getNome()+"]");
        System.out.println();
        // set status jogo false
    }

    public void realizaJogada(){
        // Testar se o jogo acabou
        String coordenada;
        boolean jogadaRealizada;
        Scanner scanner = new Scanner(System.in);

        // Regula o controle de jogdas
        do{
            System.out.print("Insira a sua jogada "+this.jogadorAtual.getNome()+": ");
            coordenada = scanner.nextLine();
            jogadaRealizada = jogada.validaJogada(tabuleiro,jogadorAtual,coordenada);
            System.out.println();
        } while(!jogadaRealizada);


        // Altera o jogador se for sua vez de jogar
        if(this.jogadorAtual == this.jogador1){
            this.jogadorAtual= this.jogador2;
        } else {
            this.jogadorAtual = this.jogador1;
        }
    }

    private void criarJogadores(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o nome do jogador das brancas: ");
        String nome = scanner.nextLine();
        Jogador jogador1 = new Jogador(nome,"Brancas");
        System.out.print("Insira o nome do jogador das negras: ");
        nome = scanner.nextLine();
        Jogador jogador2 = new Jogador(nome,"Negras");

        this.jogador1 = jogador1;
        this.jogador2 = jogador2;

        System.out.println("Nome do player 1: "+this.jogador1.getNome());
        System.out.println("Quantidade de peças: "+this.jogador1.getQuantidadeDePecas());
        System.out.println("Cor: "+this.jogador1.getCor());
        System.out.println();
        System.out.println("Nome do player 2: "+this.jogador2.getNome());
        System.out.println("Quantidade de peças: "+this.jogador2.getQuantidadeDePecas());
        System.out.println("Cor: "+this.jogador2.getCor());

        this.jogadorAtual = jogador1;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean isStatusJogo() {
        return statusJogo;
    }

    public Casa[][] getTabuleiro() {
        return tabuleiro;
    }

}
