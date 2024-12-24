package trick;
import java.util.*;

public class NeighborhoodMap {

    // Creates House[] of houses needed to build Graph
    public House[] setHouses (String inputFile) {
        StdIn.setFile(inputFile);

        // Finds number of houses from input file and initializes House[] of said size
        int houseNum = Integer.parseInt(StdIn.readLine());
        House[] houses = new House[houseNum];

        // Adds House objects to Graph
        for (int i = 0; i < houseNum; i++) {
            // Creates vertices, so weight == 0
            House newHouse = new House(StdIn.readString(), 0, null);
            newHouse.setCandyNum(StdIn.readInt());
            // Adds House[]
            houses[i] = newHouse;

            // Adds Candies to ArrayList
            for (int j = 0; j < newHouse.getCandyNum(); j++) {
                String name = StdIn.readString();
                int count = StdIn.readInt();

                Candy newCandy = new Candy(name, count);
                // Adds to ArrayList
                newHouse.addCandy(newCandy);
            }
        }
        return houses;
    }

    // Adds adjacencies 
    public void addAdjacencies (String inputFile, Graph graph) {

        // Finds number of edges from input file
        int edgeNum = StdIn.readInt();
        // Adds adjacencies for each listed edge
        for (int i = 0; i < edgeNum; i++) {
            
            /*
                * Vertices: vertex1 = house 1
                *           vertex2 = house 2
                */
            String v1Name = StdIn.readString();
            String v2Name = StdIn.readString();
            House vertex1 = new House(v1Name, null);
            House vertex2 = new House(v2Name, null);

            // Searches for vertex1 in array and sets object val
            int j = 0;
            while (!v1Name.equals(graph.houses[j].getName())) j++;
            vertex1 = graph.houses[j];

            // Searches for vertex2 in array and sets object val
            j = 0;
            while (!v2Name.equals(graph.houses[j].getName())) j++;
            vertex2 = graph.houses[j];

            /*
                * Edges: addEdge1 = house 1 --> *house 2*
                *        addEdge2 = house 2 --> *house 1*
                */
            House addEdge1 = new House(v2Name, null);
            House addEdge2 = new House(v1Name, null);

            // Creates UNDIRECTED edge by calling directed twice (bruh)
            int weight = StdIn.readInt();
            graph.addEdge(vertex1, addEdge1, weight);
            graph.addEdge(vertex2, addEdge2, weight);
        }
    }

    // Print statements
    public static void main(String[] args) {
        // Invalid terminal entry
        if (args.length < 2) {
            StdOut.println(
                    "Too few arguments. Did you put in command line arguments? If using the debugger, add args to launch.json.");
            StdOut.println(
                    "Execute: java -cp bin trick.NeighborhoodMap <neighborhoodmap INput file> <neighborhoodmap OUTput file>");
            return;
        }

        // Instantiates self object to call non-static methods
        NeighborhoodMap neighborhoodMap = new NeighborhoodMap();
        House[] houses = neighborhoodMap.setHouses(args[0]);

        // Prints houses and their candies
        StdOut.setFile(args[1]);
        for (int i = 0; i < houses.length; i++) {
            StdOut.print("\n" + houses[i].getName() + " ");
            for (int j = 0; j < houses[i].getCandyNum(); j++) {
                StdOut.print(houses[i].getCandies().get(j).getName() + " " + houses[i].getCandies().get(j).getCount() + " ");
            }
        }

        // Instantiates graph and adds adjacencies
        Graph graph = new Graph(houses);
        neighborhoodMap.addAdjacencies(args[0], graph);

        // Prints adjacencies of each house vertex
        for (int i = 0; i < houses.length; i++) {
            StdOut.print("\n" + houses[i].getName() + " ");

            ArrayList<House> adj = graph.adj(houses[i]);
            // Prints candies of each house vertex
            for (int j = 0; j < adj.size(); j++) {
                StdOut.print(adj.get(j).getName() + " " + adj.get(j).getWeight() + " ");
            }
        }

    }
}
