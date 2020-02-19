public class Edge{
    Object dest;
    Object src;
    float weight;
    Object label;
    Edge(Object src, Object dest, float weight, Object label){
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.label = label;
    }
}
