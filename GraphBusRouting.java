import java.util.*;

public class GraphBusRouting {
    public static final int NUM_STOPS = 33;
    static ArrayList<Edge>[] graph;
    static Scanner sc = new Scanner(System.in);

    enum BusStop {
        Ahmedabad, Amreli, Anand, Aravalli, Banaskantha, Bharuch, Bhavnagar,
        Botad, Chhota_Udaipur, Dahod, Dang, Devbhoomi_Dwarka, Gandhinagar, Gir_Somnath, Jamnagar,
        Junagadh, Kheda, Kutch, Mahisagar, Mehsana, Morbi, Narmada, Navsari, Panchmahal, Patan,
        Porbandar, Rajkot, Sabarkantha, Surat, Surendranagar, Tapi, Vadodara, Valsad
    }

    static class Edge {
        BusStop src;
        BusStop dst;
        int distance;

        private Edge(BusStop src, BusStop dst, int distance) {
            this.src = src;
            this.dst = dst;
            this.distance = distance;
        }

        private int getDistance() {
            return distance;
        }
    }

    private static void initialiseRoutes() {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        // Graph nodes and edges construction

        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Anand, 65));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Bhavnagar, 170));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Botad, 120));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Gandhinagar, 30));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Kheda, 60));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Mehsana, 75));
        graph[0].add(new Edge(BusStop.Ahmedabad, BusStop.Surendranagar, 125));

        graph[1].add(new Edge(BusStop.Amreli, BusStop.Bhavnagar, 70));
        graph[1].add(new Edge(BusStop.Amreli, BusStop.Botad, 90));
        graph[1].add(new Edge(BusStop.Amreli, BusStop.Gir_Somnath, 100));
        graph[1].add(new Edge(BusStop.Amreli, BusStop.Junagadh, 45));
        graph[1].add(new Edge(BusStop.Amreli, BusStop.Rajkot, 75));

        graph[2].add(new Edge(BusStop.Anand, BusStop.Ahmedabad, 65));
        graph[2].add(new Edge(BusStop.Anand, BusStop.Bharuch, 50));
        graph[2].add(new Edge(BusStop.Anand, BusStop.Kheda, 20));
        graph[2].add(new Edge(BusStop.Anand, BusStop.Vadodara, 45));

        graph[3].add(new Edge(BusStop.Aravalli, BusStop.Gandhinagar, 50));
        graph[3].add(new Edge(BusStop.Aravalli, BusStop.Kheda, 120));
        graph[3].add(new Edge(BusStop.Aravalli, BusStop.Mahisagar, 80));
        graph[3].add(new Edge(BusStop.Aravalli, BusStop.Sabarkantha, 50));

        graph[4].add(new Edge(BusStop.Banaskantha, BusStop.Kutch, 350));
        graph[4].add(new Edge(BusStop.Banaskantha, BusStop.Mehsana, 100));
        graph[4].add(new Edge(BusStop.Banaskantha, BusStop.Patan, 80));
        graph[4].add(new Edge(BusStop.Banaskantha, BusStop.Sabarkantha, 150));

        graph[5].add(new Edge(BusStop.Bharuch, BusStop.Anand, 50));
        graph[5].add(new Edge(BusStop.Bharuch, BusStop.Narmada, 50));
        graph[5].add(new Edge(BusStop.Bharuch, BusStop.Surat, 70));
        graph[5].add(new Edge(BusStop.Bharuch, BusStop.Vadodara, 70));

        graph[6].add(new Edge(BusStop.Bhavnagar, BusStop.Ahmedabad, 170));
        graph[6].add(new Edge(BusStop.Bhavnagar, BusStop.Amreli, 70));
        graph[6].add(new Edge(BusStop.Bhavnagar, BusStop.Botad, 80));

        graph[7].add(new Edge(BusStop.Botad, BusStop.Ahmedabad, 120));
        graph[7].add(new Edge(BusStop.Botad, BusStop.Amreli, 90));
        graph[7].add(new Edge(BusStop.Botad, BusStop.Bhavnagar, 80));
        graph[7].add(new Edge(BusStop.Botad, BusStop.Rajkot, 80));
        graph[7].add(new Edge(BusStop.Botad, BusStop.Surendranagar, 70));

        graph[8].add(new Edge(BusStop.Chhota_Udaipur, BusStop.Dahod, 70));
        graph[8].add(new Edge(BusStop.Chhota_Udaipur, BusStop.Narmada, 90));
        graph[8].add(new Edge(BusStop.Chhota_Udaipur, BusStop.Panchmahal, 80));
        graph[8].add(new Edge(BusStop.Chhota_Udaipur, BusStop.Vadodara, 100));

        graph[9].add(new Edge(BusStop.Dahod, BusStop.Chhota_Udaipur, 70));
        graph[9].add(new Edge(BusStop.Dahod, BusStop.Mahisagar, 90));
        graph[9].add(new Edge(BusStop.Dahod, BusStop.Panchmahal, 50));

        graph[10].add(new Edge(BusStop.Dang, BusStop.Navsari, 80));
        graph[10].add(new Edge(BusStop.Dang, BusStop.Tapi, 100));

        graph[11].add(new Edge(BusStop.Devbhoomi_Dwarka, BusStop.Jamnagar, 135));
        graph[11].add(new Edge(BusStop.Devbhoomi_Dwarka, BusStop.Porbandar, 100));

        graph[12].add(new Edge(BusStop.Gandhinagar, BusStop.Ahmedabad, 30));
        graph[12].add(new Edge(BusStop.Gandhinagar, BusStop.Aravalli, 50));
        graph[12].add(new Edge(BusStop.Gandhinagar, BusStop.Kheda, 30));
        graph[12].add(new Edge(BusStop.Gandhinagar, BusStop.Mehsana, 30));
        graph[12].add(new Edge(BusStop.Gandhinagar, BusStop.Sabarkantha, 30));

        graph[13].add(new Edge(BusStop.Gir_Somnath, BusStop.Amreli, 100));
        graph[13].add(new Edge(BusStop.Gir_Somnath, BusStop.Junagadh, 60));

        graph[14].add(new Edge(BusStop.Jamnagar, BusStop.Devbhoomi_Dwarka, 135));
        graph[14].add(new Edge(BusStop.Jamnagar, BusStop.Morbi, 100));
        graph[14].add(new Edge(BusStop.Jamnagar, BusStop.Porbandar, 150));
        graph[14].add(new Edge(BusStop.Jamnagar, BusStop.Rajkot, 90));

        graph[15].add(new Edge(BusStop.Junagadh, BusStop.Amreli, 45));
        graph[15].add(new Edge(BusStop.Junagadh, BusStop.Gir_Somnath, 60));
        graph[15].add(new Edge(BusStop.Junagadh, BusStop.Porbandar, 100));
        graph[15].add(new Edge(BusStop.Junagadh, BusStop.Rajkot, 100));

        graph[16].add(new Edge(BusStop.Kheda, BusStop.Ahmedabad, 60));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Anand, 20));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Aravalli, 120));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Gandhinagar, 30));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Mahisagar, 50));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Panchmahal, 70));
        graph[16].add(new Edge(BusStop.Kheda, BusStop.Vadodara, 45));

        graph[17].add(new Edge(BusStop.Kutch, BusStop.Banaskantha, 350));
        graph[17].add(new Edge(BusStop.Kutch, BusStop.Morbi, 250));
        graph[17].add(new Edge(BusStop.Kutch, BusStop.Patan, 235));
        graph[17].add(new Edge(BusStop.Kutch, BusStop.Surendranagar, 350));

        graph[18].add(new Edge(BusStop.Mahisagar, BusStop.Aravalli, 80));
        graph[18].add(new Edge(BusStop.Mahisagar, BusStop.Dahod, 90));
        graph[18].add(new Edge(BusStop.Mahisagar, BusStop.Kheda, 50));
        graph[18].add(new Edge(BusStop.Mahisagar, BusStop.Panchmahal, 100));

        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Ahmedabad, 75));
        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Banaskantha, 100));
        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Gandhinagar, 30));
        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Patan, 40));
        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Sabarkantha, 50));
        graph[19].add(new Edge(BusStop.Mehsana, BusStop.Surendranagar, 140));

        graph[20].add(new Edge(BusStop.Morbi, BusStop.Jamnagar, 100));
        graph[20].add(new Edge(BusStop.Morbi, BusStop.Kutch, 250));
        graph[20].add(new Edge(BusStop.Morbi, BusStop.Rajkot, 70));
        graph[20].add(new Edge(BusStop.Morbi, BusStop.Surendranagar, 100));

        graph[21].add(new Edge(BusStop.Narmada, BusStop.Bharuch, 50));
        graph[21].add(new Edge(BusStop.Narmada, BusStop.Chhota_Udaipur, 90));
        graph[21].add(new Edge(BusStop.Narmada, BusStop.Surat, 90));
        graph[21].add(new Edge(BusStop.Narmada, BusStop.Tapi, 75));
        graph[21].add(new Edge(BusStop.Narmada, BusStop.Vadodara, 90));

        graph[22].add(new Edge(BusStop.Navsari, BusStop.Dang, 80));
        graph[22].add(new Edge(BusStop.Navsari, BusStop.Surat, 30));
        graph[22].add(new Edge(BusStop.Navsari, BusStop.Tapi, 50));
        graph[22].add(new Edge(BusStop.Navsari, BusStop.Valsad, 35));

        graph[23].add(new Edge(BusStop.Panchmahal, BusStop.Chhota_Udaipur, 80));
        graph[23].add(new Edge(BusStop.Panchmahal, BusStop.Dahod, 50));
        graph[23].add(new Edge(BusStop.Panchmahal, BusStop.Kheda, 70));
        graph[23].add(new Edge(BusStop.Panchmahal, BusStop.Mahisagar, 100));
        graph[23].add(new Edge(BusStop.Panchmahal, BusStop.Vadodara, 55));

        graph[24].add(new Edge(BusStop.Patan, BusStop.Banaskantha, 80));
        graph[24].add(new Edge(BusStop.Patan, BusStop.Kutch, 235));
        graph[24].add(new Edge(BusStop.Patan, BusStop.Mehsana, 40));
        graph[24].add(new Edge(BusStop.Patan, BusStop.Surendranagar, 120));

        graph[25].add(new Edge(BusStop.Porbandar, BusStop.Devbhoomi_Dwarka, 100));
        graph[25].add(new Edge(BusStop.Porbandar, BusStop.Jamnagar, 150));
        graph[25].add(new Edge(BusStop.Porbandar, BusStop.Junagadh, 100));
        graph[25].add(new Edge(BusStop.Porbandar, BusStop.Rajkot, 175));

        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Amreli, 75));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Botad, 80));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Jamnagar, 90));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Junagadh, 100));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Morbi, 70));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Porbandar, 175));
        graph[26].add(new Edge(BusStop.Rajkot, BusStop.Surendranagar, 110));

        graph[27].add(new Edge(BusStop.Sabarkantha, BusStop.Aravalli, 50));
        graph[27].add(new Edge(BusStop.Sabarkantha, BusStop.Banaskantha, 150));
        graph[27].add(new Edge(BusStop.Sabarkantha, BusStop.Gandhinagar, 30));
        graph[27].add(new Edge(BusStop.Sabarkantha, BusStop.Mehsana, 50));

        graph[28].add(new Edge(BusStop.Surat, BusStop.Bharuch, 70));
        graph[28].add(new Edge(BusStop.Surat, BusStop.Narmada, 90));
        graph[28].add(new Edge(BusStop.Surat, BusStop.Navsari, 30));
        graph[28].add(new Edge(BusStop.Surat, BusStop.Tapi, 75));

        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Ahmedabad, 125));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Botad, 70));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Kutch, 350));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Mehsana, 140));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Morbi, 100));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Patan, 120));
        graph[29].add(new Edge(BusStop.Surendranagar, BusStop.Rajkot, 110));

        graph[30].add(new Edge(BusStop.Tapi, BusStop.Dang, 100));
        graph[30].add(new Edge(BusStop.Tapi, BusStop.Narmada, 75));
        graph[30].add(new Edge(BusStop.Tapi, BusStop.Navsari, 50));
        graph[30].add(new Edge(BusStop.Tapi, BusStop.Surat, 75));

        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Anand, 45));
        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Bharuch, 70));
        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Chhota_Udaipur, 100));
        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Kheda, 45));
        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Narmada, 90));
        graph[31].add(new Edge(BusStop.Vadodara, BusStop.Panchmahal, 55));

        graph[32].add(new Edge(BusStop.Valsad, BusStop.Navsari, 35));

    }

    private static void displaySorted(ArrayList<Edge> graph) throws InterruptedException {
        System.out.println();
        for (Edge e : graph) {
            Thread.sleep(100);
            System.out.printf("%-20s == %10s km\n", e.dst, e.distance);
        }
    }

    // Print Bus Stops

    public static void displayStops() throws InterruptedException {
        BusStop[] stopValues = BusStop.values();
        System.out
                .println("            +-----------------------------------------------------------------------------+");
        System.out
                .println("            |                                 STOPS LIST                                  |");
        System.out
                .println("            +-----------------------------------------------------------------------------+");
        for (int i = 0; i < NUM_STOPS; i += 3) {
            Thread.sleep(100);
            String stop1 = (i < NUM_STOPS) ? String.format("%-25s", "[" + (i + 1) + "] " + stopValues[i].name())
                    : "";
            String stop2 = ((i + 1) < NUM_STOPS)
                    ? String.format("%-25s", "[" + (i + 2) + "] " + stopValues[i + 1]
                            .name())
                    : "";
            String stop3 = ((i + 2) < NUM_STOPS)
                    ? String.format("%-25s", "[" + (i + 3) + "] " + stopValues[i + 2]
                            .name())
                    : "";

            System.out.println(
                    "            |                                                                             |");
            System.out.println("            | " + stop1 + stop2 + stop3 + " |");
        }
        System.out
                .println("            |                                                                             |");
        System.out
                .println("            +-----------------------------------------------------------------------------+");

    }

    // Implementation of Djikstra's Shortest Path Algorithm

    public static void findShortestPath(BusStop src, BusStop dst) {
        Map<BusStop, Integer> distance = new HashMap<>();
        Map<BusStop, BusStop> previous = new HashMap<>();
        Set<BusStop> visited = new HashSet<>();

        // Initialize the distance map with infinity for all nodes except the start node
        for (BusStop node : BusStop.values()) {
            distance.put(node, Integer.MAX_VALUE);
        }
      
        distance.put(src, 0);

        // Create a priority queue to keep track of nodes with minimum distance
        PriorityQueue<BusStop> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));
        queue.add(src);

        while (!queue.isEmpty()) {
            BusStop currentNode = queue.poll();
            visited.add(currentNode);

            ArrayList<Edge> neighbours = graph[currentNode.ordinal()];

            for (Edge neighbourEdge : neighbours) {
                BusStop neighborNode = neighbourEdge.dst;
                int edgeWeight = neighbourEdge.distance;
                int newDistance = distance.get(currentNode) + edgeWeight;

                if (newDistance < distance.get(neighborNode)) {
                    distance.put(neighborNode, newDistance);
                    previous.put(neighborNode, currentNode);
                    if (!visited.contains(neighborNode)) {
                        queue.add(neighborNode);
                    }
                }
            }
        }
        if (dst == null) {
            System.out.println();
            for (BusStop stop : BusStop.values()) {
                System.out.printf(" %-20s == %10s km\n", stop, distance.get(stop));
            }
        } else {
            List<BusStop> shortestPath = new ArrayList<>();
            BusStop currentNode = dst;
            while (previous.containsKey(currentNode)) {
                shortestPath.add(currentNode);
                currentNode = previous.get(currentNode);
            }
            shortestPath.add(src);
            Collections.reverse(shortestPath);

            System.out.print("\n                  Shortest Path from " + src + " to " + dst + ": ");
            for (BusStop s : shortestPath) {
                System.out.print(s + " --> ");
            }
            System.out.println(" Distance : " + distance.get(dst) + " km");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        graph = new ArrayList[NUM_STOPS];
        initialiseRoutes();

        while (true) {
            System.out.println("\n0 -> Exit.");
            System.out.println("1 -> Display all stops.");
            System.out.println("2 -> Display all neighbouring stops with distance.");
            System.out.println("3 -> Display shortest distance from source to allstops.");
            System.out.println("4 -> Display shortest path from source to destination.");
            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter your choice in numbers only.");
                choice = -1;
                sc.nextLine();
            }
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    displayStops();
                    break;
                case 2:
                    displayStops();
                    int c;
                    try {
                        System.out.print("\n                Enter stop number whose neighbours you want to display :");
                        c = sc.nextInt();
                        if (c > 0 && c < 34) {
                            graph[c - 1].sort(Comparator.comparing(Edge::getDistance));
                            displaySorted(graph[c - 1]);
                            break;
                        } else {
                            System.out.println("\n  Enter valid stop number.\n");
                        }
                    } catch (Exception e) {
                        System.out.println("\n" + e + "\n");
                        System.out.println("Enter your choice in numbers only.");
                        sc.nextLine();
                    }
                    break;
                case 3:
                    displayStops();
                    try {
                        System.out.print("\nEnter the starting point : ");
                        int stIndex = sc.nextInt();
                        if (stIndex > 0 && stIndex <= BusStop.values().length) {
                            BusStop start = BusStop.values()[stIndex - 1];
                            findShortestPath(start, null);
                        } else {
                            System.out.println("\nEnter valid stop number.\n");
                        }
                    } catch (Exception e) {
                        System.out.println("\n" + e + "\n");
                        System.out.println("Enter your choice in numbers only.");
                        sc.nextLine();
                    }
                    break;

                case 4:
                    displayStops();
                    try {
                        System.out.print("Enter starting point : ");
                        int startIdx = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter destination point : ");
                        int targetIdx = sc.nextInt();

                        if (startIdx > 0 && startIdx <= BusStop.values().length &&
                                targetIdx > 0 && targetIdx <= BusStop.values().length) {
                            BusStop start = BusStop.values()[startIdx - 1];
                            BusStop target = BusStop.values()[targetIdx - 1];
                            findShortestPath(start, target);
                        }
                    } catch (Exception e) {
                        System.out.println("\n" + e + "\n");
                        System.out.println("Enter your choice in numbers only.");
                        sc.nextLine();
                    }
                    break;
                default:
                    System.out.println("Enter valid choice");
                    break;
            }
        }
    }

}