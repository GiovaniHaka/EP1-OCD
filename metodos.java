
public class metodos {

    // COMPLEMENTO-DE-2--------------------------------------------------------------------------------------
    // Função que faz complemento de 2 nos números binários
    public static int[] complemento2(int valorBits, int[] valor1) {
        //criamos dois arrays auxiliares para fazer  complemento
        int[] numeroEmComp2 = new int[valorBits];
        int[] somaUm = new int[valorBits];
        for (int i = 0; i < valorBits; i++) {
            somaUm[i] = 0;
        }
        somaUm[0] = 1;

        //alterna os valores, 1 se torna 0 e 0 se torna 1
        for (int i = 0; i < valorBits; i++) {
            if (valor1[i] == 0) {
                numeroEmComp2[i] = 1;
            } else if (valor1[i] == 1) {
                numeroEmComp2[i] = 0;
            }
        }

        //retorna a soma dos numero invertido com 1
        return soma(valorBits, numeroEmComp2, somaUm);
    }

    // SOMA----------------------------------------------------------------------------------------------------------
    // Funçao que realiza a soma de números binários, printando "Overflow" quando acontecer overflow
    public static int[] soma(int valorBits, int[] valor1, int[] valor2) {
        int[] resultado = new int[valorBits];
        for (int i = 0; i < valorBits; i++) {
            resultado[i] = 0;
        }
        int aux = 0;
        for (int i = 0; i < valorBits; i++) {
            if (valor1[i] == 0 && valor2[i] == 0) {
                if (aux == 1) {
                    resultado[i] = 1;
                    aux = 0;
                } else {
                    aux = 0;
                }
            } else if ((valor1[i] == 1 && valor2[i] == 0) || (valor1[i] == 0 && valor2[i] == 1)) {
                // verifica se vai dar overflow
                if (i == valorBits - 1 && aux == 1) {
                    System.out.println("Overflow");
                }

                if (aux == 1) {
                    aux = 1;
                } else {
                    resultado[i] = 1;
                    aux = 0;
                }
            } else if (valor1[i] == 1 && valor2[i] == 1) {
                // verifica se vai dar overflow
                if (i == valorBits - 1) {
                    System.out.println("Overflow");
                }

                if (aux == 1) {
                    resultado[i] = 1;
                    aux = 1;
                } else {
                    aux = 1;
                }
            }
        }
        return resultado;
    }

    // VALIDAR-ARRAY----------------------------------------------------------------------------------------------------
    // Coloca o valor inserido pelo usuário dentro de um array, sendo que o primeiro número da direita do numero binário está na posição 0 do array
    public static int[] validarArray(String valorString, int valorBits) {
        // Criando e zerando array auxiliar
        int[] valor = new int[valorBits];
        
        for (int i = 0; i < valorBits; i++) {
            valor[i] = 0;
        }

        // Colocando o valor inserido no array de trás para frente
        int f = 0;
        for (int i = valorString.length() - 1; i >= 0; i--) {
            if (valorString.charAt(i) == '0') {
                valor[f] = 0;
                f++;
            } else {
                valor[f] = 1;
                f++;
            }
        }
        return valor;

    }

