package trick;

import java.util.*;


public class PathToMostCandy {

    /*
     * Using a queue, traverse the graph using BFS to find the most efficient path
     * @param Graph graph, House source, House target
     */
    public ArrayList<House> bfs (Graph graph, HashMap<String, Integer> element, House source, House target) {
        
        // Arrays to keep track during BFS
        boolean[] marked = new boolean[graph.houses.length];
        int[] edgeTo = new int[graph.houses.length];
        ArrayList<House> order = new ArrayList<House>();

        Queue<House> check = new Queue<House>();
        check.enqueue(source);

        // Populate the order tracking array
        while (!check.isEmpty()) {
            House v = check.dequeue();

            marked[element.get(v.getName())] = true;

            // Source points to itself
            if (graph.houses[element.get(v.getName())].getName().equals(source.getName())) edgeTo[element.get(v.getName())] = element.get(v.getName());

            for (House w: graph.adj(v)) {
                if (!marked[element.get(w.getName())]) {
                    check.enqueue(w);
                    marked[element.get(w.getName())] = true;
                    edgeTo[element.get(w.getName())] = element.get(v.getName());
                }
            }
        }

        /*
         * Searches for shortest path similar to quick-union's find()
         * Loop stops when reaching source in edgeTo array
         * Adds each house visited to order array
         */
        int i = element.get(target.getName());
        while (edgeTo[i] != i) {
            order.add(graph.houses[i]);
            i = edgeTo[i];
        }
        // Add source
        order.add(graph.houses[i]);

        // Reverse ArrayList order to get path source --> target, instead of target --> source
        Collections.reverse(order);

        return order;
    }

    public static void main(String[] args) {

        // Invalid terminal entry
        if (args.length < 4) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "execute: java -cp bin trick.PathToMostCandy input1.in h1 KitKat mostcandy1.out");
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

        // Instantiate DFS route and arrays used in DFS
        FindTreatsRoute route = new FindTreatsRoute();
        route.setLength(houses.length);
        route.dfs(graph, element, houses[element.get(args[1])]);

        // Initialize self object to call non-static methods
        FindHouseWithMostCandy mostCandyHouse = new FindHouseWithMostCandy();
        House target = mostCandyHouse.mostCandy(graph, route, args[2]);

        PathToMostCandy mostCandyRoute = new PathToMostCandy();
        ArrayList<House> order = mostCandyRoute.bfs(graph, element, houses[element.get(args[1])], target);
        
        // Print order of shortest path using ArrayList order
        StdOut.setFile(args[3]);
        for (int i = 0; i < order.size(); i++) {
            StdOut.print(order.get(i).getName() + " ");
        }
    }
}
