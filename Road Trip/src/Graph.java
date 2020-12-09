import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

// Graph class
public class Graph {
    Hashtable<String, List<String>> adjacencyList;
    List<Edge> edges;

    // add Vertex
    public void addVertex(String location) {
        adjacencyList.putIfAbsent(location, new ArrayList<>());
    }

    // Edge
    public class Edge {
        int minutes;
        int weight;
        String first;
        String second;

        public Edge (String first, String second, int dist, int time) {
            this.first = first;
            this.second = second;
            minutes = time;
            weight = dist;
        }
    }

    // Graph
    public Graph() {
        adjacencyList = new Hashtable<>();
        edges = new ArrayList<>();
    }

    // addEdge
    public void addEdge(String start, String end, int dist, int time) {
        addVertex(start);
        addVertex(end);
        adjacencyList.get(start).add(end);
        adjacencyList.get(end).add(start);
        edges.add(new Edge(start, end, dist, time));
    }

    @Override
    public String toString () {
        String result = "";
        for (Edge edge : edges) {
            result += edge.first + " -> " + edge.second + "\t" + edge.weight + "\n";
        }
        return result;
    }
}