    // ALGORITMO-DE-BOOTH-----------------------------------------------------------------------------------------------
    // O algoritmo de booth consiste em utilizar um array modificado de acordo com o valor1 ou valor2, comparando o mesmo com uma variavel "q"
    // e dependendo do resultado da comparacao, ocorre uma determinada alteracao no array modificado, repetindo o ciclo valorBits de vezes ate chegar
    // ao resultado
    public static int[] booth(int valorBits, int[] valor1, int[] valor2) {
        int y = 0;
        int q = 0;
        int tamanhoDaTabela = valorBits + valorBits;
        int[] complementoDoValor1 = new int[valorBits];
        int[] resultado = new int[tamanhoDaTabela];
        for (int i = 0; i < tamanhoDaTabela; i++) {
            resultado[i] = 0;
        }
        int[] a = new int[valorBits];
        for (int i = 0; i < valorBits; i++) {
            complementoDoValor1[i] = 0;
            a[i] = 0;
        }
        for (int i = valorBits - 1; i >= 0; i--) {
            resultado[i] = valor2[i];
        }
        complementoDoValor1 = complemento2(valorBits, valor1); // guardando o complemento do valor1 para realizar a subtracao (utilizando o metodo soma)

        while (y < valorBits) { // comparar valorBits de vezes
            if ((resultado[0] == 0 && q == 0) || (resultado[0] == 1 && q == 1)) {  // se o ultimo bit do array modificado for 0 e q for 0, ou o ultimo bit do array modificado for 1 e q for 1
                if (resultado[0] == 1) { 
                    if (resultado[tamanhoDaTabela - 1] == 1) {
                        resultado = deslocamento(tamanhoDaTabela, resultado);
                        resultado[tamanhoDaTabela - 1] = 1;
                        a = atualizaA(valorBits, resultado);
                        q = 1;
                    } else if (resultado[tamanhoDaTabela - 1] == 0) {
                        resultado = deslocamento(tamanhoDaTabela, resultado);
                        resultado[tamanhoDaTabela - 1] = 0;
                        q = 1;
                    }
                } else if (resultado[0] == 0) {
                    if (resultado[tamanhoDaTabela - 1] == 1) {
                        resultado = deslocamento(tamanhoDaTabela, resultado);
                        resultado[tamanhoDaTabela - 1] = 1;
                    } else if (resultado[tamanhoDaTabela - 1] == 0) {
                        resultado = deslocamento(tamanhoDaTabela, resultado);
                    }
                }
            } else if (resultado[0] == 1 && q == 0) { // se o ultimo bit do array modificado for 1 e q for 0
                a = soma(valorBits, a, complementoDoValor1);
                resultado = incrementarAAtualizado(a, resultado);

                if (resultado[tamanhoDaTabela - 1] == 1) {
                    resultado = deslocamento(tamanhoDaTabela, resultado);
                    a = atualizaA(valorBits, resultado);
                    q = 1;
                } else if (resultado[tamanhoDaTabela - 1] == 0) {
                    resultado = deslocamento(tamanhoDaTabela, resultado);
                    resultado[tamanhoDaTabela - 1] = 0;
                    a = atualizaA(valorBits, resultado);
                    q = 1;
                }
            } else if (resultado[0] == 0 && q == 1) { // se o ultimo bit do array modificado for 0 e q for 1
                a = soma(valorBits, a, valor1);
                resultado = incrementarAAtualizado(a, resultado);

                if (resultado[tamanhoDaTabela - 1] == 1) {
                    resultado = deslocamento(tamanhoDaTabela, resultado);
                    resultado[tamanhoDaTabela - 1] = 1;
                    a = atualizaA(valorBits, resultado);
                    q = 0;
                } else if (resultado[tamanhoDaTabela - 1] == 0) {
                    resultado = deslocamento(tamanhoDaTabela, resultado);
                    resultado[tamanhoDaTabela - 1] = 0;
                    a = atualizaA(valorBits, resultado);
                    q = 0;
                }
            }
            y++;
        }
        return resultado;
    }

    // ATUALIZA-A---------------------------------------------------------------------------------------------------
    public static int[] atualizaA(int valorBits, int[] resultado) {
        int[] a = new int[valorBits];
        for (int j = 0; j < valorBits; j++) {
            a[j] = 0;
        }
        int tamanhoDeRes = resultado.length - 1;
        for (int i = valorBits - 1; i >= 0; i--) {
            a[i] = resultado[tamanhoDeRes];
            tamanhoDeRes--;
        }
        return a;
    }

    // INCREMENTA-A-ATUALIZADO----------------------------------------------------------------------------------------
    public static int[] incrementarAAtualizado(int[] a, int[] resultado) {

        int tamanhoDeA = a.length;
        int tamanhoDoResultado = resultado.length;
        for (int i = tamanhoDeA - 1; i >= 0; i--) {
            resultado[tamanhoDoResultado - 1] = a[i];
            tamanhoDoResultado--;
        }
        return resultado;
    }

    // DESLOCAMENTO---------------------------------------------------------------------------------------------------
    public static int[] deslocamento(int tamanhoDaTabela, int[] resultado) {
        int[] aux = new int[tamanhoDaTabela];
        for (int i = 0; i < tamanhoDaTabela; i++) {
            aux[i] = 0;
        }
        int bitSignificativo = resultado[tamanhoDaTabela - 1];
        if (bitSignificativo == 1) {
            // aux[tamanhoDaTabela - 1] = 1;
            for (int i = tamanhoDaTabela - 1; i > 0; i--) {
                aux[i - 1] = resultado[i];
            }
            aux[tamanhoDaTabela - 1] = 1;
        }
        if (bitSignificativo == 0) {
            // aux[tamanhoDaTabela - 1] = 0;
            for (int i = tamanhoDaTabela - 1; i > 0; i--) {
                aux[i - 1] = resultado[i];
            }
            aux[tamanhoDaTabela - 1] = 0;
        }
        return aux;
    }

    // DESLOCAMENTO-FLOAT---------------------------------------------------------------------------------------------------
    // Faz o deslocamento do floast de acordo com o valor do expoente
    public static char[] deslocamentoFloat(int tamanhoArray, char[] resultado, int diferencaExpoente) {
        int cont = 0;
        int posicaoVirgula = 0;
        while (resultado[posicaoVirgula] != ',') {
            posicaoVirgula++;
        }

        while (cont < diferencaExpoente) {
            //  0  1  ,  1  0  0
            // [5][4][3][2][1][0]
            for (int i = 0; i < tamanhoArray - 1; i++) {
                if (i != posicaoVirgula) {
                    if (i + 1 == posicaoVirgula){
                        resultado[i] = resultado[i + 2];
                    } else {
                        resultado[i]= resultado[i + 1];
                    }
                } 
            }
            cont++;
        }
        return resultado;
    }

