package Labirinto;

public class Labirinto {
    private static final int TAMANHO = 12;
    private static char[][] tabuleiro;
    private static final double PROBABILIDADE = 0.99;
    private static final char inicio = 'I';
    private static final char destino = 'D';
    private static final char PAREDE_HORIZONTAL = '_';
    private static final char PAREDE_VERTICAL = '|';
    private static final char PAREDE_INTERNA = '@';
    private static final char SEM_CAMINHO = 'x';
    private static final char CAMINHO_VAZIO = ' ';
    private static final char CAMINHO = '.';
    private static int linhaInicio;
    private static int colunaInicio;


    private static void criaTabuleiro(){


        //cria as paredes horizontais '_' e verticais '|'
        for(int i = 0; i < TAMANHO; i++){
            tabuleiro[0][i] = PAREDE_HORIZONTAL;
            tabuleiro[TAMANHO - 1][i] = PAREDE_HORIZONTAL;
            tabuleiro[i][0] = PAREDE_VERTICAL;
            tabuleiro[i][TAMANHO - 1] = PAREDE_VERTICAL;
        }

        //cria as paredes internas '@' e o caminho que ficará vazio
        for (int i = 1; i < TAMANHO - 1; i++){
            for (int j = 1; j < TAMANHO - 1; j++){
                if (Math.random() > PROBABILIDADE){
                    tabuleiro[i][j] = PAREDE_INTERNA;
                } else {
                    tabuleiro[i][j] = CAMINHO_VAZIO;
                }
            }
        }
        //define os locais de inicio e destino do jogo
        linhaInicio = gerarNumero(1, TAMANHO - 2);
        colunaInicio = gerarNumero(1, TAMANHO - 2);
        tabuleiro[linhaInicio][colunaInicio] = inicio;
        int linhaDestino = gerarNumero(1, TAMANHO - 2);
        int colunaDestino = gerarNumero(1, TAMANHO - 2);
        tabuleiro[linhaDestino][colunaDestino] = destino;
    }

    private static int gerarNumero(int minimo, int maximo){
        //puxa um numero aleatório para definir os locais de inicio e destino
        int valor = (int) Math.round(Math.random() * (maximo-minimo));
        //retorna o valor
        return minimo + valor;
    }
    private static void desenhaTabuleiro(){
        try {
            //pausa de 0.3 segundos
            Thread.sleep(300);
            //desenha o tabuleiro
            for (int i = 0; i < TAMANHO; i++){
                for (int j = 0; j < TAMANHO; j++ ){
                    System.out.print(tabuleiro[i][j]);
                }
                System.out.println();
            }
        } catch (Exception e){
            //se ocorrer algum erro, a mensagem abaixo será impressa
            System.err.println("Erro ao tentar imprimir o tabuleiro.");
        }
    }
    //faz o percurso dentro do tabuleiro até encontrar o destino
    private static boolean procurarCaminho(int linhaAtual, int colunaAtual){
        int proximaLinha;
        int proximaColuna;
        boolean achou;

        //tenta subir
        proximaLinha = linhaAtual - 1;
        proximaColuna = colunaAtual;
        achou = tentarCaminho(proximaLinha, proximaColuna);

        //tenta descer
        if(!achou){
            proximaLinha = linhaAtual + 1;
            proximaColuna = colunaAtual;
            achou = tentarCaminho(proximaLinha, proximaColuna);
        }

        //tenta à esquerda
        if(!achou){
            proximaLinha = linhaAtual;
            proximaColuna = colunaAtual - 1;
            achou = tentarCaminho(proximaLinha, proximaColuna);
        }
        //tenta à direita
        if(!achou){
            proximaLinha = linhaAtual;
            proximaColuna = colunaAtual + 1;
            achou = tentarCaminho(proximaLinha, proximaColuna);
        }

        return achou;
    }

    private static boolean tentarCaminho(int proximaLinha, int proximaColuna) {
        boolean achou = false;
        //verifica se a localização atual é a mesma do destino
        if (tabuleiro[proximaLinha][proximaColuna] == destino){
            achou = true;
        } else if (posicaoVazia(proximaLinha,proximaColuna)) {
            //se a posição for vazia, será adicionado o carectere de caminho '.'
            tabuleiro[proximaLinha][proximaColuna] = CAMINHO;
            desenhaTabuleiro();
            achou = procurarCaminho(proximaLinha,proximaColuna);
            if(!achou){
                //se não tiver caminho, o carectere 'x' será impresso
                tabuleiro[proximaLinha][proximaColuna] = SEM_CAMINHO;
                desenhaTabuleiro();
            }
        }
        return achou;
    }

    private static boolean posicaoVazia(int linha, int coluna) {
        boolean vazio = false;
        //retorna true se a posição atual for vazia
        if (linha >= 0 && coluna >= 0 && linha < TAMANHO && coluna < TAMANHO){
            vazio = (tabuleiro[linha][coluna] == CAMINHO_VAZIO);
        }
        return vazio;
    }

    public static void main(String[] args) {
        //Inicia a variavel tabuleiro com o tamanho da constante TAMANHO
        tabuleiro = new char[TAMANHO][TAMANHO];
        criaTabuleiro();
        desenhaTabuleiro();

        System.out.println("\n -PROCURANDO SOLUÇÃO -\n");
        boolean achou = procurarCaminho(linhaInicio, colunaInicio);
        if(achou){
            System.out.println("ACHOU O CAMINHO");
        } else {
            System.out.println("NÃO TEM CAMINHO");
        }
    }
}
