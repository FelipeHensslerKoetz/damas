import java.util.ArrayList;

public class Jogada {


    public boolean validaJogada(Casa[][] tabuleiro, Jogador jogador, String coordenada) {

        // Regex verifica se o formato inserido foi o correto
        String regex = "[a-hA-H][1-8]-[a-hA-H][1-8]";
        if (!coordenada.matches(regex)) {
            System.out.println("Formato de entrada inválido, modelo de jogada: a4-b5");
            return false;
        }


        // Obtem os valores de origem e destino
        int linhaOrigem = Character.getNumericValue(coordenada.charAt(1)); // Pega o valor - a soma deve dar 8
        linhaOrigem = (8 - linhaOrigem); // Ajustando para o array
        char colunaOrigemChar = coordenada.charAt(0);
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

        int linhaDestino = Character.getNumericValue(coordenada.charAt(4));
        linhaDestino = (8 - linhaDestino);
        char colunaDestinoChar = coordenada.charAt(3);
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


        // Jogador de brancas
        if (jogador.getCor().equals("Brancas")) {
            if (!tabuleiro[linhaOrigem][colunaOrigem].isOcupada() || tabuleiro[linhaOrigem][colunaOrigem].getDominio().equals("Negras")) {
                System.out.println("Casa de origem é vazia ou de peça adversária, tente novamente.");
                return false;
            }

            // Acao pecao comum
            if (!tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {

                // Movimento
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)) {
                    // Promovida
                    if(linhaDestino == 7){
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo((char)9923);
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio("");
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(false);

                    } else {
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio("");
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(false);
                    }
                    return true;
                }

                // Captura
                if(!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 2) && (colunaDestino == colunaOrigem + 2 ||colunaDestino == colunaOrigem +2) && (tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].getDominio().equals("Negras"))){
                    if(linhaDestino == 7 ){
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo((char)9923);
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setOcupada(false);
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setConteudo(' ');
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setDominio(" ");
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setPecaEspecial(false);

                    } else{
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(false);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setOcupada(false);
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setConteudo(' ');
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setDominio(" ");
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setPecaEspecial(false);
                    }

                    // Desconta peca do jogador contrario !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    return true;
                }
                System.out.println("Casa de destino invalida");
                return false;
            }

            // Peca especial
            if (tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {

            }


        }

        // Jogado de negras
        if (jogador.getCor().equals("Negras")) {
            if (!tabuleiro[linhaOrigem][colunaOrigem].isOcupada() || tabuleiro[linhaOrigem][colunaOrigem].getDominio().equals("Brancas")) {
                System.out.println("Casa de origem é vazia ou de peça adversária, tente novamente.");
                return false;
            }

            // Peca comum
            if (!tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {
                // Movimento
                if(!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem - 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)){
                    // Testa promocao
                    if(linhaDestino == 0){
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo((char)9921);
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                    } else {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(false);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);
                    }
                    return true;
                }

                // Captura peca comum
                if(!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem-2) &&  (colunaDestino == colunaOrigem+2 || colunaDestino == colunaOrigem-2) && (tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].getDominio().equals("Brancas"))){
                    if(linhaDestino == 0){
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo((char)9921);
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setOcupada(false);
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setConteudo(' ');
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setDominio(" ");
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setPecaEspecial(false);


                    } else {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(false);

                        // Remover peca da origem
                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo(' ');
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                        // Remover peca inimiga intermediaria
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setOcupada(false);
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setConteudo(' ');
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setDominio(" ");
                        tabuleiro[(linhaOrigem+linhaDestino)/2][(colunaOrigem+colunaDestino)/2].setPecaEspecial(false);

                    }
                    return true;
                }




                System.out.println("Casa de destino invalida");
                return false;
            }

            // Peca especial
            if (tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {

            }


        }

        System.out.println("Nao joguei");
        return false;
    }

    // Lista de jogadas possiveis
    private ArrayList jogadasValidas() {
        ArrayList jogadasValidas = new ArrayList();
        return jogadasValidas;

    }


}
