package exercicio3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel da Silva Bernardi e Matheus Waltrich da Silva
 */
public class Main {
    private static int numeroTeste = 0;
    private static int[][] matrizAdjacencia;
    private static List<Integer> verticesLidos = new ArrayList<>();
    private static List<String> resultados = new ArrayList<>();
    private static Queue<Integer> listaAdjacentes = new LinkedList<>();
    
    public static void main(String[] args) {
        informarNumeroTestes();
        informarGrafo();
        resultadoCiclos();
    }
    
    private static void informarNumeroTestes() {
        numeroTeste = Integer.parseInt(JOptionPane.showInputDialog("Informe o Número de Testes:"));
    }

    private static void informarGrafo() {
        for (int i = 1; i <= numeroTeste; i++) {
            matrizAdjacencia = null;
            String valorTeste = JOptionPane.showInputDialog("Informe o " + i + "º Caso de Teste: ");
            Integer numVertices = Integer.parseInt(valorTeste.split(" ")[0]);
            matrizAdjacencia = new int[numVertices][numVertices];
            informarLigacoes(valorTeste);
        }
    }

    private static void informarLigacoes(String valorTeste) {
        Integer qtdArestas = Integer.parseInt(valorTeste.split(" ")[1]);
        for (int i = 1; i <= qtdArestas; i++) {
            String ligacao = JOptionPane.showInputDialog("Informe a " + i + "ª ligação: ");
            popularMatrizAdjacencia(ligacao);
        }
        verticesLidos = new ArrayList<>();
        listaAdjacentes = new LinkedList<>();
        resultados.add(possuiCiclo(0));
    }
    
    private static void popularMatrizAdjacencia(String ligacao) {
        String[] v = ligacao.split(" ");
        
        int primeroVerticeInformado = Integer.parseInt(v[0]);
        int segundoVerticeInformado = Integer.parseInt(v[1]);
        
        matrizAdjacencia[primeroVerticeInformado-1][segundoVerticeInformado-1] = 1;
    }
    
    
    private static String possuiCiclo(int vertice) {
        String resultado  = "Não";
        if(!verticesLidos.contains(vertice)){
            verticesLidos.add(vertice);
        }
        for(int i = 0; i < matrizAdjacencia.length; i++){
            if(matrizAdjacencia[vertice][i] > 0){
                if(!verticesLidos.contains(i)){
                    listaAdjacentes.add(i);
                }
                if(verticesLidos.contains(i)){
                    resultado = "Sim";
                }
            }
        }
        
        if (!listaAdjacentes.isEmpty()){
            int pro = listaAdjacentes.poll();
            if(!verticesLidos.contains(pro)){
              resultado = possuiCiclo(pro);    
            }
        }
        return resultado;
    }

    private static void resultadoCiclos() {
        String r = "";
        int count = 1;
        for (String resultado : resultados) {
            r += "Caso de Teste " + count + ": " + resultado + "\n";
            count++;
        }
        JOptionPane.showMessageDialog(null, r);
    }
}
