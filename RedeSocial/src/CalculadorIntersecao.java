import java.util.List;

public interface CalculadorIntersecao {

    // possui um único método: retornar uma lista dos amigos em comum

    /**
     * Obtém a interseção entre 2 listas de usuários.
     * @param lista1 de usuários
     * @param lista2 de usuários
     * @return list com os usuarios em comum
     **/
    List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2);
}
