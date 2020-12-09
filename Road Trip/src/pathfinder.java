import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

// pathfinder extends Graph
public class pathfinder extends Graph{

    HashSet<String> cities;
    // All cities have to go through
    Hashtable<String, Boolean> visited;
    // <The location, pre Location> then "Path"
    Hashtable<String, Integer> distance;
    // <The location, Bool Status T-F, then "Known"
    Hashtable<String, String> previous;
    // <The location, The distance from the begin>, then "Cost"
    Hashtable<String, String> allAttractions;
    // <K,V> == <Attraction, Location>
    Graph graph;
    // The edges - vertices - Adjacency List
    int milesTravelled;
    // All traveled ways
    int timeTaken;
    // The time took

    // print_function for output
    public void print_function(List<String> path) {
        System.out.println(path.toString());
        System.out.println("The total distance travelled : " + milesTravelled + " miles");
        System.out.println("The time took for the determined trip : " + timeTaken + " minutes");
    }

    // edge_time_status
    public int edge_time_status(String v1, String v2) {
        int theTime = 0;
        for (Edge edge : graph.edges) {
            if (edge.first.equals(v1) && edge.second.equals(v2)) {
                return edge.minutes;
            }
            else if (edge.first.equals(v2) && edge.second.equals(v1)) {
                return edge.minutes;
            }
        }
        return theTime;
    }

    // the_least_cost_for_the_unknown_vertex
    private String the_least_cost_for_the_unknown_vertex() {
        String vertex = "";
        int mins = Integer.MAX_VALUE;

        for (String theCity : cities) {
            if (!visited.get(theCity) && distance.get(theCity) <= mins) {
                mins = distance.get(theCity);
                vertex = theCity;
            }
        }
        return vertex;
    }

    // the_known_status
    private void the_known_status(String v) {
        if (v != null) {
            visited.put(v, true);
        }
    }

    // the_edge_weight_status
    public int the_edge_weight_status(String v1, String v2) {
        int theWeight = 0;
        for (Edge edge : graph.edges) {
            if (edge.first.equals(v1) && edge.second.equals(v2)) {
                return edge.weight;
            }
            else if (edge.first.equals(v2) && edge.second.equals(v1)) {
                return edge.weight;
            }
        }
        return theWeight;
    }

    // pathfinder
    public pathfinder() {
        allAttractions = new Hashtable<>(143);
        visited = new Hashtable<>();
        previous = new Hashtable<>();
        distance = new Hashtable<>();
        graph = new Graph();
        cities = new HashSet<>();
        milesTravelled = 0;
        timeTaken = 0;
    }

    // route list
    List<String> route(String start_location, String end_location, List<String> attractions) {
        ArrayList<String> path = new ArrayList<>();

        if (start_location.isBlank() || end_location.isBlank()) {
            System.out.println("Begin - Blank Location");
            return path;
        }

        graph.addEdge(start_location, start_location, 0, 0);
        for (String city : cities) {
            if (city != null) {
                visited.put(city, false);
                distance.put(city, Integer.MAX_VALUE);
            }
        }

        distance.put(start_location, 0);
        for (String city : cities) {
            while (!visited.get(city)) {
                String vertex = the_least_cost_for_the_unknown_vertex();
                the_known_status(vertex);
                for (String v : graph.adjacencyList.get(vertex)) {
                    int weight = the_edge_weight_status(vertex, v);
                    if (distance.get(v) > distance.get(vertex) + weight && !v.equals(vertex)) { //Vertex's cost greater, update
                        distance.put(v, distance.get(vertex) + weight);
                        previous.put(v, vertex);
                    }
                }
            }
        }

        ArrayList<Integer> attractionRank = new ArrayList<>();
        ArrayList<String> attractionsRanked = new ArrayList<>();
        Hashtable<Integer, String> attractionConverter = new Hashtable<>();

        for (String attraction : attractions) {
            attractionRank.add(distance.get(allAttractions.get(attraction)));
            attractionConverter.put(distance.get(allAttractions.get(attraction)), attraction);
        }
        Collections.sort(attractionRank);

        for (int rank : attractionRank) {
            attractionsRanked.add(allAttractions.get(attractionConverter.get(rank)));
        }

        attractionsRanked.add(0, start_location);

        if (attractionsRanked.contains(end_location)) {
            attractionsRanked.remove(end_location);
            attractionsRanked.add(end_location);
        }

        Stack st = new Stack();

        for (int i = 0; i < attractionsRanked.size()-1; i++) {
            String current = attractionsRanked.get(i);
            String nextVertex = attractionsRanked.get(i + 1);
            String nextVertexTemp = attractionsRanked.get(i + 1);

            st.add(nextVertex);
            while (!current.equals(nextVertex)) {
                String prevCity = previous.get(nextVertex);

                milesTravelled += the_edge_weight_status(nextVertex, prevCity);
                timeTaken += edge_time_status(nextVertex, prevCity);
                st.add(prevCity);
                nextVertex = prevCity;
            }

            while (!st.isEmpty()) {
                path.add((String) st.pop());
            }

            visited = new Hashtable<>();
            previous = new Hashtable<>();
            distance = new Hashtable<>();

            for (String city : cities) {
                if (city != null) {
                    visited.put(city, false);
                    distance.put(city, Integer.MAX_VALUE);
                }
            }

            distance.put(nextVertexTemp, 0);
            for (String city : cities) {
                while (!visited.get(city)) {
                    String vertex = the_least_cost_for_the_unknown_vertex();
                    the_known_status(vertex);
                    for (String v : graph.adjacencyList.get(vertex)) {
                        int weight = the_edge_weight_status(vertex, v);
                        if (distance.get(v) > distance.get(vertex) + weight && !v.equals(vertex)) {
                            distance.put(v, distance.get(vertex) + weight);
                            previous.put(v, vertex);
                        }
                    }
                }
            }
        }
        return path;
    }

