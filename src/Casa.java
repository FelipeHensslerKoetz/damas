public class Casa {
    private String cor;
    private boolean ocupada = false;
    private char conteudo;
    private String dominio;
    private boolean pecaEspecial = false;

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public char getConteudo() {
        return conteudo;
    }

    public void setConteudo(char conteudo) {
        this.conteudo = conteudo;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public boolean isPecaEspecial() {
        return pecaEspecial;
    }

    public void setPecaEspecial(boolean pecaEspecial) {
        this.pecaEspecial = pecaEspecial;
    }
}
