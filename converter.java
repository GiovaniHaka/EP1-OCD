import java.util.Scanner;

public class converter {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"Digite \"1\" para calcular em Inteiros. \nDigite \"2\" para calcular com Ponto Flutuante. \nDigite \"3\" para sair do programa.");
		int operacao = input.nextInt();

		// OPERAÇÕES COM INTEIROS
		if (operacao == 1) {
			System.out.println("Insira a quantidade de bits: ");
			int valorBits = input.nextInt();
			System.out.println(
					"Insira \"+\" para somar ou insira \"-\" para subtrair ou insira \"*\" para multiplicar ou insira \"/\" para dividir ");
			String tipoDeOperacao = input.next();
			System.out.println("Insira o sinal do primeiro número: ");
			String sinalPrimeiroValor = input.next();
			System.out.println("Insira primeiro número binário: ");
			String primeiroValor = input.next();
			System.out.println("Insira o sinal do segundo número: ");
			String sinalSegundoValor = input.next();
			System.out.println("Insira o segundo número binário: ");
			String segundoValor = input.next();

			int[] valor11 = new int[valorBits];
			int[] valor22 = new int[valorBits];
			int[] resultadoH = new int[valorBits];

			for (int i = 0; i < valorBits; i++) {
				resultadoH[i] = 0;
			}

			// Colocar primeiroValor em array
			valor11 = metodos.validarArray(primeiroValor, valorBits);

			// Colocar segundoValor em array
			valor22 = metodos.validarArray(segundoValor, valorBits);

			resultadoH = metodos.soma(valorBits, valor11, valor22);

			// OPERAÇÃO DE SOMA
			if (tipoDeOperacao.equals("+")) {

				int[] valor1 = new int[valorBits];
				int[] valor2 = new int[valorBits];
				int[] resultado = new int[valorBits];

				for (int i = 0; i < valorBits; i++) {
					resultado[i] = 0;
				}

				// Colocar primeiroValor em array
				valor1 = metodos.validarArray(primeiroValor, valorBits);

				// Colocar segundoValor em array
				valor2 = metodos.validarArray(segundoValor, valorBits);

				// Se for soma de positivos
				if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("+")) {
					resultado = metodos.soma(valorBits, valor1, valor2);

				}
				// Soma de positivo com negativo
				else if ((sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("+"))) {
					int[] primeiroValorComp2 = new int[valorBits];
					primeiroValorComp2 = metodos.complemento2(valorBits, valor1);
					resultado = metodos.soma(valorBits, primeiroValorComp2, valor2);

				}
				// Soma de negativo com positivo
				else if ((sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("-"))) {
					int[] segundoValorComp2 = new int[valorBits];
					segundoValorComp2 = metodos.complemento2(valorBits, valor2);
					resultado = metodos.soma(valorBits, valor1, segundoValorComp2);

				}
				// Soma de negativos
				else {
					int[] primeiroValorComp2 = new int[valorBits];
					primeiroValorComp2 = metodos.complemento2(valorBits, valor1);

					int[] segundoValorComp2 = new int[valorBits];
					segundoValorComp2 = metodos.complemento2(valorBits, valor2);

					resultado = metodos.soma(valorBits, primeiroValorComp2, segundoValorComp2);

				}

				// Imprime resultado
				System.out.print("Valor1: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor1[k]);
				}

				System.out.println();
				System.out.print("Valor2: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor2[k]);
				}

				System.out.println();
				System.out.print("Resultado: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(resultado[k]);
				}
			}

			// OPERAÇÃO DE SUBTRAÇÃO
			else if (tipoDeOperacao.equals("-")) {

				int[] valor1 = new int[valorBits];
				int[] valor2 = new int[valorBits];
				int[] resultado = new int[valorBits];

				for (int i = 0; i < valorBits; i++) {
					resultado[i] = 0;
				}

				// Colocar primeiroValor em array
				valor1 = metodos.validarArray(primeiroValor, valorBits);

				// Colocar segundoValor em array
				valor2 = metodos.validarArray(segundoValor, valorBits);

				// Se for subtração de positivos
				if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("+")) {
					// Transformar segundo valor em negativo
					int[] segundoValorComp2 = new int[valorBits];
					segundoValorComp2 = metodos.complemento2(valorBits, valor2);

					resultado = metodos.soma(valorBits, valor1, segundoValorComp2);
				}

				// Subtração de negativo com positivo
				else if ((sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("+"))) {
					int[] primeiroValorComp2 = new int[valorBits];
					primeiroValorComp2 = metodos.complemento2(valorBits, valor1);

					int[] segundoValorComp2 = new int[valorBits];
					segundoValorComp2 = metodos.complemento2(valorBits, valor2);

					resultado = metodos.soma(valorBits, primeiroValorComp2, segundoValorComp2);
				}

				// Subtração de positivo com negativo
				else if ((sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("-"))) {
					resultado = metodos.soma(valorBits, valor1, valor2);
				}

				// Subtração de negativos
				else {
					int[] primeiroValorComp2 = new int[valorBits];
					primeiroValorComp2 = metodos.complemento2(valorBits, valor1);

					resultado = metodos.soma(valorBits, primeiroValorComp2, valor2);
				}

				// Imprime resultado
				System.out.print("Valor1: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor1[k]);
				}

				System.out.println();
				System.out.print("Valor2: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor2[k]);
				}

				System.out.println();
				System.out.print("Resultado: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(resultado[k]);
				}
			}

			// OPERAÇÃO DE MULTIPLICAÇÃO
			else if (tipoDeOperacao.equals("*")) {

				int[] valor1 = new int[valorBits];
				int[] valor2 = new int[valorBits];
				int[] resultado = new int[valorBits + valorBits];

				// Colocar primeiroValor em array
				valor1 = metodos.validarArray(primeiroValor, valorBits);

				// Colocar segundoValor em array
				valor2 = metodos.validarArray(segundoValor, valorBits);

				if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("+")) {
					resultado = metodos.booth(valorBits, valor1, valor2);

				} else if (sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("+")) {
					valor1 = metodos.complemento2(valorBits, valor1);
					resultado = metodos.booth(valorBits, valor1, valor2);

				} else if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("-")) {
					valor2 = metodos.complemento2(valorBits, valor2);
					resultado = metodos.booth(valorBits, valor1, valor2);

				} else if (sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("-")) {
					valor1 = metodos.complemento2(valorBits, valor1);
					valor2 = metodos.complemento2(valorBits, valor2);
					resultado = metodos.booth(valorBits, valor1, valor2);
				}

				// Imprime resultado
				System.out.println();
				System.out.print("Valor1: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor1[k]);
				}

				System.out.println();
				System.out.print("Valor2: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(valor2[k]);
				}

				System.out.println();
				System.out.print("Resultado: ");
				for (int k = (valorBits + valorBits - 1); k >= 0; k--) {
					System.out.print(resultado[k]);
				}
			}

			// OPERAÇÃO DE DIVISÃO
			else if (tipoDeOperacao.equals("/")) {
				int[] dividendoQ = new int[primeiroValor.length()];
				int[] divisorB = new int[segundoValor.length()];
				int[] valorQ = new int[primeiroValor.length()];
				int[] resultado = new int[valorBits];
				int[] a = new int[valorBits + 2];// REMAINDER
				int count = primeiroValor.length();
				int aux = primeiroValor.length()-1;

				for (int i = 0; i < valorBits; i++) {
					resultado[i] = 0;
					a[i] = 0;
				}

				dividendoQ = metodos.validarArray(primeiroValor, valorBits);
				divisorB = metodos.validarArray(segundoValor, valorBits);
				valorQ = metodos.validarArray(primeiroValor, valorBits);

				while (count != 0) {
					// System.out.println("a antes: ");
					// for (int k = valorBits - 1; k >= 0; k--) {
					// 	System.out.print(a[k]);
					// }
					// System.out.println();

					// System.out.println("dividendoQ antes: ");
					// for (int k = valorBits - 1; k >= 0; k--) {
					// 	System.out.print(dividendoQ[k]);
					// }
					// System.out.println();
					
					a = metodos.shiftLeftA(a, valorBits, valorQ, aux);
					dividendoQ = metodos.shiftLeftQ(dividendoQ, valorBits);
					aux--;

					// System.out.println("a depois: ");
					// for (int k = valorBits - 1; k >= 0; k--) {
					// 	System.out.print(a[k]);
					// }
					// System.out.println();

					// System.out.println("dividendoQ depois: ");
					// for (int k = valorBits - 1; k >= 0; k--) {
					// 	System.out.print(dividendoQ[k]);
					// }
					// System.out.println();

					a = metodos.subtrair(valorBits, a, divisorB);

					// System.out.println("a(subtrair): ");
					// for (int k = valorBits - 1; k >= 0; k--) {
					// 	System.out.print(a[k]);
					// }
					// System.out.println();

					if (a[valorBits-1] == 1) {
						dividendoQ[0] = 0;
						a = metodos.soma(valorBits, a, divisorB);
					} else {
						dividendoQ[0] = 1;
					}
					count--;
				}
				resultado = dividendoQ;

				// Imprime resultado
				// System.out.print("Divisor: ");
				// for (int k = valorBits - 1; k >= 0; k--) {
				// 	System.out.print(divisorB[k]);
				// }

				// System.out.println();
				// System.out.print("Dividendo: ");
				// for (int k = valorBits - 1; k >= 0; k--) {
				// 	System.out.print(dividendoQ[k]);
				// }

				System.out.print("Resultado: ");
				for (int k = valorBits - 1; k >= 0; k--) {
					System.out.print(resultado[k]);
				}
			}

			// OPERAÇÕES COM FLOATS
		} else if (operacao == 2) {
			System.out.println(
					"Insira \"+\" para somar, ou \"-\" para subtrair, ou \"*\" para multiplicar, ou \"/\" para dividir ");
			String tipoDeOperacao = input.next();
			System.out.println("Insira o sinal do primeiro número: ");
			String sinalPrimeiroValor = input.next();
			System.out.println("Insira a primeira mantissa: ");
			System.out.print("1,");
			String primeiroValor = "1," + input.next();
			System.out.println("Expoente do primeiro binário: ");
			int expoentePrimeiroValor = input.nextInt();
			System.out.println("Insira o sinal do segundo número: ");
			String sinalSegundoValor = input.next();
			System.out.println("Insira a segunda mantissa: ");
			System.out.print("1,");
			String segundoValor = "1," + input.next();
			System.out.println("Expoente do primeiro binário: ");
			int expoenteSegundoValor = input.nextInt();

			System.out.println();

			int tamanhoArray;
			char[] valor1;
			char[] valor2;
			char[] resultado;
			int expoenteResultado = 0;
			int diferencaExpoente = expoentePrimeiroValor - expoenteSegundoValor;

			// Se a diferenca de expoente for negativa
			if (diferencaExpoente < 0) {
				diferencaExpoente = diferencaExpoente * -1;
			}

			// Criando o mesmo tamanho de array
			if (primeiroValor.length() > segundoValor.length()) {
				valor1 = new char[primeiroValor.length() + 1 + diferencaExpoente];
				valor2 = new char[primeiroValor.length() + 1 + diferencaExpoente];
				resultado = new char[primeiroValor.length() + 1 + diferencaExpoente];
				tamanhoArray = primeiroValor.length() + 1 + diferencaExpoente;
			} else if (primeiroValor.length() < segundoValor.length()) {
				valor1 = new char[segundoValor.length() + 1 + diferencaExpoente];
				valor2 = new char[segundoValor.length() + 1 + diferencaExpoente];
				resultado = new char[segundoValor.length() + 1 + diferencaExpoente];
				tamanhoArray = segundoValor.length() + 1 + diferencaExpoente;
			} else {
				valor1 = new char[segundoValor.length() + 1 + diferencaExpoente];
				valor2 = new char[segundoValor.length() + 1 + diferencaExpoente];
				resultado = new char[segundoValor.length() + 1 + diferencaExpoente];
				tamanhoArray = segundoValor.length() + 1 + diferencaExpoente;
			}

			for (int i = 0; i < tamanhoArray; i++) {
				valor1[i] = '0';
				valor2[i] = '0';
				resultado[i] = '0';
			}
			// Valor 1 recebe os valores
			for (int i = 0; i < primeiroValor.length(); i++) {
				valor1[i + 1] = primeiroValor.charAt(i);
			}

			// Valor 2 recebe os valores

			for (int i = 0; i < segundoValor.length(); i++) {
				valor2[i + 1] = segundoValor.charAt(i);
			}

			System.out.println(tamanhoArray);

			// imprimir valor
			System.out.println("Primeiro valor: ");
			for (int i = 0; i < tamanhoArray; i++) {
				System.out.print(valor1[i]);
			}
			System.out.println();
			// imprimir valor
			System.out.println("Segundo valor: ");
			for (int i = 0; i < tamanhoArray; i++) {
				System.out.print(valor2[i]);
			}
			expoenteResultado = expoentePrimeiroValor;

			if (tipoDeOperacao.equals("+")) {
				if (expoentePrimeiroValor == expoenteSegundoValor) {
					if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("+")) {
						resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);
					}
					else if (sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("+")) {
						valor1 = metodos.complemento2Float(tamanhoArray, valor1);
						resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);
					}
					else if (sinalPrimeiroValor.equals("+") && sinalSegundoValor.equals("-")) {
						valor2 = metodos.complemento2Float(tamanhoArray, valor2);
						resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);
					}
					else if (sinalPrimeiroValor.equals("-") && sinalSegundoValor.equals("-")) {
						valor1 = metodos.complemento2Float(tamanhoArray, valor1);
						valor2 = metodos.complemento2Float(tamanhoArray, valor2);
						resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);
					}
					
				} else if (expoentePrimeiroValor > expoenteSegundoValor) {
					valor2 = metodos.inverteArray(tamanhoArray, valor2);
					valor2 = metodos.deslocamentoFloat(tamanhoArray, valor2, diferencaExpoente);
					valor2 = metodos.inverteArray(tamanhoArray, valor2);

					// System.out.println();
					// System.out.println("Resultado Inverte Array e desloca virgula: ");
					// for (int i = tamanhoArray -1 ; i >= 0; i--) {
					// System.out.print(valor2[i]);
					// }
					resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);

				} else if (expoentePrimeiroValor < expoenteSegundoValor) {
					valor1 = metodos.inverteArray(tamanhoArray, valor1);
					valor1 = metodos.deslocamentoFloat(tamanhoArray, valor1, diferencaExpoente);
					valor1 = metodos.inverteArray(tamanhoArray, valor1);
					resultado = metodos.somaFloat(tamanhoArray, valor1, valor2);
				}

				System.out.println();
				System.out.println("Resultado: ");
				for (int i = 0; i < tamanhoArray; i++) {
					System.out.print(resultado[i]);
				}
				System.out.println();
				System.out.println("Expoente: ");
				System.out.print(expoenteResultado);
			} else if (tipoDeOperacao.equals("-")) {

			} else if (tipoDeOperacao.equals("*")) {

			} else if (tipoDeOperacao.equals("/")) {

			}

			// SAIR
		} else if (operacao == 3) {
			// return;
		} else {

		}

		input.close();
	}

}
