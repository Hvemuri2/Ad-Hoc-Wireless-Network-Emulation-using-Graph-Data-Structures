
import java.util.*;

/**
 * this class Implements a graph data structure using the adjacency list model.
 */
public class NetGraph {

    /**
     * represent the uninitialized graph data structure.
     */
    private ArrayList<AdjacencyListHead> nodesList;

    /**
     * Constructor initializes the graph.
     * @param nodesList represents the graph.
     */
    public NetGraph(ArrayList<AdjacencyListHead> nodesList){
        this.nodesList=nodesList;
        //This method is complete
    }

    /**
     * getter for the graph data structure(nodesList).
     * @return returns an arraylist representing the graph.
     */
    public ArrayList<AdjacencyListHead> getNodesList(){
        return nodesList;
        //This method is complete
    }

    /**
     * private helper method to compare two NetNodes.
     * @param node1 node being compared.
     * @param node2 node being compared.
     * @return represents if the 2 nodes are equal.
     */
    private boolean checkEqual(NetNode node1, NetNode node2){
        return node1.getId()==node2.getId();
    }

    /**
     * getter
     * @return an int representing the number of nodes in the graph.
     */
    public int getNumNodes(){
        return nodesList.size();
    }

    /**
     * getter
     * @return an int representing the number of links in the graph.
     */
    public int getNumLinks(){
        int numLinks = 0;
        for(AdjacencyListHead a : nodesList){
            numLinks += a.getAdjacencyList().size();
        }
        return numLinks/2;
    }

