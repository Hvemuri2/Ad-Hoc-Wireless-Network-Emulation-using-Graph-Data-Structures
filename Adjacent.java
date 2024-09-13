/**
 * this class is provided to us.
 * this class represents a link
 */
public class Adjacent {

    /**
     * the adjacent node to the node in focus.
     */
    private NetNode neighbor;

    /**
     * the distance of the link.
     */
    private double weight;

    /**
     * constructor.
     * @param neighbor NetNode.
     * @param weight double.
     */
    public Adjacent(NetNode neighbor,double weight){
        this.neighbor=neighbor;
        this.weight=weight;
    }

    /**
     * setter
     * @param neighbor NetNode.
     */
    public void setNeighbor(NetNode neighbor){
        this.neighbor=neighbor;
    }

    /**
     * setter.
     * @param weight double.
     */
    public void setWeight(double weight){
        this.weight=weight;
    }

    /**
     * getter.
     * @return a NetNode
     */
     public NetNode getNeighbor(){
         return neighbor;
     }

    /**
     * getter.
     * @return a double.
     */
    public double getWeight(){
        return weight;
    }  
} 
