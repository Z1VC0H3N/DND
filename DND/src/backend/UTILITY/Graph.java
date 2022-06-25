package backend.UTILITY;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Set<Position> nodes= new HashSet<>();
    public Graph(Set<Position> pos){
       this.nodes=pos;
    }
    public void addNode(Position p){
        nodes.add(p);
    }
    public Set<Position> getSet(){
        return nodes;
    }
    public Graph calculateShortestPathFromSource(Graph graph, Position source) {
        source.setDistance(0);
        Set<Position> settledNodes = new HashSet<>();
        Set<Position> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Position currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry< Position, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Position adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }
    private static void calculateMinimumDistance(Position evaluationNode,
                                                 Integer edgeWeigh, Position sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Position> shortestPath = new LinkedList<Position>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }
    private static Position getLowestDistanceNode(Set< Position > unsettledNodes) {
        Position lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Position node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }
}
