# LabirintoJava
Labirinto que se resolve sozinho.

É criado um tabuleiro de tamanho padrão 12x12 que pode
ser alterado.

Logo após a criação da matriz do tabuleiro, 
o método "criaTabuleiro()" é chamado, e é o responsável
pelo preenchimento da matriz com as paredes verticais e 
horizontais do tabuleiro, e também as paredes internas, 
representadas por '@'.
Depois da criação das paredes, é chamado o método "criaLinhaColuna()", 
responsável pela criação da linha e coluna de início e destino, com o
auxílio do método "gerarNumero(minimo, maximo)" que é o responsável por
retornar um número aleatório dentro das dimensões do tabuleiro.
O método "criaLinhaColuna()" então utiliza a estrutura de decisão 'if' 
para chamar um outro método, o "verDisponibilidade(linha, coluna)" para 
verificar se o índice de início e destino estão cercados por paredes.
Se estiverem, será feita uma recursão do método "criaLinhaColuna()" até que
os valores de início e destino não estejam completamente cercados por paredes.

Após a criação do tabuleiro, o método "desenhaTabuleiro()" é chamado, e faz a 
primeira impressão do tabuleiro, com uma pausa inicial de 0.3 segundos, 
dentro de um bloco de "try-catch". Se alguma exceção ocorre, apenas será
impressa a mensagem "Erro ao tentar imprimir o tabuleiro.".

Após isso, é hora de procurar o caminho do índice de início ao de destino,
e com isso é definido o boolean "achou", vinculado ao método 
"procurarCaminho(linhaI, colunaI)" que será responsável por se movimentar 
pelo tabuleiro, e chama o método "tentarCaminho(proxLinha, proxColuna)" 
para verificar se a posição nova é a mesma que a do destino, se não for 
é desenhado no local o caractere "-" para demonstrar o caminho sendo feito, 
caso o local que o caminho tentar seja sem saída ou já tenha passado por ele, 
o caractere "x" será atribuído àquele local, como demonstração de "sem saída".

Se o caminho não for encontrado, o método "procurarCaminho()" atribuirá o 
valor "false" ao boolean "achou", caso contrário o boolean receberá o valor 
"true" que será verificado por um "if-else", e irá imprimir se o caminho foi 
encontrado ou não.
