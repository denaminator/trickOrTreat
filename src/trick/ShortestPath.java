package trick;

import java.util.*;
import java.util.Map.Entry;


public class ShortestPath {

    private int[] distance;
    private House[] pred;

    public void setLength(int length) { 
        this.distance = new int[length];
        this.pred = new House[length];
    }

    public void dijkstra (Graph graph, HashMap<String, Integer> element, House source) {

        /*
         * Structures to use during search
         * 
         * fringe: list of Houses yet to be checked
         * done[]: checked Houses (in order)
         * distance[]: distance between source and other Houses
         * pred[]: links between Houses, similar to quick-union find()
         */
        ArrayList<House> fringe = new ArrayList<House>();
        ArrayList<House> done = new ArrayList<House>();

        // Set distances for every house compared to the source (source = 0, all else = "infinity")
        for (int i = 0; i < graph.houses.length; i++) {
            if (!graph.houses[i].getName().equals(source.getName())) distance[i] = Integer.MAX_VALUE;
            else distance[i] = 0;
            pred[i] = new House(null, null);
        }

        // Add source to fringe and check all houses through neighbors
        fringe.add(source);
        while (!fringe.isEmpty()) {

            House ptr = fringe.get(0);          // Front of ArrayList
            int remove = 0;                           // Element of ArrayList to remove, update dynamically
            int min = Integer.MAX_VALUE;

            // Finds min distance vertex
            int j = 0;
            while (j < fringe.size()) {
                // Update min and index of House to remove
                if (distance[element.get(ptr.getName())] < min) {
                    min = distance[element.get(ptr.getName())];
                    remove = element.get(ptr.getName());
                }
                j++;
                // Conditional to avoid going out of bounds
                if (j < fringe.size()) ptr = fringe.get(j);
            }

            // Add min distance vertex to done
            done.add(graph.houses[remove]);

            // Check neighbors of min distance vertex
            for (House w: graph.adj(graph.houses[remove])) {

                // Find weight of neighbor by checking graph
                int weight = 0;
                for (House curr = graph.houses[remove]; curr != null; curr = curr.getNext()) {
                    if (curr.getName().equals(w.getName())) weight = curr.getWeight();
                }

                // If distance of the neighbor hasn't been set yet
                if (distance[element.get(w.getName())] == Integer.MAX_VALUE) {
                    // Distance = distance of pred (min distance vertex) + weight of edge between neighbor and pred
                    distance[element.get(w.getName())] = distance[remove] + weight;
                    pred[element.get(w.getName())] = graph.houses[remove];
                    fringe.add(w);      // Adds neighbor if not in fringe or done
                }
                // If distance of neighbor has been set and can be replaced with more efficient path
                else if (distance[element.get(w.getName())] > (distance[remove] + weight)) {
                    distance[element.get(w.getName())] = distance[remove] + weight;
                    pred[element.get(w.getName())] = graph.houses[remove];
                }
            }

            // Remove from fringe
            for (int i = 0; i < fringe.size(); i++) {
                if (graph.houses[remove].getName().equals(fringe.get(i).getName())) {
                    remove = i;
                    break;
                }
            }
            fringe.remove(remove);
        }
    }

    public House[] returnOrder() { return pred; }
    public int[] returnDistance() { return distance; }

    public static void main(String[] args) {
        if (args.length < 3) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.ShortestPath input1.in h1 shortestpaths1.out");
            return;
        }

        // Instantiates graph and adds adjacencies
        NeighborhoodMap map = new NeighborhoodMap();
        House[] houses = map.setHouses(args[0]);
        Graph graph = new Graph(houses);
        map.addAdjacencies(args[0], graph);

        // Make HashMap to contain index number of House in houses[]
        ElementHash hash = new ElementHash();
        HashMap<String, Integer> element = hash.hashMap(graph);

        // Instantiates self object
        ShortestPath shortest = new ShortestPath();
        shortest.setLength(graph.houses.length);

        // Implements Dijkstra's algo and returns pred[] for adjacencies in the path order
        shortest.dijkstra(graph, element, houses[element.get(args[1])]);
        House[] pred = shortest.returnOrder();

        // Prints each house and its predecessor
        StdOut.setFile(args[2]);
        for (int i = 0; i < pred.length; i++) {
            StdOut.println(houses[i].getName() + " " + pred[i].getName());
        }
        
    }
}