    /**
     * this method is used to create and insert nodes into the graph.
     * @param id one of the attributes required to create a node.
     * @param name one of the attributes required to create a node.
     * @param x_coordinate one of the attributes required to create a node.
     * @param y_coordinate one of the attributes required to create a node.
     */
    public void insertNetNode(int id,String name,double x_coordinate,double y_coordinate)
	{
        NetNode node = new NetNode(id,name,x_coordinate,y_coordinate);
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node))throw new IllegalArgumentException();
        }

        AdjacencyListHead adjnodeHd = new AdjacencyListHead(node);
        nodesList.add(adjnodeHd);
	}

    /**
     * this method add a link between two nodes in the graph after inserting a node.
     * @param node1 one of the nodes being linked.
     * @param node2 one of the nodes being linked.
     * @param weight distance between the 2 nodes.
     */
    public void addLink(NetNode node1, NetNode node2, double weight) {
        boolean valid = false;
        boolean valid1 = false;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node1))valid = true;
            if(checkEqual(a.getNetNode(),node2))valid1 = true;
        }
        if(node1==null||node2==null||!valid1||!valid)throw new IllegalArgumentException();

        Adjacent a1 = new Adjacent(node1, weight);
        Adjacent a2 = new Adjacent(node2, weight);

        for(AdjacencyListHead a:nodesList){ // adding a1 and a2 onto respective adjacency lists
            if(checkEqual(a.getNetNode(),node1)){ // if we find node1, we add a2 onto its adjacency list
                LinkedList<Adjacent> adjList = a.getAdjacencyList();
                boolean absnt = true;
                for(Adjacent ad:adjList){
                    if(checkEqual(ad.getNeighbor(),a2.getNeighbor()))absnt=false;
                }if(absnt){
                    adjList.add(a2);
                    a.setAdjacencyList(adjList);
                }
            }

            if(checkEqual(a.getNetNode(),node2)){ // if we find node2, we add a1 onto its adjacency list
                LinkedList<Adjacent> adjList = a.getAdjacencyList();
                boolean absnt = true;
                for(Adjacent ad:adjList){
                    if(checkEqual(ad.getNeighbor(),a1.getNeighbor()))absnt=false;
                }if(absnt){
                    adjList.add(a1);
                    a.setAdjacencyList(adjList);
                }
            }
        }
    }

    /**
     * this method is used to delete a node from the graph.
     * @param node NetNode to be deleted.
     */
    public void deleteNetNode(NetNode node){
        boolean valid = false;
        for(AdjacencyListHead a: nodesList){
            if(checkEqual(a.getNetNode(),node))valid =true;
        }
        if(node==null||!valid)throw new IllegalArgumentException();

        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node)){
                nodesList.remove(a);
            }

            LinkedList<Adjacent> adjList = a.getAdjacencyList();
            for(Adjacent a1:adjList){
                if(checkEqual(a1.getNeighbor(),node)){
                    adjList.remove(a1);
                }
            }
            a.setAdjacencyList(adjList);
        }
    }

    /**
     * this method is used to remove link between any two nodes
     * @param node1 one of the nodes that is linked.
     * @param node2 one of the nodes that is linked.
     */
    public void removeLink(NetNode node1, NetNode node2)
	{   
        boolean valid = false;
        boolean valid1 = false;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node1))valid = true;
            if(checkEqual(a.getNetNode(),node2))valid1 = true;
        }
        if(node1==null||node2==null||!valid||!valid1)throw new IllegalArgumentException();

        for(AdjacencyListHead a:nodesList){
            LinkedList<Adjacent> adjList = a.getAdjacencyList();
            if(checkEqual(a.getNetNode(),node1)){
                for(Adjacent a1:adjList){
                    if(checkEqual(a1.getNeighbor(),node2))adjList.remove(a1);
                }
            }if(checkEqual(a.getNetNode(),node2)){
                for(Adjacent a2:adjList){
                    if(checkEqual(a2.getNeighbor(),node1))adjList.remove(a2);
                }
            }
            a.setAdjacencyList(adjList);
        }
    }

    /**
     * this method return the number of adjacent NetNodes of a given NetNode.
     * @param node NetNode.
     * @return linked list representing a list of adjacent NetNodes to the given NetNode.
     */
    public LinkedList<Adjacent> getAdjacents(NetNode node){
        boolean valid = false;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node))valid=true;
        }
        if(node==null||!valid)throw new IllegalArgumentException();

        LinkedList<Adjacent> result = new LinkedList<>();
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node))result=a.getAdjacencyList();
        }
        return result;
            
    }

    /**
     * this method gives the index of a specific node in the graph.
     * @param node node to find.
     * @return an int representing the location of the node in the graph.
     */
    int getNodeIndex(NetNode node){
        boolean valid = false;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node))valid=true;
        }
        if(node==null||!valid)throw new IllegalArgumentException();

        int index = -1;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node)){
                index = nodesList.indexOf(a);
            }
        }
        return index;
    }

    /**
     * gives the degree or the number of adjacent nodes to a specific node.
     * @param node node of focus.
     * @return aa int representing the number of adjacent nodes the node in focus has.
     */
    public int degree(NetNode node){
        boolean valid = false;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node))valid=true;
        }

        int degree = -1;
        for(AdjacencyListHead a:nodesList){
            if(checkEqual(a.getNetNode(),node)){
                degree = a.getAdjacencyList().size();
            }
        }

        return degree;
    }


    /**
     * this method gives the max degree of the graph.
     * @return an int representing the max degree of the graph.
     */
    public int getGraphMaxDegree()
	{
        int max = -1;
        for(AdjacencyListHead a:nodesList){
            int currVal = a.getAdjacencyList().size();
            if(currVal>max)max=currVal;
        }

        return max;
	}

    /**
     * gives a node when given an index.
     * @param index index to look for.
     * @return the NetNode at the desired index.
     */
    public NetNode nodeFromIndex(int index)
    {
        int indx = 0;
        NetNode result = null;
        for(AdjacencyListHead a:nodesList){
            if(indx==index)result=a.getNetNode();
            indx++;
        }
        return result;
    }

    /**
     * gives a string representation of this data structure.
     * @return a String.
     */
    public String printGraph(){
        String result = "";

        for(AdjacencyListHead a:nodesList){
            result += a.getNetNode().getName();
            result += ":{";
            LinkedList<Adjacent> adjList = a.getAdjacencyList();
            int i=0;
            for(Adjacent a1:adjList){
                if(i == (adjList.size()-1)){
                    result += "("+a1.getNeighbor().getName()+","+a1.getWeight()+")";
                }else{
                    result += "("+a1.getNeighbor().getName()+","+a1.getWeight()+"), ";
                }
                i++;
            }
            result+= "}\n";
        }

        return result;
    }

}