    // main
    public static void main(String args[]) throws FileNotFoundException {
        pathfinder core = new pathfinder();

        String fileNameForAttactions = "attractions.csv";
        String fileNameForRoads = "roads.csv";
        File[] theFiles = new File[]{new File(fileNameForRoads), new File(fileNameForAttactions)};

        int fileIndexCount = 0;
        for (File f : theFiles) {
            Scanner scan = new Scanner(f);
            if (fileIndexCount == 0) {
                scan.useDelimiter("\n");
                while (scan.hasNextLine() && scan.hasNext()) {
                    String line = scan.next();
                    if (line.strip().length() == 0) { //Extra line at end is empty
                        break;
                    }
                    String[] roadData = line.split(",");
                    String start = roadData[0].replace("\n", "");
                    String end = roadData[1];
                    int miles = Integer.parseInt(roadData[2]);
                    if (roadData[3].equals("10a")) {
                        roadData[3] = "101";
                    }
                    int minutes = Integer.parseInt(roadData[3]);
                    if (start != null && end != null) {
                        core.graph.addEdge(start, end, miles, minutes);
                        core.cities.add(start);
                        core.cities.add(end);
                    }
                }
                fileIndexCount++;
            }
            else {
                scan.useDelimiter("\n");
                int lineNumber = 0;
                while (scan.hasNextLine() && scan.hasNext()) {
                    String line = scan.next();
                    if (lineNumber == 0) {  //Skip the 0th line of Attraction Location
                        lineNumber++;
                    }
                    else {
                        String[] attractionData = line.split(",");
                        String interest = attractionData[0];
                        String location = attractionData[1];
                        core.allAttractions.put(interest, location);
                    }
                }
            }
        }

        // -----------------------------------------------------------------------------------
        // -----------------------------------RUNNING-----------------------------------------
        // -----------------------------------------------------------------------------------

        System.out.println("--------------Running--------------");
        List<String> attractions = new ArrayList<>();
        attractions.add("Cape May-Lewes Ferry");
        attractions.add("Iowa State Fair");
        attractions.add("Grand Canyon");
        attractions.add("Dollywood");
        attractions.add("Biltmore");
        attractions.add("Portland City Tour");
        //System.out.println("Locations: \"Cape May-Lewes Ferry\"  \"Iowa State Fair\"  \"Grand Canyon\" \"Dollywood\"  \"Biltmore\" \"Portland City Tour\"");
        List<String> path = core.route("Tucson AZ", "San Francisco CA", attractions);
        core.print_function(path);
        System.out.println("--------------Running---------------");

        // -----------------------------------------------------------------------------------
        // -----------------------------------RUNNING-----------------------------------------
        // -----------------------------------------------------------------------------------

    }

}
