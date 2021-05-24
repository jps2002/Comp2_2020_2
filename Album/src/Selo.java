import java.awt.*;

public class Selo implements Colecionavel {


    private final Image imagem;
    private final int posicao;
    private String pais;

    private float valorMonetario;

    public Selo(int posicao, String urlDaImagem, float valorMonetario) {
        this.imagem = obterImagem(urlDaImagem);
        this.posicao = posicao;
        this.valorMonetario = valorMonetario;

    }

    public float getValorNominal()
    {
        return this.valorMonetario;
    }

    public String getPais()
    {
        return this.pais;
    }

    private Image obterImagem(String url) {
        // ToDo baixaria da Internet...
        return null;
    }

    @Override
    public Image getImagem() {
        return this.imagem;
    }

    @Override
    public int getPosicao() {
        return this.posicao;
    }
}
