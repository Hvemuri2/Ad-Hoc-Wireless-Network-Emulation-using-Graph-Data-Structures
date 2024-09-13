
import java.util.*;

/**
 * This class is responsible for creating and implementing the network.
 * While this class also applies algorithms on the network for respective purposes.
 */
public class Network {

    /**
     * This is a private inner class representing a custom implementation of an edge between 2 NetNodes.
     * This class implements comparable which enables a custom comparison between 2 NetNodes that are part of the edge.
     */
    private static class Edge implements Comparable<Edge>{

        /**
         * start node.
         */
        NetNode start;

        /**
         * end node.
         */
        NetNode end;

        /**
         * distance of edge.
         */
        double weight;

        /**
         * Constructor.
         * @param start NetNode.
         * @param end NetNode.
         * @param weight double.
         */
        Edge(NetNode start, NetNode end, double weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        /**
         * custom compareto.
         * @param o the object to be compared.
         * @return int representing the diff between edges.
         */
        @Override
        public int compareTo(Edge o) {
            if(this.weight > o.weight)return 1;
            else if(this.weight < o.weight)return -1;
            return 0;
        }
    }

    /**
     * This is an implementation of a custom node that is used in the method "getShortestPath".
     * This class implements comparable which enables a custom comparison between 2 Nodes.
     */
    private static class Node implements Comparable<Node>{

        /**
         * currNode.
         */
        NetNode currNode;

        /**
         * distance.
         */
        double distance;

        /**
         * predeccesor.
         */
        NetNode predeccesor;

        /**
         * constructor.
         * @param currNode NetNode.
         * @param distance double.
         * @param predeccesor NetNode.
         */
        Node(NetNode currNode, double distance, NetNode predeccesor){
            this.currNode = currNode;
            this.distance = distance;
            this.predeccesor = predeccesor;
        }

        /**
         * custom compareTo.
         * @param o the object to be compared.
         * @return int representing the diff between Nodes.
         */
        @Override
        public int compareTo(Node o) {
            if(distance>o.distance)return 1;
            else if(distance<o.distance)return -1;
             return 0;
        }
    }

    /**
     * this is an object represents a custom graph data structure.
      */
    private NetGraph networkGraph;

    /**
     * Default constructor creates a Network consisting of 1000 nodes located in an area of 200x200.
     */
    public Network(){
        Network sampleNetwork=new Network(1000,200);
    }

    /**
     * Constructor initializes the networkGraph and generates links connecting the nodes
     * based on the 20*sqrt(2) unit transmission range of the nodes.
     * @param numofNodes represent the number of nodes in the graph.
     * @param side represent the side of the area limit of the graph.
     */
    public Network(int numofNodes,double side){
        ArrayList<AdjacencyListHead> nodesList=new ArrayList<AdjacencyListHead>();
        for (int i = 0; i < numofNodes; i++) {
			double x_coordinate = side*Math.random();  
			double y_coordinate = side*Math.random();  
			int id=i;
            String name="node "+i;
			NetNode node=new NetNode(id,name,x_coordinate,y_coordinate);
            nodesList.add(new AdjacencyListHead(node));
                        
		}

        networkGraph=new NetGraph(nodesList);
        for(AdjacencyListHead a:nodesList){
            for(AdjacencyListHead a1:nodesList){
                double reqDist = 20*(Math.sqrt(2));
                double eucDist = euclideanDistance(a.getNetNode(), a1.getNetNode());
                if(eucDist <= reqDist){
                    networkGraph.addLink(a.getNetNode(),a1.getNetNode(),eucDist);
                }
            }
        }
    }

    /**
     * this method calculates the euclidean Distance between two adjacent nodes.
     * @param node1 represents one of the nodes that are part of the edge.
     * @param node2 represents one of the nodes that are part of the edge.
     * @return a double representing the euc distance between the 2 nodes.
     */
    double euclideanDistance(NetNode node1,NetNode node2){
	    double x1 = node1.getX_coordinate();
        double y1 = node1.getY_coordinate();

        double x2 = node2.getX_coordinate();
        double y2 = node2.getY_coordinate();

        return Math.sqrt(Math.pow((x2-x1),2.0)+Math.pow((y2-y1),2.0));
    }

