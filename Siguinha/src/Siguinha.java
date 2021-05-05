import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {

    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    String instituicaoDeEnsino;

    // questão 1
    public static HashMap<Long, Aluno> alunos = new HashMap<Long, Aluno>();

    // questão 2
    public static void cadastrarAluno(long dre, String nome)
    {
        Aluno novoAluno = new Aluno(dre, nome);
        alunos.put(dre, novoAluno);
    }

    public static void cadastrarAluno(Aluno aSerCadastrado)
    {
        long dre = aSerCadastrado.getDre();
        alunos.put(dre, aSerCadastrado);
    }

    // questão 3
    public static Aluno obterAluno(long dre)
    {
        Aluno encontrado = null;
        for(Long numero : alunos.keySet())
        {
            if(numero == dre)
            {
                encontrado = alunos.get(numero);
            }
        }
        return encontrado;
    }

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) throws IllegalAccessException {

        // teste rápido de turma

        Aluno a1 = new Aluno (1234, "joao");
        Aluno a2 = new Aluno (12345, "joaozinho");
        Aluno a3 = new Aluno (123456, "joaozao");
        Aluno a4 = new Aluno (1234567, "joaozaoao");

        /*cadastrarAluno(1234, "joao");
        cadastrarAluno(123456, "joaozinho");
        cadastrarAluno(1234567, "joaozao");*/

        cadastrarAluno(a1);
        cadastrarAluno(a2);
        cadastrarAluno(a3);

        Turma turminha = new Turma();

        turminha.inscreverAluno(a1);
        turminha.inscreverAluno(a2);
        turminha.inscreverAluno(a3);

        turminha.atribuirMediaFinal(123456, 10);

        System.out.println(turminha.listarAlunos());

    }
}
