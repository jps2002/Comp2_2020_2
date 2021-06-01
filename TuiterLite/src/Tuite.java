import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tuite {

    private final Usuario autor;
    private final String texto;
    private final Set<String> hashtags;
    private Object anexo;

    public Tuite(Usuario autor, String texto, Set<String> hashtags) {
        this.autor = autor;
        this.texto = texto;
        this.hashtags = hashtags;
    }

    public void anexarAlgo(Object anexo) {
        this.anexo = anexo;
    }

    /**
     * Encontra as hashtags em um texto.
     * @param texto a ser analisado e de onde serão extraídas as hashtags
     * @return hashset com as hashtags em strings separadas
     */
    public static Set<String> findHashtags(String texto)
    {
        // criar novo hashset
        Set<String> hashtagsEncontradas = new HashSet<String>();

        // adicionar as hashtags encontradas em texto ao hashset criado
        Pattern padrao = Pattern.compile("(#+[a-zA-Z0-9(_)]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = padrao.matcher(texto);
        while(matcher.find()) {
            String hashtag = matcher.group(1);
            hashtagsEncontradas.add(hashtag);
        }

        // retornar o hashset com as hashtags encontradas
        return hashtagsEncontradas;
    }

    public Object getAnexo() {
        return this.anexo;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public Set<String> getHashtags() {
        return this.hashtags;
    }

    public static void main(String[] args) {

        // Testes para encontrar o melhor algoritmo que utiliza regex para encontrar hashtags em strings

        // usando:(#+[a-zA-Z0-9(_)]+)
        Pattern padrao = Pattern.compile("(#+[a-zA-Z0-9(_)]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = padrao.matcher("oi #hello joao #pedro#joao");
        while(matcher.find()) {
            String hashtag = matcher.group(1);
            System.out.println("Hashtag encontrada: " + hashtag);
        }

        //usando: caracteres de escape
        Pattern padrao2 = Pattern.compile(".*?(#+\\w+).*?");
        Matcher matcher2 = padrao2.matcher("oi #hello joao #pedro#joao");
        while(matcher2.find())
        {
            String hashtag = matcher2.group(1);
            System.out.println("Hashtag encontrada: " + hashtag);
        }
    }
}
