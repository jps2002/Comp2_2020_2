public class Pessoa {

    public final static int TAMANHO_MAXIMO_DO_NOME = 30;
    // instance variables

    protected String nome;
    protected int anoDeNascimento;


    // constructors


    //methods

    public String getNome() {
        return this.nome;
    }


    public int getAnoNascimento() {
        return anoDeNascimento;
    }

    public int getIdade() {
        return Siguinha.obterAnoCorrente() - anoDeNascimento;
    }

    public void setNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_DO_NOME) {
            // ToDo: lançar uma exceção!!!
            System.out.println("Error: tamanho excede(30 caracteres).");
            return;
        }
        this.nome = nome;
    }


}
