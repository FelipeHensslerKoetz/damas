import java.util.Scanner;

public class DamaTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Jogador[] jogadores = new Jogador[3];

        jogadores[0] = new Jogador("CPU",false);

        System.out.print("Insira o player 1: ");
        String nome = scan.nextLine();

        jogadores[1] = new Jogador(nome,true);
        System.out.print("Insira o player 2: ");
        nome = scan.nextLine();
        jogadores[2] = new Jogador(nome,true);


        int opcao = 0;

        do{
            System.out.println("\n\n### DamasMaster - Um jogo de damas simples feito no terminal  ###|");
            System.out.println("\n                  =========================");
            System.out.println("                  |     1 - 1 vs CPU      |");
            System.out.println("                  |     2 - 1 vs 1        |");
            System.out.println("                  |     3 - Ranking       |");
            System.out.println("                  |     0 - Sair          |");
            System.out.println("                  =========================\n");
            System.out.print("Escolha uma opcao de jogo: ");
            opcao = scan.nextInt();

            switch (opcao){
                case 1:
                    for(int i =1; i <jogadores.length;i++){
                        System.out.println(i+". "+jogadores[i].getNome());
                    }
                    int playerPosicao = 0;
                    do{
                        System.out.print("Escolha o player: ");
                        playerPosicao = scan.nextInt();
                    } while(playerPosicao <1 || playerPosicao >2);

                    Jogo novoJogoCpu = new Jogo(jogadores[0],jogadores[playerPosicao]);
                    while (novoJogoCpu.isStatusJogo()){
                        novoJogoCpu.imprimirTabuleiro();
                        novoJogoCpu.realizaJogada();
                    }

                    break;
                case 2:
                    Jogo novoJogo = new Jogo(jogadores[1],jogadores[2]);
                    while (novoJogo.isStatusJogo()){
                        novoJogo.imprimirTabuleiro();
                        novoJogo.realizaJogada();
                    }
                    break;
                case 3:
                    System.out.println("Scores dos jogadores");
                    for (Jogador player: jogadores) {
                        System.out.println(player.getNome()+" - "+player.getVitorias());
                    }
                    break;
            }
        } while(opcao != 0);
        System.out.println("Fim do programada, obrigado por jogar!");

    }
}
