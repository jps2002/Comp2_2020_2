import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Pares {

    // methods

    public static void encontrarPar(List<Integer> lista, int k)
    {
        // 2 mapas: um para guardar os numeros que vem com a lista(só para utilizar a velocidade de hash)
        // e outro para guardar os pares que eu vou achando, mas sem repetição
        HashMap<Integer, Integer> meusNumeros = new HashMap<>();
        HashMap<Integer,Integer > meusPares = new HashMap<>();

        // adicionar todos os numeros na lista ao mapa meusNumeros como chaves
        for (int numero : lista)
        {
            meusNumeros.put(numero, 0);
        }
        // para cada numero na lista, verificar se o complemento dele é uma chave de meusNumeros
        for(int i = 0; i < lista.size(); i++)
        {
            int numeroDaVez = lista.get(i);
            int numeroNecessario = k-numeroDaVez;
            // se for uma chave, isso significa que o complemento existe i.e. a soma de ambos é igual à soma desejada
            if(meusNumeros.get(numeroNecessario) != null)
            {
                // se for uma chave, devemos procurar se o par já não foi encontrando antes, procurando em meusPares
                if(meusPares.get(numeroNecessario) == null)
                {
                    meusPares.put(numeroDaVez, numeroNecessario);
                }

            }
        }

        // imprimir os pares
        meusPares.forEach((key, value) -> {
            System.out.println("Par: (" + key + ", " + value + ")");
        });

    }

    public static void main(String[] args) {

        // teste
        List<Integer> lista = Arrays.asList(1, 5, -10, 56, 44, 12, 18);
        List<Integer> lista2 = Arrays.asList(10, 9, 5, 9, 0, 10, 2, 10, 1, 9);
        int soma = 34;
        int soma2 = 12;
        encontrarPar(lista, soma);
        encontrarPar(lista2, soma2);

    }


}
