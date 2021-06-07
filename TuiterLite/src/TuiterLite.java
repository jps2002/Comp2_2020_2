import java.util.*;

// Resolução por João Pedro Silveira Gonçalves - DRE 120019402
/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static int TAMANHO_MAXIMO_TUITES = 120;

    // atributos

    private HashMap<String, Usuario> usuarios; // o conjunto de usuários identificados por seu email
    private ArrayList tuites; // o conjunto de tuites feitos pelos os usuários
    private HashMap<String, Integer> hashtags; // o conjunto de hashtags usadas identificadas por seu nome(ex: #LAB7) e qual sua frequência
    T tipoAnexo;

    // construtor

    public TuiterLite()
    {
        this.usuarios = new HashMap<String, Usuario>(); // criar uma hashmap para guardar usuarios
        this.tuites = new ArrayList(); // criar uma arrayList para guardar tuites
        this.hashtags = new HashMap<>(); // criar um hashmap para guardar hashtags
    }

    // métodos


    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {

        // se o usuario não estiver cadastrado, criar usuario e adicionar a lista de usuários
        if(!usuarios.containsKey(email))
        {
            Usuario novoUsuario = new Usuario(nome, email); // criar um usuário
            this.usuarios.put(email, novoUsuario); // adicionar o novo usuário à mapa de usuários dessa instância de tuiter lite
        }

        // retornar o Usuario do mapa de usuários relacionado ao email dado
        // ou seja, o usuário novo criado,caso ele não estivesse previamente cadastrado
        // ou um usuário preexistente, caso ele já tivesse sido cadastrado previamente
        return usuarios.get(email);
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) throws UsuarioDesconhecidoException, TamanhoMaximoExcedidoException {

        // se o usuário ou o texto forem nulos, lançar IllegalArgumentException
        if(usuario == null || texto == null)
            throw new IllegalArgumentException("Usuário ou texto nulos.");

        // se o texto estiver no tamanho certo pré-definido:
        if(texto.length() <= TAMANHO_MAXIMO_TUITES)
        {
            // se o usuário estiver cadastrado no mapa de usuários dessa instância de tuiter lite
            if(usuarios.containsKey(usuario.getEmail()))
            {
                // encontrar hashtags no texto
                Set<String> hashtagsEncontradas = Tuite.findHashtags(texto);
                // com as informações passadas(usuário e texto) e as hashtags encontradas no texto, criar um novo tuite
                Tuite novoTuite = new Tuite(usuario, texto, hashtagsEncontradas);
                // adicionar novo tuite ao sistema
                tuites.add(novoTuite);
                // adicionar hashtags ao sistema
                inserirHashtags(hashtagsEncontradas);
                // retornar o novo tuite
                return novoTuite;

            }
            else // se o usuário não estiver em usuarios
            {
                throw new UsuarioDesconhecidoException(); // lançar exceção
            }
        }
        else // se o texto não estiver no tamanho certo pre-definido
        {
            throw new TamanhoMaximoExcedidoException(texto); // lançar exceção
        }


        // se o texto está fora do limite ou o usuário não foi cadastrado previamente, retornar null
        //return null;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {

        // encontrar a maior frequência de uma hashtag cadastrada(dentre as previamente utilizadas)
        int maiorFrequenciaHashtag = Collections.max(hashtags.values());

        // iterar pelo hashmap das hashtags até encontrar a primeira hashtag que têm essa frequência
        for (Map.Entry<String, Integer> hashtag : hashtags.entrySet())
        {
            if(hashtag.getValue() == maiorFrequenciaHashtag)
            {
                return hashtag.getKey();
            }
        }

        // se não encontrou, nunca uma hashtag foi tuitada e devemos retornar null
        return null;
    }

    /**
     * Atualizar o sistema com as hashtags encontradas em um novo tuite.
     * @param hashtagsEncontradas no texto do novo tuite.
     */
    private void inserirHashtags(Set<String> hashtagsEncontradas)
    {
        // para cada hashtag encontrada no texto
        for (String hashtag: hashtagsEncontradas)
        {
            // se ela não estiver cadastrada
            if(!hashtags.containsKey(hashtag))
            {
                // cadastrá-la com frequência 0
                hashtags.put(hashtag, 0);
            }
            // atualizar sua frequência(agora com todas hashtags encontradas já cadastradas)
            hashtags.put(hashtag, hashtags.get(hashtag) + 1 );
        }
    }
}
