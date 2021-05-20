import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Texto {

    static void encontrarCaracterMaisFrequente(String string) {

        // salvar caracteres da string em um mapa (caractere, frequência)
        HashMap<Character, Integer> meusCaracteres = new HashMap<>();

        // para cada caractere da string:
        for (int i = 0; i < string.length(); i++) {
            char caractere = Character.toLowerCase(string.charAt(i));

            // se ele não for uma chave do mapa, adicioná-lo com frequência 0
            if (!meusCaracteres.containsKey(caractere)) meusCaracteres.put(caractere, 0);

            // aumentar sua frequencia(valores no mapa) em 1 unidade
            meusCaracteres.put(caractere, meusCaracteres.get(caractere) + 1);
        }

        // usar Collections.max para descobrir a maior frequencia em meusCaracteres
        int maiorFrequencia = (Collections.max(meusCaracteres.values()));
        // imprimir caractere em meusCaracteres com a maior frequencia descoberta anteriormente
        // iterar por meusCaracteres
        for (Map.Entry<Character, Integer> entradas : meusCaracteres.entrySet()) {
            // se o valor da frequencia é igual ao da maior frequencia, imprimir.
            if (entradas.getValue() == maiorFrequencia) {
                System.out.println("Caractere mais frequente: '" + entradas.getKey() + "' que aparece " + maiorFrequencia + " vezes.");     // Print the key with max value
            }
        }

    }

    public static void main(String[] args) {
        // teste
        encontrarCaracterMaisFrequente("A ararinha comeu a bananinha que estava na mochila da Aninha.");
    }
}

