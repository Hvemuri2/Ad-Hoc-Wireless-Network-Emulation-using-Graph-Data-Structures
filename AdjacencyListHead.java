/**
 * this class was provided to us.
 * this class represents a NetNode and all the links it has.
 */

import java.util.*;
public class AdjacencyListHead {

    /**
     * the main node.
     */
    private NetNode node;

    /**
     * list of adjacent nodes to the main node.
     */
    private LinkedList<Adjacent> adjacencyList;

    /**
     * default constructor initializes the list of adjacent nodes to an empty linked list.
     * @param node NetNode that represents the main node.
     */
    public AdjacencyListHead(NetNode node){
        this.node=node;
        this.adjacencyList=new LinkedList<Adjacent>();
    }

    /**
     * Constructor.
     * @param node NetNode that represents the main node.
     * @param adjacencyList linked list that represent the list of adjacent nodes.
     */
    public AdjacencyListHead(NetNode node,LinkedList<Adjacent> adjacencyList){
        this.node=node;
        this.adjacencyList=adjacencyList;
    }

    /**
     * setter.
     * @param node NetNode.
     */
    public void setNetNode(NetNode node){
        this.node=node;
    }

    /**
     * setter
     * @param adjacencyList LinkedList.
     */
    public void setAdjacencyList(LinkedList<Adjacent> adjacencyList){
        this.adjacencyList=adjacencyList;
    }

    /**
     * getter.
     * @return a NetNode.
     */
    public NetNode getNetNode(){
        return node;
    }

    /**
     * getter
     * @return a LinkedList.
     */
    public LinkedList<Adjacent> getAdjacencyList(){
        return adjacencyList;
    }
    
    
}
