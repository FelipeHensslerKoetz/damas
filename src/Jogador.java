public class Jogador {
    private String nome;
    private String cor;
    private int quantidadeDePecas = 12;
    private int vitorias;
    private boolean humano;

    public Jogador(String nome, boolean humano) {
        this.nome = nome;
        this.humano = humano;
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

    public boolean isHumano() {
        return humano;
    }

    public void setHumano(boolean humano) {
        this.humano = humano;
    }
}
