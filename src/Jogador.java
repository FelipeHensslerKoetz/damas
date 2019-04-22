public class Jogador {
    private String nome;
    private String cor;
    private int quantidadeDePecas = 12;
    private int vitorias;

    public Jogador(String nome, String cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQuantidadeDePecas() {
        return quantidadeDePecas;
    }

    public void setQuantidadeDePecas(int quantidadeDePecas) {
        this.quantidadeDePecas = quantidadeDePecas;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }
}
