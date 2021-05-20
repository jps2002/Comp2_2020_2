import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {

        List<Usuario> emComum = new ArrayList<>();

        // para cada elemento da primeira lista, percorra a segunda lista até encontrá-lo (ou não)

        for(Usuario user1 : lista1)
        {
            for(Usuario  user2: lista2)
            {
                if (user1.getId() == user2.getId())
                {
                    emComum.add(user1);
                    break;
                }

                // possível otimização: quebrar a iteração se já tiver encontrado
            }
        }
        return emComum;
    }

    private boolean equals(Usuario user1, Usuario user2) {

        return user1.getId() == user2.getId();
    }
}
