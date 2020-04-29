
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
                 //verifica se vai dar overflow
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
                //verifica se vai dar overflow
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
    //O primeiro número da direita do numero binário está na posição 0 do array
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
			if ((resultado[0] == 0 && q == 0) || (resultado[0] == 1 && q == 1)) {  // P
				if (resultado[0] == 1) {
					if (resultado[tamanhoDaTabela -1 ] == 1) {
						resultado = deslocamento(tamanhoDaTabela, resultado);
						resultado[tamanhoDaTabela -1] = 1;
						a = atualizaA(valorBits, resultado);
						q = 1;
					} else if (resultado[tamanhoDaTabela -1] == 0) {
						resultado = deslocamento(tamanhoDaTabela, resultado);
						resultado[tamanhoDaTabela -1] = 0;
						q = 1;						
					}
				} else if (resultado[0] == 0) {
					if (resultado[tamanhoDaTabela -1] == 1) {
						resultado = deslocamento(tamanhoDaTabela, resultado);
						resultado[tamanhoDaTabela -1] = 1;
					} else if (resultado[tamanhoDaTabela -1] == 0) {
						resultado = deslocamento(tamanhoDaTabela, resultado);
					}
				}
			} else if (resultado[0] == 1 && q == 0) {   // P2
				a = soma(valorBits, a, complementoDoValor1);
                resultado = incrementarAAtualizado(a, resultado);
                
				if (resultado[tamanhoDaTabela -1] == 1) {
					resultado = deslocamento(tamanhoDaTabela, resultado);
					a = atualizaA(valorBits, resultado);
					q = 1;		
				} else if (resultado[tamanhoDaTabela -1 ] == 0) {
					resultado = deslocamento(tamanhoDaTabela, resultado);
					resultado[tamanhoDaTabela -1] = 0;
					a = atualizaA(valorBits, resultado);
					q = 1;
				}
			} else if (resultado[0] == 0 && q == 1) { //   P3
				a = soma(valorBits, a, valor1);
				resultado = incrementarAAtualizado(a, resultado);

				if (resultado[tamanhoDaTabela -1] == 1) {
					resultado = deslocamento(tamanhoDaTabela, resultado);
					resultado[tamanhoDaTabela -1 ] = 1;
					a = atualizaA(valorBits, resultado);
					q = 0;
				} else if (resultado[tamanhoDaTabela -1] == 0) {
					resultado = deslocamento(tamanhoDaTabela, resultado);
					resultado[tamanhoDaTabela -1] = 0;
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
		for(int j = 0; j < valorBits; j++){
			a[j] = 0;
		}
		int tamanhoDeRes = resultado.length -1;
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
		for (int i = tamanhoDeA -1 ; i >= 0;  i--) {
			resultado[tamanhoDoResultado -1] = a[i];
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
		int bitSignificativo = resultado[tamanhoDaTabela -1];
		if (bitSignificativo == 1) {
			//aux[tamanhoDaTabela - 1] = 1;
			for (int i = tamanhoDaTabela - 1; i > 0; i--) {
				aux[i - 1] = resultado[i];
			}
			aux[tamanhoDaTabela - 1] = 1;
		}
		if (bitSignificativo == 0) {
			//aux[tamanhoDaTabela - 1] = 0;
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
        while (resultado[posicaoVirgula] != ','){
            posicaoVirgula++;
        }

        while (cont < diferencaExpoente){
            char aux = resultado[posicaoVirgula + 1];
            resultado[posicaoVirgula + 1] = resultado[posicaoVirgula];
            resultado[posicaoVirgula] = aux;
            posicaoVirgula++;
            cont++;
        }
		return resultado;
	}
    // INVERTE-ARRAY----------------------------------------------------------------------------------------------------
    public static char[] inverteArray(int tamanhoArray, char[] valor){
        
        char[] aux = new char[tamanhoArray];
		for (int i = 0; i < tamanhoArray; i++) {
            aux[i] = '0';
        }
        
        int j = 0;
        for (int i = tamanhoArray - 1; i >= 0; i--){
            aux [j] = valor[i];
            j++;
        }

        return aux;
    }
    // SHIFT-LEFT-A-----------------------------------------------------------------------------------------------------
    public static int[] shiftLeftA(int[] vetorA, String primeiroValor, int[] dividendoQ) {
        for (int j = 0; j < primeiroValor.length() - 2; j++) {
            // if (j!=primeiroValor.length()-1){
            vetorA[j] = vetorA[j + 1];
            // } else {
            // vetorA[primeiroValor.length()] = dividendoQ[primeiroValor.length()];

        }
        vetorA[0] = dividendoQ[primeiroValor.length() - 1];

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