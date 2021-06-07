public class TamanhoMaximoExcedidoException extends Exception {

    private int tamanhoTexto = 0;
    private String texto;

    public TamanhoMaximoExcedidoException (String texto)
    {
        this.texto = texto;
        this.tamanhoTexto = texto.length();
    }
    public int getTamanhoTexto() {
        return this.tamanhoTexto;
    }


}
