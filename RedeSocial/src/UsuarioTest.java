import static java.lang.System.currentTimeMillis;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;

class UsuarioTest {

    CalculadorIntersecao ingenuo = new CalculadorIntersecaoIngenuo();
    CalculadorIntersecao rapidinho= new CalculadorIntersecaoEsperto();
    CalculadorIntersecao medio= new CalculadorIntersecaoViaBuscaBinaria();


    Usuario joao;
    Usuario joana;
    Usuario jose;
    Usuario josefa;
    Usuario mario;
    Usuario maria;

    @BeforeEach
    void setUp() {

         joao = new Usuario(001, ingenuo);
         joana = new Usuario(002, rapidinho);
         jose = new Usuario(003, ingenuo);
         josefa = new Usuario(004, medio);
         mario = new Usuario(005, ingenuo);
         maria = new Usuario(006, medio);

        joao.addAmigo(jose);
        joao.addAmigo(mario);
        joao.addAmigo(jose);
        joao.addAmigo(mario);

        joana.addAmigo(josefa);
        joana.addAmigo(maria);
        joana.addAmigo(jose);
        joana.addAmigo(mario);

        maria.addAmigo(mario);

        josefa.addAmigo(joao);
        josefa.addAmigo(jose);
        josefa.addAmigo(mario);
        josefa.addAmigo(maria);
        josefa.addAmigo(joana);
    }

    @Test
    void getIdTest() {
        assertEquals(001, joao.getId());
        assertFalse(joao.getId() == 002);
    }

    @org.junit.jupiter.api.Test
    void getAmigosTest() {

    }

    @Test
    // QUESTÃO 2 e 3 DO LAB5
    void obterQuantidadeDeAmigosTest() {

        assertEquals(2,joao.obterQuantidadeDeAmigosEmComum(joana));
        assertEquals(3,josefa.obterQuantidadeDeAmigosEmComum(joana));
        assertFalse(maria.obterQuantidadeDeAmigosEmComum(joao) == 2);

    }
    @Test
    void obterQuantidadeDeAmigosComTempoTest()
    {
        Usuario usuarioNovo;
        int temp = 007;
        int qtdNovosAmigos = 100;
        for(int i = 0; i < qtdNovosAmigos; i++)
        {
            usuarioNovo= new Usuario(temp, rapidinho);
            joao.addAmigo(usuarioNovo);
            joana.addAmigo(usuarioNovo);
        }

        long inicioTempoIngenuo = currentTimeMillis();
        System.out.println("Amigos de joao em comum com joana: " + joao.obterQuantidadeDeAmigosEmComum(joana));
        long fimTempoIngenuo = currentTimeMillis();
        long tempoPassadoIngenuo = fimTempoIngenuo - inicioTempoIngenuo;
        System.out.println("Tempo de execução do ingenuo em milissegundos: " + tempoPassadoIngenuo);

        long inicioTempoRapido = currentTimeMillis();
        System.out.println("Amigos de joana em comum com joao: " + joana.obterQuantidadeDeAmigosEmComum(joao));
        long fimTempoRapido = currentTimeMillis();
        long tempoPassadoRapido = fimTempoRapido - inicioTempoRapido;
        System.out.println("Tempo de execução do rapido em milissegundos: " + tempoPassadoIngenuo);






    }

    @Test
    // questão 4
    void questao4() {

    }

}