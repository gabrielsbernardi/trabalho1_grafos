/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel da Silva Bernardi e Matheus Waltrich da Silva 
 * 
 */
public class Main {
    private static List<String> vertices;
    private static Queue<Integer> listaAdjacentes = new LinkedList<>();
    private static List<Integer> verticesLidos = new ArrayList<>();
    private static int qtdVertices;
    private static int qtdArestas;
    private static int[][] matrizAdjacencia;
    
    public static void main(String[] args) {
        informarQtdVerticesArestas();
        informarArestas();
    }

    private static void informarQtdVerticesArestas() {
        qtdVertices = Integer.parseInt(JOptionPane.showInputDialog("Informe o número VÉRTICES:"));
        qtdArestas = Integer.parseInt(JOptionPane.showInputDialog("Informe o número ARESTAS:"));
        matrizAdjacencia = new int[qtdVertices][qtdVertices];
        montarConjuntoVertices();
    }

    private static void montarConjuntoVertices() {
        vertices = new ArrayList<>(qtdVertices);
        for (int i = 0; i < qtdVertices; i++) {
            vertices.add("V" + i);
        }
    }

    private static void informarArestas() {
        StringBuilder verticesDisponiveis = new StringBuilder();
        int count = 0;
        verticesDisponiveis.append("Vértices disponíveis: ");
        for (String vertice : vertices) {
            verticesDisponiveis.append(vertice).append(" ");
            count++;
        }
        System.out.println(verticesDisponiveis.toString() + "\nVértice de ENTRADA = V0\nVértice de SAÍDA = V" + (count-1));
        System.out.println("Padrão de exemplo de inserção de ligações: V0 V1\n");
        
        System.out.println("Informe as ligações dos vértices.");
        for (int i = 0; i < qtdArestas; i++) {
            boolean arestaValida = false;
            while (!arestaValida) {
                String verticeInformados = JOptionPane.showInputDialog("Ligação " + i + ": ");
                arestaValida = validarAresta(verticeInformados);
                if (arestaValida) {
                    popularMatrizAdjacencia(verticeInformados);
                }
            }
        }
        
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println(buscaMenorCaminho(0, 0));
    }
    
    private static boolean validarAresta(String ligacao) {
        String[] v = ligacao.split(" ");
        if (v.length != 2) {
            System.out.println("Conjunto de arestas inválidas!");
            return false;
        }
        if (!vertices.contains(v[0]) || !vertices.contains(v[1])){
            System.out.println("Conjunto de arestas inválidas!");
            return false;
        }
        return true;
    }

    private static void popularMatrizAdjacencia(String ligacao) {
        String[] v = ligacao.split(" ");
        
        int primeroVerticeInformado = Integer.parseInt(v[0].replace("V", ""));
        int segundoVerticeInformado = Integer.parseInt(v[1].replace("V", ""));
        
        matrizAdjacencia[primeroVerticeInformado][segundoVerticeInformado] = 1;
        matrizAdjacencia[segundoVerticeInformado][primeroVerticeInformado] = 1;
    }

    private static int buscaMenorCaminho(int vertice, int distancia) {
        if(!verticesLidos.contains(vertice)){
            verticesLidos.add(vertice);
        }
        for(int i = 0; i < matrizAdjacencia.length; i++){
            if(matrizAdjacencia[vertice][i] > 0){
                if(!verticesLidos.contains(i)){
                    listaAdjacentes.add(i);
                }
            }
        }
        
        if (!listaAdjacentes.isEmpty()){
            int pro = listaAdjacentes.poll();
            if(!verticesLidos.contains(pro)){
                distancia++;
                return distancia = buscaMenorCaminho(pro, distancia);    
            }
        }
        return distancia;
    }
}
