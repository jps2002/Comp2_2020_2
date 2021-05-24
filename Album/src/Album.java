import java.awt.*;
import java.util.*;
import java.util.List;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Figurinha> figurinhasColadas;  // direct addressing
    private int quantFigurinhasColadas;

    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidasByPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.figurinhasColadas = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.figurinhasColadas.add(null);
        }
        this.quantFigurinhasColadas = 0;

        this.contRepetidasByPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Figurinha[] figurinhasDoPacotinho = pacotinho.getFigurinhas();
        if (figurinhasDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (Figurinha fig : pacotinho.getFigurinhas()) {
            final int posicao = fig.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida

                // jeito pior
//                Integer contRepetidas = this.contRepetidasByPosicao.get(posicao);
//                this.contRepetidasByPosicao.put(
//                        posicao, contRepetidas == null ? 1 : contRepetidas + 1);

                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidasByPosicao.getOrDefault(posicao, 0);
                this.contRepetidasByPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.figurinhasColadas.set(posicao, fig);
                this.quantFigurinhasColadas++;
            }
        }
    }

    public Figurinha getItemColado(int posicao) {
        return figurinhasColadas.get(posicao);
    }

    public boolean possuiItemColado(int posicao) {
        // para cada figurinha colada:
        //for( Figurinha figurinha : figurinhasColadas)
        //{
            // se a posicao da figurinha for igual a desejada, retorna true
            //if(figurinha.getPosicao() == posicao)
            //{
                //return true;
            //}
       // }
        // se não há figurinha colada com essa posicao, retorna false
        //return false;
        for (int i = 1; i <= quantFigurinhasColadas; i++)
        {
            if(figurinhasColadas.get(i).getPosicao() == posicao)
            {
                return true;
            }
        }

        return false;

    }

    public boolean possuiItemRepetido(int posicao) {
        int cont = contRepetidasByPosicao.getOrDefault(posicao, 0);
        if (cont> 0) return true;
        return false;
    }

    public int getTamanho() {
        return this.repositorio.getTotalFigurinhas();
    }

    public int getQuantItensColados() {
        return this.quantFigurinhasColadas;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        int tamanhoRepositorio = getTamanho();

        // descobrir minimo de figurinhas coladas para liberar autoCompletar
        int min = (int) Math.ceil(tamanhoRepositorio * PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100.0);

        // se atender aos critérios
        if(this.quantFigurinhasColadas >= min)
        {
                for(int i = 1; i <= tamanhoRepositorio; i++)
                {
                    if(!possuiItemColado(i))
                    {
                        Figurinha novaFigurinha = new Figurinha(i, null);
                        this.figurinhasColadas.set(i, novaFigurinha);
                    }
                }
        }

    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> meuArrayList = new ArrayList<>(200);
//
//        // inicializa as posições com nulls, para poder acessá-las diretamente
//        for (int i = 0; i < 200; i++) {
//            meuArrayList.add(null);
//        }
//
////        System.out.println(meuArrayList.get(3));
//
//        meuArrayList.add(3, 300000);  // insert com shift right
//
//        for (int numero : meuArrayList) {
//            System.out.println(numero);
//        }
//    }
}
