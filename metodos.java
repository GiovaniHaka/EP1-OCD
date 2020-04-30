
public class metodos {

    // COMPLEMENTO-DE-2--------------------------------------------------------------------------------------
    public static int[] complemento2(int valorBits, int[] valor1) {
        int[] numeroEmComp2 = new int[valorBits];
        int[] somaUm = new int[valorBits];
        for (int i = 0; i < valorBits; i++) {
            somaUm[i] = 0;
        }
        somaUm[0] = 1;
        for (int i = 0; i < valorBits; i++) {
            if (valor1[i] == 0) {
                numeroEmComp2[i] = 1;
            } else if (valor1[i] == 1) {
                numeroEmComp2[i] = 0;
            }
        }

        return soma(valorBits, numeroEmComp2, somaUm);
    }

    // SOMA----------------------------------------------------------------------------------------------------------
    public static int[] soma(int valorBits, int[] valor1, int[] valor2) {
        int[] resultado = new int[valorBits];
        for (int i = 0; i < valorBits; i++) {
            resultado[i] = 0;
        }
        int aux = 0;
        boolean overflow = false;
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
                    overflow = true;
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
                    overflow = true;
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
    // O primeiro número da direita do numero binário está na posição 0 do array
    public static int[] validarArray(String valorString, int valorBits) {
        int[] valor = new int[valorBits];

        for (int i = 0; i < valorBits; i++) {
            valor[i] = 0;
        }

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
        complementoDoValor1 = complemento2(valorBits, valor1);

        while (y < valorBits) {
            if ((resultado[0] == 0 && q == 0) || (resultado[0] == 1 && q == 1)) { // P
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
            } else if (resultado[0] == 1 && q == 0) { // P2
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
            } else if (resultado[0] == 0 && q == 1) { // P3
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

    // ATUALIZA-A----------------------------------------------------------------------------------------------------
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
    //
    public static int[] shiftLeftA(int[] vetorA, int valorBits, int[] dividendoQ, int count) {
        for (int j = valorBits - 1; j > 0; j--) {
            vetorA[j] = vetorA[j-1];
        }
        vetorA[0] = dividendoQ[count];

        return vetorA;
    }

    // SHIFT-LEFT-Q-----------------------------------------------------------------------------------------------------
    public static int[] shiftLeftQ(int[] dividendoQdois, int valorBits) {
        for (int i = valorBits - 1; i > 0; i--) {
            dividendoQdois[i] = dividendoQdois[i - 1];
        }
        return dividendoQdois;
    }

    // SUBTRAIR-----------------------------------------------------------------------------------------------------------
    public static int[] subtrair(int valorBits, int[] valor1, int[] valor2) {
        int[] segundoValorComp2 = new int[valorBits];
        segundoValorComp2 = complemento2(valorBits, valor2);

        int[] resultado = soma(valorBits, valor1, segundoValorComp2);
        return resultado;
    }
}