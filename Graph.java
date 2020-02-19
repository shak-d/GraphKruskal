import java.util.*;

public class Graph {

    private HashMap<Object, LinkedList<Edge>> nodesLabel;
    private boolean directGraph;
    private int nodesAmount;

    //creates a new empty graph
    public Graph(boolean isDirect){
        nodesAmount = 0;
        nodesLabel = new HashMap<>();
        directGraph = isDirect;
    }

    //adds a new node to the graph and return the adjacency list
    public LinkedList<Edge> AddNode(Object label){
        if(label==null)
            return null;
        LinkedList<Edge> arrayList = new LinkedList<>();
        nodesLabel.put(label, arrayList);
        nodesAmount += 1;
        return  arrayList;
    }

    //adds a new edge between two nodes, and creates them if needed
    public void AddEdge(Object src, Object dest, float weight, Object label){

        if(src == null || dest == null || label == null)
            return;

        LinkedList<Edge> srcNode = nodesLabel.get(src);
        LinkedList<Edge> dstNode = nodesLabel.get(dest);

        if(srcNode == null)
           srcNode = AddNode(src);
        if(dstNode == null)
           dstNode = AddNode(dest);


        for(Edge e : srcNode){
            if(e.dest == dest)
                return;
        }
        Edge edge = new Edge(src, dest, weight, label);
        srcNode.add(edge);
        if(directGraph)
            return;
        Edge edge1 = new Edge(dest, src, weight, label);
        dstNode.add(edge1);
    }

    //checks if a given node is contained in the graph
   public boolean ContainsNode(Object label){

       return nodesLabel.get(label) != null;
   }

   //checks if there's an edge between two nodes
   public boolean ContainsEdge(Object src, Object dest){

        LinkedList<Edge> srcNode = nodesLabel.get(src);
        LinkedList<Edge> dstNode = nodesLabel.get(dest);
        if(srcNode == null || dstNode == null)
            return false;

        for(Edge adj : srcNode){
            if(adj.dest == dest)
                return true;
        }

        return false;
    }

    //deletes the edge between two nodes
    public void deleteEdge(Object src, Object dest){

        LinkedList<Edge> srcNode = nodesLabel.get(src);
        LinkedList<Edge> dstNode = nodesLabel.get(dest);
        if(srcNode == null || dstNode == null)
            return;

        for(Edge edge : srcNode){
            if(edge.dest == dest){
                srcNode.remove(edge);
                if(!directGraph){
                    for(Edge edge1 : dstNode){
                        if(edge1.dest == src)
                            dstNode.remove(edge1);
                    }
                }
                return;
            }
        }
    }

    //delete the given node from the graph with all its edges
    public void deleteNode(Object label){

        LinkedList<Edge> target = nodesLabel.get(label);
        if(target == null)
            return;
        for(Map.Entry<Object, LinkedList<Edge>> set : nodesLabel.entrySet()){

            LinkedList<Edge> node = set.getValue();
            for(Edge edge : node){
                if(edge.dest == label)
                    node.remove(edge);
                break;
            }

        }
        nodesAmount = nodesAmount - 1;
        nodesLabel.remove(label);

    }

    //returns a list of all the nodes in the graph
    public ArrayList<Object> getNodesLabels(){

        ArrayList<Object> labels = new ArrayList<>();

        for(Map.Entry<Object, LinkedList<Edge>> set : nodesLabel.entrySet()){

           labels.add(set.getKey());

        }
        return labels;
    }

    //returns the amount of nodes in the graph
    public int getNodesAmount(){
        return nodesAmount;
    }

    //returns the amount of edges in the graph
    public int getEdgesAmount(){
        int edges = 0;
        for(Map.Entry<Object, LinkedList<Edge>> set : nodesLabel.entrySet())
            edges += set.getValue().size();
        if(directGraph)
            return edges;
        return edges/2;
    }

    //returns a list of all the edges in the graph
    public ArrayList<Edge> getEdges(){
        ArrayList<Edge> edges = new ArrayList<>();
        for(Map.Entry<Object, LinkedList<Edge>> set : nodesLabel.entrySet()){

            LinkedList<Edge> node = set.getValue();
            edges.addAll(node);

        }
        return  edges;
    }

    //returns a list of all the adjacent nodes to a given node
    public ArrayList<Object> getAdjacentNodes(Object label){

        ArrayList<Object> adjacentList = new ArrayList<>();
        LinkedList<Edge> node = nodesLabel.get(label);
        if(node == null)
            return null;
        for(Edge edge : node)
            adjacentList.add(edge.dest);
        return  adjacentList;
    }

    //returns the edge label between two nodes
    public Object getEdgeLabel(Object src, Object dest){
        if(src == null || dest == null)
            return null;
        LinkedList<Edge> srcNode = nodesLabel.get(src);
        LinkedList<Edge> destNode = nodesLabel.get(dest);
        if(srcNode == null || destNode == null)
            return null;
        for(Edge edge : srcNode){
            if(edge.dest == dest)
                return edge.label;
        }
        return null;
    }

    //return true if the graph is directed
    public boolean isDirect(){
        return directGraph;
    }

}


