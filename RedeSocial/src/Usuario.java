import java.util.ArrayList;
import java.util.List;

public class Usuario implements Comparable<Usuario> {

    private final int id;

    private List<Usuario> amigos = new ArrayList<>();

    private CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id, CalculadorIntersecao calculador) {
        // instancia um calculador de interseção
        this.id = id;
        this.calculadorIntersecao = calculador;
    }

    public int getId() {
        return id;
    }

    public void addAmigo(Usuario usuario)
    {
        amigos.add(usuario);
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }

    public static void main(String[] args) {
        CalculadorIntersecaoIngenuo ingenuo = new CalculadorIntersecaoIngenuo();
        Usuario joao = new Usuario(001, ingenuo);
        Usuario joana = new Usuario(002, ingenuo);
        Usuario jose = new Usuario(003, ingenuo);
        Usuario josefa = new Usuario(004, ingenuo);
        Usuario mario = new Usuario(005, ingenuo);
        Usuario maria = new Usuario(006, ingenuo);

        joao.addAmigo(jose);
        joao.addAmigo(mario);
        joana.addAmigo(josefa);
        joana.addAmigo(maria);
        joana.addAmigo(jose);

        System.out.println(joao.amigos);
        System.out.println(joao.obterQuantidadeDeAmigosEmComum(joana));
    }
}
