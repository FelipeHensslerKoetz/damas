
import java.util.ArrayList;

public class Jogada {


    public boolean validaJogada(Casa[][] tabuleiro, Jogador jogador, String coordenada) {


        // Regex verifica se o formato inserido foi o correto
        String regex = "[a-hA-H][1-8]-[a-hA-H][1-8]";
        if (!coordenada.matches(regex)) {
            System.out.println("Formato de entrada inválido, modelo de jogada: a4-b5");
            return false;
        }

        ArrayList capturasObg;
        capturasObg = capturaObrigatoria(tabuleiro, jogador);
        if (!capturasObg.isEmpty()) {
            if (!capturasObg.contains(coordenada)) {
                System.out.print("Captura obrigatoria em: ");
                for (int i = 0; i < capturasObg.size(); i++) {
                    System.out.print(capturasObg.get(i) + ", ");
                }
                System.out.print(" escolha uma destas jogadas.");
                return false;
            }
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


        // Jogador de Negras
        if (jogador.getCor().equals("Negras")) {
            if (!tabuleiro[linhaOrigem][colunaOrigem].isOcupada() || tabuleiro[linhaOrigem][colunaOrigem].getDominio().equals("Brancas")) {
                System.out.println("Casa de origem é vazia ou de peça adversária, tente novamente.");
                return false;
            }

            // Acao pecao comum
            if (!tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {

                // Movimento
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)) {
                    // Promovida
                    if (linhaDestino == 7) {
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo("*D");
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(true);

                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);

                    } else {
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                        tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                        tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                        tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                        tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                        tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                        tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);
                    }
                    return true;
                }

                // Captura
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 2) && (colunaDestino == colunaOrigem + 2 || colunaDestino == colunaOrigem - 2) && (tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].getDominio().equals("Brancas"))) {
                    if (linhaDestino == 7) {
                        // Mover origem para destino
                        tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                        tabuleiro[linhaDestino][colunaDestino].setConteudo("*D");
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

                    // Desconta peca do jogador contrario !!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    return true;
                }

            }

            // Peca especial
            if (tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {

                // Movimento
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 1 || linhaDestino == linhaOrigem - 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)) {
                    tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                    tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                    tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                    tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                    tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                    tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                    tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                    tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);
                    return true;
                }

                // Captura
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 2 || linhaDestino == linhaOrigem - 2) && (colunaDestino == colunaOrigem + 2 || colunaDestino == colunaOrigem - 2) && (tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].getDominio().equals("Brancas"))) {
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

                    return true;
                }
            }

            System.out.println("Casa de destino invalida");
            return false;
        }

        // Jogado de Brancas
        if (jogador.getCor().equals("Brancas")) {
            if (!tabuleiro[linhaOrigem][colunaOrigem].isOcupada() || tabuleiro[linhaOrigem][colunaOrigem].getDominio().equals("Negras")) {
                System.out.println("Casa de origem é vazia ou de peça adversária, tente novamente.");
                return false;
            }

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
                    return true;
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
                    return true;
                }


                System.out.println("Casa de destino invalida");
                return false;
            }

            // Peca especial
            if (tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial()) {
                // Movimento
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 1 || linhaDestino == linhaOrigem - 1) && (colunaDestino == colunaOrigem + 1 || colunaDestino == colunaOrigem - 1)) {
                    tabuleiro[linhaDestino][colunaDestino].setOcupada(tabuleiro[linhaOrigem][colunaOrigem].isOcupada());
                    tabuleiro[linhaDestino][colunaDestino].setConteudo(tabuleiro[linhaOrigem][colunaOrigem].getConteudo());
                    tabuleiro[linhaDestino][colunaDestino].setDominio(tabuleiro[linhaOrigem][colunaOrigem].getDominio());
                    tabuleiro[linhaDestino][colunaDestino].setPecaEspecial(tabuleiro[linhaOrigem][colunaOrigem].isPecaEspecial());

                    tabuleiro[linhaOrigem][colunaOrigem].setOcupada(false);
                    tabuleiro[linhaOrigem][colunaOrigem].setConteudo("  ");
                    tabuleiro[linhaOrigem][colunaOrigem].setDominio(" ");
                    tabuleiro[linhaOrigem][colunaOrigem].setPecaEspecial(false);
                    return true;
                }

                // Captura
                if (!tabuleiro[linhaDestino][colunaDestino].isOcupada() && (linhaDestino == linhaOrigem + 2 || linhaDestino == linhaOrigem - 2) && (colunaDestino == colunaOrigem + 2 || colunaDestino == colunaOrigem - 2) && (tabuleiro[(linhaOrigem + linhaDestino) / 2][(colunaOrigem + colunaDestino) / 2].getDominio().equals("Negras"))) {
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

                    return true;
                }
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

    private ArrayList capturaObrigatoria(Casa[][] tabuleiro, Jogador jogador) {
        ArrayList capturasObrigatorias = new ArrayList();

        // Jogador de brancas
        if (jogador.getCor().equals("Brancas")) {
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++) {
                    if ((i + j) % 2 == 0) {
                        continue;
                    }
                    if (tabuleiro[i][j].getDominio().equals("Brancas")) {
                        if ((i - 1 >= 0 && i - 1 <= 7) && (j - 1 >= 0 && j - 1 <= 7) && (i - 2 >= 0 && i - 2 <= 7) && (j - 2 >= 0 && j - 2 <= 7)) {
                            if (tabuleiro[i - 1][j - 1].getDominio().equals("Negras") && !tabuleiro[i - 2][j - 2].isOcupada()) {
//                                System.out.println("Captura obrigatro esquerda frente");
                               capturasObrigatorias.add(montaCoordenada(i,j,(i-2),(j-2)));
                            }

                        }
                        if ((i - 1 >= 0 && i - 1 <= 7) && (j + 1 >= 0 && j + 1 <= 7) && (i - 2 >= 0 && i - 2 <= 7) && (j + 2 >= 0 && j + 2 <= 7)) {
                            if (tabuleiro[i - 1][j + 1].getDominio().equals("Negras") && !tabuleiro[i - 2][j + 2].isOcupada()) {
//                                System.out.println("Captura obrigatoria direita frente");
                                capturasObrigatorias.add(montaCoordenada(i,j,(i-2),(j+2)));
                            }

                        }
                        if (tabuleiro[i][j].isPecaEspecial()) {
                            if ((i + 1 >= 0 && i + 1 <= 7) && (j - 1 >= 0 && j - 1 <= 7) && (i + 2 >= 0 && i + 2 <= 7) && (j - 2 >= 0 && j - 2 <= 7)) {
                                if (tabuleiro[i + 1][j - 1].getDominio().equals("Negras") && !tabuleiro[i + 2][j - 2].isOcupada()) {
//                                    System.out.println("Captura obrigatoria esquerda tras");
                                    capturasObrigatorias.add(montaCoordenada(i,j,(i+2),(j-2)));
                                }
                            }

                            if ((i + 1 >= 0 && i + 1 <= 7) && (j + 1 >= 0 && j + 1 <= 7) && (i + 2 >= 0 && i + 2 <= 7) && (j + 2 >= 0 && j + 2 <= 7)) {
                                if (tabuleiro[i + 1][j + 1].getDominio().equals("Negras") && !tabuleiro[i + 2][j + 2].isOcupada()) {
//                                    System.out.println("Captura obrigatoria direita tras");
                                    capturasObrigatorias.add(montaCoordenada(i,j,(i+2),(j+2)));
                                }
                            }
                        }
                    }
                }
            }
        }

        if(jogador.getCor().equals("Negras")){
            for (int i =0; i<tabuleiro.length;i++){
                for(int j=0;j<tabuleiro[i].length;j++){
                    if ((i + j) % 2 == 0) {
                        continue;
                    }
                    if(tabuleiro[i][j].getDominio().equals("Negras")){
                        if ((i + 1 >= 0 && i + 1 <= 7) && (j - 1 >= 0 && j - 1 <= 7) && (i + 2 >= 0 && i + 2 <= 7) && (j - 2 >= 0 && j - 2 <= 7)) {
                            if (tabuleiro[i + 1][j - 1].getDominio().equals("Brancas") && !tabuleiro[i + 2][j - 2].isOcupada()) {
//                                System.out.println("Captura obrigatoria esquerda tras");
                                capturasObrigatorias.add(montaCoordenada(i,j,(i+2),(j-2)));
                            }
                        }

                        if ((i + 1 >= 0 && i + 1 <= 7) && (j + 1 >= 0 && j + 1 <= 7) && (i + 2 >= 0 && i + 2 <= 7) && (j + 2 >= 0 && j + 2 <= 7)) {
                            if (tabuleiro[i + 1][j + 1].getDominio().equals("Brancas") && !tabuleiro[i + 2][j + 2].isOcupada()) {
//                                System.out.println("Captura obrigatoria direita tras");
                                capturasObrigatorias.add(montaCoordenada(i,j,(i+2),(j+2)));
                            }
                        }

                        if(tabuleiro[i][j].isPecaEspecial()){
                            if ((i - 1 >= 0 && i - 1 <= 7) && (j - 1 >= 0 && j - 1 <= 7) && (i - 2 >= 0 && i - 2 <= 7) && (j - 2 >= 0 && j - 2 <= 7)) {
                                if (tabuleiro[i - 1][j - 1].getDominio().equals("Brancas") && !tabuleiro[i - 2][j - 2].isOcupada()) {
//                                    System.out.println("Captura obirgatoria esquerda frente");
                                    capturasObrigatorias.add(montaCoordenada(i,j,(i-2),(j-2)));
                                }

                            }
                            if ((i - 1 >= 0 && i - 1 <= 7) && (j + 1 >= 0 && j + 1 <= 7) && (i - 2 >= 0 && i - 2 <= 7) && (j + 2 >= 0 && j + 2 <= 7)) {
                                if (tabuleiro[i - 1][j + 1].getDominio().equals("Brancas") && !tabuleiro[i - 2][j + 2].isOcupada()) {
//                                    System.out.println("Captura obrigatoria direita frente");
                                    capturasObrigatorias.add(montaCoordenada(i,j,(i-2),(j+2)));
                                }
                            }
                        }
                    }
                }
            }
        }

        return capturasObrigatorias;
    }

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

}
