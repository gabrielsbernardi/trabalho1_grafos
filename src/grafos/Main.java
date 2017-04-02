package grafos;

import java.util.Scanner;

/**
 * 
 * @author Gabriel Bernardi e Matheus Waltrich
 * 
 */
public class Main {
    private static int[][] matrizAdjacencia;
    private static int maxLinhas = 0;
    
    public static void main(String[] args) {
        informarArestasPorLinha();
        ValidarGrafo grafo = new ValidarGrafo();
        System.out.println(grafo.tipoDoGrafo(matrizAdjacencia) 
                            + "\n" + grafo.arestasDoGrafo(matrizAdjacencia)
                            + "\n" + grafo.grausDoVertice(matrizAdjacencia));
    }

    private static void informarArestasPorLinha() {
        String valorLinha = "";
        int numLinha = 0;
        Scanner ler = new Scanner(System.in);
        System.out.println("Separe os valores da linha por espaço. Pressione \"Enter\" para cada nova linha da matriz.");
        do {
            System.out.print("Linha " + numLinha + ": ");
            valorLinha = ler.nextLine();
            if ("".equals(valorLinha.trim())) {
                System.err.println("Valor inválido!\n");
            } else {
                processarLinha(valorLinha, numLinha);
                numLinha++;
            }
        } while (maxLinhas > numLinha);
    }
    
    private static void processarLinha(String linha, int numLinha){
        String[] valoresLinha = linha.split(" ");
        if (numLinha == 0) {
            matrizAdjacencia = new int[valoresLinha.length][valoresLinha.length];
            maxLinhas = valoresLinha.length;
        } 
        for (int i = 0; i < valoresLinha.length; i++) {
            if (isNumber(valoresLinha[i])) {
                matrizAdjacencia[numLinha][i] = Integer.parseInt(valoresLinha[i]);
            } else {
                System.err.println("Valor inválido na linha " + numLinha);
                System.exit(0);
            }
        }
    }
    
    private static boolean isNumber(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
        
}
