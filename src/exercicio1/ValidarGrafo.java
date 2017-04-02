package exercicio1;

import java.util.ArrayList;

/**
 * 
 * @author Gabriel Bernardi e Matheus Waltrich
 * 
 */
public class ValidarGrafo {
    private int[][] grafo;
    private boolean dirigido;
    
    public ValidarGrafo() {} 
    
    public String tipoDoGrafo(int[][] grafo){
        this.grafo = grafo;
        StringBuilder retorno = new StringBuilder();
        
        retorno.append("Tipo do Grafo: ")
        .append(this.isDirigido())
        .append(", ")
        .append(this.isSimples())
        .append(", ")
        .append(this.isRegular())
        .append(", ")
        .append(this.isCompleto())
        .append(", ")
        .append(this.isNulo())
        .append(" e ")
        .append(this.isBipartido())
        .append(".");
        return retorno.toString();
    }
    
    private String isDirigido() {
        String linha = "";
        String coluna = "";
        for (int j = 0; j < grafo.length; j++) {
            for (int i = 0; i < grafo[j].length; i++) {
                linha += grafo[j][i];
            }
            for (int i = 0; i < grafo.length; i++) {
                coluna += grafo[i][j];
            }
        }
        if (linha.equals(coluna)) {
            this.dirigido = false;
            return "Não dirigido";
        } else {
            this.dirigido = true;
            return "Dirigido";
        }
    }
    
    private String isSimples() {
        for (int i = 0; i < grafo.length; i++) {
            if (grafo[i][i] != 0) {
                return "multigrafo";
            }
        }
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (grafo[i][j] > 1) {
                    return "multigrafo";
                }
            }
        }
        return "simples";
    }
    
    private String isRegular() {
        int valorAnterior = 0;
        int valorAtual = 0;
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                valorAtual += grafo[i][j];
            }
            if (i == 0) {
                valorAnterior = valorAtual;
            }
            if (valorAnterior != valorAtual) {
                return "não regular";
            } 
            valorAtual = 0;
        }
        return "regular";
    }
    
    private String isCompleto(){
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (i == j) {
                    if (grafo[i][j] != 0) {
                        return "não completo";
                    }
                } else {
                    if (grafo[i][j] != 1) {
                        return "não completo";
                    }
                }
            }
        }
        return "completo";
    }
    
    private String isNulo() {
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (grafo[i][j] != 0) {
                    return "não nulo";
                }
            }
        }
        return "nulo";
    }
    
    private String isBipartido() {
        ArrayList<Integer> grupoA = new ArrayList<Integer>();
        ArrayList<Integer> grupoB = new ArrayList<Integer>();
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                if (i == j && grafo[i][j] > 0) {
                    return "não bipartido";
                }
                if (grafo[i][j] > 0) {
                    if (!adicionaIndice(grupoA, grupoB, i)) {
                        return "não bipartido";
                    }
                    if (!adicionaIndice(grupoB, grupoA, j)) {
                        return "não bipartido";
                    }
                }
            }
        }
        return "bipartido";
    } 
    
    private boolean adicionaIndice(ArrayList<Integer> grupo1, ArrayList<Integer> grupo2, int indice) {
        boolean inseridoSucesso = true;
        if (grupo1.size() == 0) {
            grupo1.add(indice);
        } else {
            for (Integer indiceLista : grupo1) {
                if (grafo[indiceLista][indice] > 0) {
                    inseridoSucesso = false;
                    break;
                }          
            }
            if (!grupo1.contains(indice) && inseridoSucesso) {
                grupo1.add(indice);
            }
        }
        if (!inseridoSucesso) {
            if (grupo2.size() == 0) {
                grupo2.add(indice);
            } else {
                for (Integer indiceLista : grupo2) {
                    if (grafo[indiceLista][indice] > 0) {
                        return false;
                    }
                }
                if (!grupo2.contains(indice)) {
                    grupo2.add(indice);
                }
            }
        }
        return true;
    }
    
    public String arestasDoGrafo(int[][] grafo){
        this.grafo = grafo;
        int qtdArestas = 0;
        String conjunto = "";
        
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                qtdArestas += grafo[i][j];
                if (grafo[i][j] > 0) {
                    conjunto += "(" + i + ", " + j + "), ";
                }
            }
        }
        if (!"".equals(conjunto)) {
            conjunto = conjunto.substring(0, conjunto.length()-2);
        }
        if (dirigido){
            return "Quantidade de arestas: " + qtdArestas + "\nConjunto de arestas: E = {" + conjunto + "}";
        }
        return "Quantidade de arestas: " + (qtdArestas / 2) + "\nConjunto de arestas: E = {" + conjunto + "}";
    }
    
    public String grausDoVertice(int[][] grafo){
        this.grafo = grafo;
        String grauVertice = "";
        ArrayList<Integer> lstGraus = new ArrayList<Integer>();
        int qtdGraus = 0;
        
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                qtdGraus += grafo[i][j];
            }
            grauVertice += "V" + i + ": " + qtdGraus + ", ";
            lstGraus.add(qtdGraus);
            qtdGraus = 0;
        }
        if (!"".equals(grauVertice)) {
            grauVertice = grauVertice.substring(0, grauVertice.length()-2);
        }
        grauVertice += "\nSequência dos graus: ";
        lstGraus.sort(null);
        
        for (Integer lstGrau : lstGraus) {
            grauVertice += lstGrau + ", ";
        }
        if (!"".equals(grauVertice)) {
            grauVertice = grauVertice.substring(0, grauVertice.length()-2);
        }
        
        return "Grau de cada vértice: " + grauVertice;
    }
}
