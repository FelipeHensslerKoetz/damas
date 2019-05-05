import java.util.ArrayList;

public class CPU {

    public String lanceValido(Casa[][] tabuleriro, Jogador cpu){

        // Intrepretar o tabuleiro

        // 1 - Existem lance obrigatorios
        // 1.1 - Varrer o tabuleiro e verificar apenas os lances obrigatorios em forma de string
        // 1.2 - Jogar uma jogada aleatoria obrigatoria
        // 1.3 - Retorna a string da jogada para o jogo


        // 2 - Nao existem lences obrigaotrios
        // 2.1 - Varrer o tabuleiro e adicionar todas as jogadas em formas em string
        // 2.2 - Excutar a propria jogada aleatoria passando uma String
        // 2.3 - Retornar a string da jogada para o jogo


//      // Obtendo as jogadas em formato string
        ArrayList jogadas = jogadas(tabuleriro,cpu);
//
        // Passar uma posicao aleatoria para executar de jogadas
        String jogadaExecutada = executaJogada(tabuleriro,"a3-b4",cpu);

//      Retorna a jogada
        return jogadaExecutada;

    }


    // Recebe coordenada literal e retorna em String
    private String montaCoordenada(int linhaOrigem, int colunaOrigem, int linhaDestino, int colunaDestino) {
        String coordenadaTexto;

        int linhaOrigemParametro = linhaOrigem;
        int colunaOrigemParametro = colunaOrigem;
        int linhaDestinoParametro = linhaDestino;
        int colunaDestinoParametro = colunaDestino;

        char colunaOrigemCalculada= ' ';
        char colunaDestinoCalculada = ' ';
        int linhaOrigemCalculada = 8-linhaOrigemParametro;
        int linhaDestinoCalculada = 8-linhaDestinoParametro;

        // Pega e tranforma a primeira letra
        switch (colunaOrigemParametro){
            case 0:
                colunaOrigemCalculada = 'a';
                break;
            case 1:
                colunaOrigemCalculada = 'b';
                break;
            case 2:
                colunaOrigemCalculada = 'c';
                break;
            case 3:
                colunaOrigemCalculada = 'd';
                break;
            case 4:
                colunaOrigemCalculada = 'e';
                break;
            case 5:
                colunaOrigemCalculada = 'f';
                break;
            case 6:
                colunaOrigemCalculada = 'g';
                break;
            case 7:
                colunaOrigemCalculada = 'h';
                break;
        }

        // Pega e transforma a segunda letra
        switch(colunaDestinoParametro){
            case 0:
                colunaDestinoCalculada = 'a';
                break;
            case 1:
                colunaDestinoCalculada = 'b';
                break;
            case 2:
                colunaDestinoCalculada = 'c';
                break;
            case 3:
                colunaDestinoCalculada = 'd';
                break;
            case 4:
                colunaDestinoCalculada = 'e';
                break;
            case 5:
                colunaDestinoCalculada = 'f';
                break;
            case 6:
                colunaDestinoCalculada = 'g';
                break;
            case 7:
                colunaDestinoCalculada = 'h';
                break;
        }
        coordenadaTexto=""+colunaOrigemCalculada+linhaOrigemCalculada+"-"+colunaDestinoCalculada+linhaDestinoCalculada;
        return coordenadaTexto;
    }

    // Executa a jogada da CPU passando um STRING
    private String executaJogada(Casa [][] tabuleiro,String jogada, Jogador cpu){
        int linhaOrigem = Character.getNumericValue(jogada.charAt(1)); // Pega o valor - a soma deve dar 8
        linhaOrigem = (8 - linhaOrigem); // Ajustando para o array
        char colunaOrigemChar = jogada.charAt(0);
        int colunaOrigem = 0;
        switch (colunaOrigemChar) {
            case 'a':
                colunaOrigem = 0;
                break;
            case 'b':
                colunaOrigem = 1;
                break;
            case 'c':
                colunaOrigem = 2;
                break;
            case 'd':
                colunaOrigem = 3;
                break;
            case 'e':
                colunaOrigem = 4;
                break;
            case 'f':
                colunaOrigem = 5;
                break;
            case 'g':
                colunaOrigem = 6;
                break;
            case 'h':
                colunaOrigem = 7;
                break;
        }

        int linhaDestino = Character.getNumericValue(jogada.charAt(4));
        linhaDestino = (8 - linhaDestino);
        char colunaDestinoChar = jogada.charAt(3);
        int colunaDestino = 0;
        switch (colunaDestinoChar) {
            case 'a':
                colunaDestino = 0;
                break;
            case 'b':
                colunaDestino = 1;
                break;
            case 'c':
                colunaDestino = 2;
                break;
            case 'd':
                colunaDestino = 3;
                break;
            case 'e':
                colunaDestino = 4;
                break;
            case 'f':
                colunaDestino = 5;
                break;
            case 'g':
                colunaDestino = 6;
                break;
            case 'h':
                colunaDestino = 7;
                break;
        }

        // Jogada convertida em literais

        if (cpu.getCor().equals("Brancas")){
            // Peca comum
            if (!tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {
                // Movimento
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem - 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)) {
                    // Testa promocao
                    if (linhaDestino == 0) {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(" D");
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                    } else {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);
                    }
                }

                // Captura peca comum
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem - 2) && (colunaDestino == colunaOrigem + 2 || colunaDestino == colunaOrigem - 2) && (tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].getDominio().equals("Negras"))) {
                    if (linhaDestino == 0) {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(" D");
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setOcupada(false);
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setConteudo("  ");
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setDominio(" ");
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setPecaEspecial(false);


                    } else {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setOcupada(false);
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setConteudo("  ");
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setDominio(" ");
                        tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].setPecaEspecial(false);
                    }
                }
            }

        }

        return jogada;
    }


    // Retorna todas as jogadas em String
    private ArrayList jogadas(Casa[][] tabuleiro, Jogador cpu){
        // Retorna as jogadas obrigatorias ou retorna todas as jogadas
        ArrayList jogadas = new ArrayList();

        if(cpu.getCor().equals("Brancas")) {


        }

        return jogadas;
    }
}
