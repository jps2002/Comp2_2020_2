import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Pacotinho {

    private Figurinha[] figurinhas;

    Random rand  = new Random();

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {

        // criar array com tamanho de posicoesDesejadas
        figurinhas = new Figurinha[posicoesDesejadas.length];

        // para cada item em posicoesDesejadas adicionar a figurinhas
       for(int i = 0; i < posicoesDesejadas.length; i++)
       {
           Figurinha figurinhaTemp = new Figurinha(posicoesDesejadas[i] , null);
           figurinhas[i] = figurinhaTemp;
       }

    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {

        int tamanhoRepositorio = repo.getTotalFigurinhas();

        figurinhas = new Figurinha[quantFigurinhas];

        for(int i = 0; i < quantFigurinhas; i++)
        {
            int posicaoAleatoria = rand.nextInt(tamanhoRepositorio);

            Figurinha novaFigurinha = new Figurinha(posicaoAleatoria, null);

            figurinhas[i] = novaFigurinha;

        }
    }

    public Figurinha[] getFigurinhas() {

        return figurinhas;
    }
}
