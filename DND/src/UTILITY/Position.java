package UTILITY;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Position {
    private int x;
    private int y;
    // just for dijkstra
    private LinkedList<Position> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Position,Integer> adjacentNodes = new HashMap<>();
    private boolean visited= false;
    public Position(int z,int t){
        x=z;
        y=t;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int newX ){
        this.x=newX;
    }
    public void setY(int newY){
        this.y=newY;
    }
    public void swap(int newX,int newY){
        this.x=newX;
        this.y=newY;
    }
//just for dijkstra
    public void addDestination(Position destination,int distance){
        adjacentNodes.put(destination,distance);
    }
    public void clearDestinations(){
        this.adjacentNodes=new HashMap<>();
    }
    public int getDistance(){
        return distance;
    }
    public LinkedList<Position> getShortestPath(){
        return shortestPath;
    }
    public void clearPath(){
        this.shortestPath =new LinkedList<>();
    }
    public void setDistance(int distance){
        this.distance=distance;
    }
    public void setShortestPath(LinkedList<Position> shortestPath) {
        this.shortestPath=shortestPath;
    }

    public Map<Position,Integer> getAdjacentNodes() {
        return adjacentNodes;
    }
    public boolean isVisited(){
        return visited;
    }
    public void setVisited(boolean visit){
        this.visited=visit;
    }
    public String toString(){
        return "X value: "+x + " Y value:"+y;
    }
    public boolean equals(Position other){
        return x == other.x &y == other.y;
    }

    public void addDistance(Position position) {
        this.shortestPath.addLast(position);
    }
}
