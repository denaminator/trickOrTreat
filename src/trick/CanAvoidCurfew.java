package trick;

import java.util.HashMap;

public class CanAvoidCurfew {

    public boolean avoidCurfew (int realTime, int total) {
        if (realTime > total) return false;
        return true;
    }
    public static void main(String[] args) {

        // Invalid terminal entry
        if (args.length < 5) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.CanAvoidCurfew input1.in h1 h8 100 shortestpaths1.out");
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

        // Implements Dijkstra's algo
        ShortestPath shortest = new ShortestPath();
        shortest.setLength(graph.houses.length);
        shortest.dijkstra(graph, element, houses[element.get(args[1])]);

        // Returns distance[] for total distances from source and finds optimal distance from source of target house
        int[] distance = shortest.returnDistance();
        int realTime = distance[element.get(args[2])];

        // Instantiates self object to determine if curfew can be met, then prints result
        CanAvoidCurfew curfew = new CanAvoidCurfew();
        StdOut.setFile(args[4]);
        StdOut.println(curfew.avoidCurfew(realTime, Integer.parseInt(args[3])) + " " + realTime);        
    }
}