    /**
     * this method returns a matrix representation of the minimum spanning tree of the respective graph.
     * this method makes use of the Prim's algorithm to do so.
     * @return a 2D array representing the minimum spanning tree.
     */
    public int[][] minSpanningTree()
	{
        int edges = 0;
        ArrayList<AdjacencyListHead> graph = networkGraph.getNodesList();

        int[][] mst = new int[graph.size()][graph.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[][] visited = new int[graph.size()][graph.size()];

        AdjacencyListHead firstStart = graph.get(0);
        for(Adjacent a:firstStart.getAdjacencyList()){
            pq.add(new Edge(firstStart.getNetNode(), a.getNeighbor(), a.getWeight()));
        }

        while(!pq.isEmpty()||edges<=graph.size()-1){
            Edge e = pq.poll();
            int indx1 = e.start.getId();
            int indx2 = e.end.getId();

            if(visited[indx1][indx2]==0){
                visited[indx1][indx2]=1;
                mst[indx1][indx2]=1;
                edges++;
            }

            AdjacencyListHead Start = find(e.end);
            for(Adjacent a:Start.getAdjacencyList()){
                int idx1 = Start.getNetNode().getId();
                int idx2 = a.getNeighbor().getId();
                if(visited[idx1][idx2]==0){
                    pq.add(new Edge(Start.getNetNode(), a.getNeighbor(), a.getWeight()));
                }
            }
        }

        return mst;       //the running time should be <= O(n*n) where n is the number of vertices in the graph
	}

    /**
     * private helper method for the "minSpanningTree" method.
     * @param n a NetNode
     * @return a AdjacencyListHead that has "n" as its node.
     */
    private AdjacencyListHead find(NetNode n){
        for(AdjacencyListHead a: networkGraph.getNodesList()){
            if(a.getNetNode().equals(n))return a;
        }
        return null;
    }

    /**
     * This method gives the shortest path between 2 nodes in a graph.
     * This method makes use of Dijkstra's algorithm to do so.
     * @param node1 represents the starting node of the path.
     * @param node2 represents the ending node of the path.
     * @return an arraylist representing the path.
     */
    public ArrayList<NetNode> getShortestPath(NetNode node1, NetNode node2){

        PriorityQueue<Node> temp = new PriorityQueue<>();
        ArrayList<Node> temp1 = new ArrayList<>();

        temp.add(new Node(node1, 0, node1));
        temp1.add(new Node(node1, 0, node2));

        for(int i=1;i<networkGraph.getNodesList().size();i++){
            temp1.add(new Node(networkGraph.getNodesList().get(i).getNetNode(),Integer.MAX_VALUE, null));
        }

        ArrayList<Node> preResult = new ArrayList<>();

        while(!temp.isEmpty()){
            Node currNode = temp.poll();
            AdjacencyListHead a = find(currNode.currNode);
            for(Adjacent a1:a.getAdjacencyList()){
                Node currNode2 = find2(a1.getNeighbor(), temp1);
                if(!preResult.contains(currNode2)){
                    if(currNode2.distance>(currNode.distance + a1.getWeight())){
                        currNode2.distance = (currNode.distance + a1.getWeight());
                        currNode2.predeccesor = currNode.currNode;
                    }
                }
                temp.add(currNode2);
            }
            preResult.add(currNode);
        }
        return determinePath(preResult, node1, node2); //the running time complexity is O(V lg V) where V is the number of vertices in the graph
    }

    /**
     * helper method to the method "getShortestPath".
     * gets an arraylist holding the shortest path to all the nodes in the graph from the "getShortestPath" method.
     * this method return the shortest path between the 2 desired nodes by picking this path from the received arraylist.
     * @param preResult this is the arraylist holding the shortest path to all nodes in the graph.
     * @param source this is the starting node of the path.
     * @param end this is the ending node of the path.
     * @return the desired path in the form of an arraylist.
     */
    private ArrayList<NetNode> determinePath(ArrayList<Node> preResult, NetNode source, NetNode end){
        ArrayList<NetNode> result = new ArrayList<>();
        Stack<NetNode> subResult = new Stack<>();

        NetNode nodeToAdd = end;

        while(nodeToAdd.getId()!=source.getId()){
            Node node = find2(nodeToAdd,preResult);
            subResult.push(nodeToAdd);
            nodeToAdd = node.predeccesor;
        }

        while (!subResult.isEmpty()){
            result.add(subResult.pop());
        }

        return result;
    }

    /**
     * second helper method to the "getShortestPath" method.
     * @param node NetNode to find.
     * @param list list to search.
     * @return a Node consisting the desired NetNode.
     */
    private Node find2(NetNode node, ArrayList<Node> list){
        for(Node n:list){
            if(n.currNode.getId()== node.getId())return n;
        }
        return null;
    }
  
    
 
    
	
}
