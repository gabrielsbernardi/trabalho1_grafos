/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Gabriel Bernardi
 */
public class Main {
    private static Scanner teclado = new Scanner(System.in);
    private static List<String> vertices;
    private static int qtdVertices;
    private static int qtdArestas;
    private static int[][] matrizAdjacencia;
    
    public static void main(String[] args) {
        informarQtdVerticesArestas();
        informarArestas();
    }

    private static void informarQtdVerticesArestas() {
        System.out.print("Informe o número VÉRTICES: ");
        qtdVertices = teclado.nextInt();
        System.out.print("Informe o número ARESTAS: ");
        qtdArestas = teclado.nextInt();
        System.out.println();
        
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
        verticesDisponiveis.append("Vértices disponíveis: ");
        for (String vertice : vertices) {
            verticesDisponiveis.append(vertice).append(" ");
        }
        System.out.println(verticesDisponiveis.toString());
        
        System.out.println("Informe as ligações dos vértices.");
        for (int i = 0; i < qtdArestas; i++) {
            boolean arestaValida = false;
            while (!arestaValida) {
                System.out.print("Ligação " + i + ": ");
                String verticeInformados = teclado.nextLine();
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
        
        matrizAdjacencia[primeroVerticeInformado][segundoVerticeInformado] += 1;
    }
}
