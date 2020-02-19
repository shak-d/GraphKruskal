import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    private static Graph Kruskal(Graph graph){

        Graph minGraph = new Graph(false);
        UnionFindSet findSet = new UnionFindSet();
        ArrayList<Edge> sortedEdges;
        findSet.MakeSet(graph.getNodesLabels());
        sortedEdges = graph.getEdges();
        sortedEdges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if(o1.weight==o2.weight)
                    return 0;
                else  if(o1.weight<o2.weight)
                    return  -1;
                else
                    return 1;
            }
        });

        for(Edge edge : sortedEdges){
            if(!findSet.Find(edge.src).equals(findSet.Find(edge.dest))){

               findSet.Union(edge.src, edge.dest);
               minGraph.AddEdge(edge.src, edge.dest, edge.weight, edge.label);

            }
        }
        return minGraph;
    }

    public static void main(String[] args){

        File file = new File("C:\\cities.csv");
        Scanner scanner;
        Graph graph = new Graph(false);
        UnionFindSet unionFindSet = new UnionFindSet();


        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
            return;
        }
         while (scanner.hasNextLine()) {
             String line = scanner.nextLine();
            String[] data = line.split(",");
            graph.AddEdge(data[0], data[1], Float.parseFloat(data[2]), data[0]+data[1]);
         }

        Graph minGraph = Kruskal(graph);
        System.out.println(minGraph.getNodesAmount());
        System.out.println(minGraph.getEdgesAmount());

        long tWeight = 0;
        for(Edge edge : minGraph.getEdges()){
            tWeight += edge.weight;
        }
        System.out.println(tWeight/2);
    }

}
