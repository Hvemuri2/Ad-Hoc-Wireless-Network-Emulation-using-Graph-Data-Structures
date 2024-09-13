/**
 * this class was provided to us.
 * This class acts as a skeleton that represents the nodes that form the graph data structure.
 */
public class NetNode {

    /**
     * int representing the unique id of the node.
     */
    private int id;

    /**
     * String representing the name of the node.
     */
    private String name;

    /**
     * double representing the x-coordinate of the node's location.
     */
    private double x_coordinate;

    /**
     * double representing the y-coordinate of the node's location.
     */
    private double y_coordinate;

    /**
     * Constructor.
     * @param id int.
     * @param name String.
     * @param x_coordinate double.
     * @param y_coordinate double.
     */
    public NetNode(int id,String name,double x_coordinate,double y_coordinate){
        this.id=id;
        this.name=name;
        this.x_coordinate=x_coordinate;
        this.y_coordinate=y_coordinate;  
    }

    /**
     * setter
     * @param id integer.
     */
    public void setId(int id){
        this.id=id;
    }

    /**
     * setter.
     * @param name String.
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * setter
     * @param x_coordinate double.
     */
    public void setX_coordinate(double x_coordinate){
        this.x_coordinate=x_coordinate;
    }

    /**
     * setter
     * @param y_coordinate double.
     */
     public void setY_coordinate(double y_coordinate){
        this.y_coordinate=y_coordinate;
    }

    /**
     * getter.
     * @return an integer.
     */
    public int getId(){
        return id;
    }

    /**
     * getter.
     * @return a String.
     */
    public String getName(){
        return name;
    }

    /**
     * getter.
     * @return a double.
     */
    public double getX_coordinate(){
        return x_coordinate;
    }

    /**
     * getter.
     * @return a double.
     */
     public double getY_coordinate(){
        return y_coordinate;
    }
 }
