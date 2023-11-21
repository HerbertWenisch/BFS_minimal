/**
 * @author (Herbert Wenisch)
 * @version (Breitensuche)
 * symmetrischer und gewichteter Graph
 * Minimalversion ohne Luxus
 * keine Graphikausgabe, keine Ausgabe der Adjazenzmatrix
 */

import java.util.ArrayList;

public class Graph{
    private final int V_MAX;
    private int V = 0;  // augenblickliche Knotenanzahl
    private String[] ids;  // BezeichnerFeld für die Knoten 
    private int[][] adj; // Adjazenzmatrix;  Buch: matrix
        
    // erzeugt einen leeren Graf:
    public Graph(int V_MAX){
        this.V_MAX = V_MAX;
        ids = new String [V_MAX];
        adj = new int [V_MAX][V_MAX];
    }
    
    public void addNode(String id){
        if(V == V_MAX) return;
        ids[V] = id;
        V++;
    }

     
    // Fügt eine Kante  v ---> w vom Gewicht weight ein
    public void addEdge(int v, int w, int weight){
      if(v >= V ||  w >= V) return;
      if(v < 0 ||  w < 0 ) return;
      adj[v][w] = weight;
      adj[w][v] = weight;
    }
    
    public void addEdge(String idv, String idw, int weight){
      addEdge(indexOf(idv), indexOf(idw), weight);
    }
    
    // für ungewichtete Graphen:
    public void addEdge(String idv, String idw){
      addEdge(indexOf(idv), indexOf(idw), 1);
    }
    
    // Gibt zur id den zughörigen Index zurück:
    private int indexOf(String id){
        for(int v = 0; v < V; v++)
           if(id.equals(ids[v])) return v;
        return -1; // nicht gefunden: Fehler!
    }
    
    
    // Breitensuche:
    // ----------------------------------------------------------------------------------------
    
    private ArrayList<Integer> queue = new ArrayList<>();  // Warteliste
    private boolean[] marked;
    
    // Gibt den Array marked auf dem Bildschirm aus:
    public void printMarked(){
        System.out.println("markierte Knoten:");
        if (marked == null) return;
        for(int v = 0; v < V; v++)
          System.out.println(v + " " + marked[v]);
    }
    
    // Breitensuche mit Startknoten s:
    public void bfs(int s){
        // Vorbereitungen:
        queue.clear();
        marked = new boolean[V];
         
        // Startbedingung:     
        marked[s] = true;
        queue.add(s);
        
        // Schleife:
        while(!queue.isEmpty()){
            int v = queue.remove(0); // v: aktuelle Knoten
            for(int w = 0; w < V; w++)   
               if(adj[v][w] > 0 && !marked[w]){
                   marked[w] = true;
                   queue.add(w);   
               }     
        }
        
    }
}