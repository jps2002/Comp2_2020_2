import java.util.HashMap;
import java.util.Map;

public class Turma {

    public Disciplina disciplina;

    public Periodo periodo;

    public Professor professor;

    private HashMap<Long, Aluno> alunos = new HashMap<Long, Aluno>();

    private HashMap<Long, Float> notasFinais = new HashMap<Long, Float>();


    /**
     * Adicionar aluno à turma
     * @param aluno aluno previamente cadastrado em alunos do siga
     * @throws IllegalArgumentException
     */
    public void inscreverAluno(Aluno aluno) throws IllegalArgumentException
    {
        if(aluno == null) throw new IllegalArgumentException("Erro: null aluno");

        long dre = aluno.getDre();

        // inscrever o aluno somente se ele existir no siguinha

        // verificar se o aluno não está no siguinha
        if(Siguinha.obterAluno(dre) == null)
        {
            System.out.println("Erro: aluno não cadastrado no siga previamente");
            throw new IllegalArgumentException("Erro: aluno não cadastrado no siga previamente");
        }
        else // o aluno existe no siguinha
        {
            alunos.put(dre, aluno);
        }

    }

    /**
     * Insere  a media final do aluno no HashMap notasFinais da instância do objeto turma.
     * @param dre
     * @param nota
     * @throws IllegalAccessException caso o dre não esteja cadastrado na turma
     */
    public void atribuirMediaFinal(long dre,  float nota) throws IllegalAccessException
    {
        // verificar se  aluno está na lista de alunos da turma

        if(alunos.containsKey(dre)) // o aluno está inscrito na turma
        {
            // atribuir ao seu DRE essa nota media final
            notasFinais.put(dre, nota);
;        }
        else // o aluno não está inscrito na turma
        {
            // imprimir mensagem de erro
            System.out.println("Error: aluno não cadastrado na turma");
            throw new IllegalAccessException("aluno não cadastrado na turma");
        }
    }


    /**
     * Retorna a media final do aluno(identificado pelo dre) que está no HashMap da instância do objeto.
     * @param dre
     * @return float media final do aluno
     * @throws IllegalAccessException caso dre não esteja inscrito na turma
     */
    public float obterMediaFinal(long dre) throws IllegalAccessException
    {
        if(!alunos.containsKey(dre)) // o aluno não está inscrito na turma
        {
            System.out.println("Error: aluno não cadastrado na turma");
            throw new IllegalAccessException("aluno não cadastrado na turma");
        }

        return this.notasFinais.get(dre);
    }

    public String listarAlunos()
    {
        String output = "Lista de alunos:\n";
        for (Map.Entry<Long, Aluno> parDreAluno : this.alunos.entrySet()) {
            Long dre = parDreAluno.getKey();
            Aluno aluno = parDreAluno.getValue();
            output += "DRE: " + dre + " - NOME: " + aluno.nome + "\n";
        }
        return output;
    }




}
