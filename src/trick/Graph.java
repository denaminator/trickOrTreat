package trick;
import java.util.*;

public class Graph {
    
    // Vars
    public House[] houses;

    // Constructor to initialize Graph from input of House[]
    public Graph (House[] input) {
        this.houses = input;
    }

    /*
     * Add an edge between two houses with a given weight
     * @param House "from", House "to", int weight (of edge)
     */
    public void addEdge(House u, House v, int weight) {
        House vertex = null;

        // If trying to add an edge to itself, return
        if (u.getName().equals(v.getName())) return;

        // Search for vertex in adjacency array
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getName().equals(u.getName())) vertex = houses[i];
        }

        while (vertex.getNext() != null) {
            // If house to add is present, return
            if (vertex.getName().equals(v.getName())) return;
            vertex = vertex.getNext();
        }

        // Checks if last element equals v
        if (vertex.getName().equals(v.getName())) return;

        // Adds v to list if doesn't already exist in list
        v.setWeight(weight);
        vertex.setNext(v);
    }

    
    /*
     * Display the list of adjacencies for a House
     * @param House target
     */
    public ArrayList<House> adj(House v) {
        ArrayList<House> adj = new ArrayList<House>();
        House target = null;

        // Search for vertex v in houses
        for (int i = 0; i < houses.length; i++) {
            if (houses[i].getName().equals(v.getName())) target = houses[i];
        }

        // In case House v not found (avoid null ptr exception)
        if (target == null) return null;
        
        // Hop to first edge in list
        target = target.getNext();
        
        // Add all adjacent houses to adj
        while (target != null) {
            adj.add(target);
            target = target.getNext();
        }

        return adj;
    }

}