    // SOMA-FLOAT---------------------------------------------------------------------------------------------
    // Soma para floats considerando o caractér ","
    public static char[] somaFloat(int tamanhoArray, char[] valor1, char[] valor2) {
        int posicaoVirgula = 0;
        int aux = 0;
        char[] resultado = new char[tamanhoArray];

        for (int i = 0; i < tamanhoArray; i++) {
            resultado[i] = '0';
        }

        for (int i = tamanhoArray - 1; i >= 0; i--) {
            if (valor1[i] == '0' && valor2[i] == '0') {
                if (aux == 1) {
                    resultado[i] = '1';
                    aux = 0;
                } else {
                    aux = 0;
                }
            } else if ((valor1[i] == '1' && valor2[i] == '0') || (valor1[i] == '0' && valor2[i] == '1')) {
                if (aux == 1) {
                    aux = 1;
                } else {
                    resultado[i] = '1';
                    aux = 0;
                }
            } else if (valor1[i] == '1' && valor2[i] == '1') {
                if (aux == 1) {
                    resultado[i] = '1';
                    aux = 1;
                } else {
                    aux = 1;
                }
            } else if (valor1[i] == ',' && valor2[i] == ',') {
                resultado[i] = ',';
                posicaoVirgula = i;
            }
        }
        if (resultado[0] == '1') {
            char auxiliar;
            auxiliar = resultado[posicaoVirgula];
            resultado[2] = resultado[1];
            resultado[1] = auxiliar;
        }
        return resultado;
    }

    // COMPLEMENTO-DE-2-FLOAT--------------------------------------------------------------------------------------
    // Complemento de 2 para binarios float, considerando a vírgula
    public static char[] complemento2Float(int tamanhoArray, char[] valor1) {
        char[] numeroEmComp2 = new char[tamanhoArray];
        char[] somaUm = new char[tamanhoArray];
        somaUm = valor1;

        //Saber a posição da vírgula
        int posicaoVirgula = 0;
        while (valor1[posicaoVirgula] != ',') {
            posicaoVirgula++;
        }

        for (int i = 0; i < tamanhoArray; i++) {
            if (i!=posicaoVirgula){
                somaUm[i] = '0';
            } else {
                somaUm[i] = ',';
            }
        }

        somaUm[0] = '1';
        for (int i = 0; i < tamanhoArray; i++) {
            if (valor1[i] == '0') {
                numeroEmComp2[i] = '1';
            } else if (valor1[i] == '1') {
                numeroEmComp2[i] = '0';
            } else if (valor1[i] == ','){
                numeroEmComp2[i] = ',';
            }
        }
        return somaFloat(tamanhoArray, numeroEmComp2, somaUm);
    }

    // INVERTE-ARRAY----------------------------------------------------------------------------------------------------
    // Inverte a ordem do array
    public static char[] inverteArray(int tamanhoArray, char[] valor) {

        char[] aux = new char[tamanhoArray];
        for (int i = 0; i < tamanhoArray; i++) {
            aux[i] = '0';
        }

        int j = 0;
        for (int i = tamanhoArray - 1; i >= 0; i--) {
            aux[j] = valor[i];
            j++;
        }

        return aux;
    }

    // SHIFT-LEFT-A-----------------------------------------------------------------------------------------------------
    // Faz o deslocamento para a esquerda do array A da divisão
    public static int[] shiftLeftA(int[] vetorA, int valorBits, int[] dividendoQ, int count) {
        for (int j = valorBits - 1; j > 0; j--) {
            vetorA[j] = vetorA[j-1];
        }
        vetorA[0] = dividendoQ[count];

        return vetorA;
    }

    // SHIFT-LEFT-Q-----------------------------------------------------------------------------------------------------
    // Faz o deslocamento para a esquerda do Dividendo
    public static int[] shiftLeftQ(int[] dividendoQdois, int valorBits) {
        for (int i = valorBits - 1; i > 0; i--) {
            dividendoQdois[i] = dividendoQdois[i - 1];
        }
        return dividendoQdois;
    }

    // SUBTRAIR-----------------------------------------------------------------------------------------------------------
    // Função de subtração para divisão
    public static int[] subtrair(int valorBits, int[] valor1, int[] valor2) {
        int[] segundoValorComp2 = new int[valorBits];
        segundoValorComp2 = complemento2(valorBits, valor2);

        int[] resultado = soma(valorBits, valor1, segundoValorComp2);
        return resultado;
    }
}