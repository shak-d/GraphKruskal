import java.util.ArrayList;
import java.util.List;

public class UnionFindSet {

    private ArrayList<Object> nodes;
    private ArrayList<Integer> parents;
    private ArrayList<Integer> size;

    public UnionFindSet(){

        nodes = new ArrayList<>();
        parents = new ArrayList<>();
        size = new ArrayList<>();
    }

    //creates n different sets from n objects
    public void MakeSet(List<Object> list){

        if(list == null || list.isEmpty())
            return;
        int id = 0;
        for(Object obj : list) {

            if (!nodes.contains(obj) && obj != null) {

                nodes.add(obj);
                parents.add(id);
                size.add(1);
                id = id + 1;

            }
        }
    }

    //returns the representative item of the specified object
    public Object Find(Object target){

        for(Object obj : nodes){
            if(obj.equals(target)) {

                int nodeIndex = nodes.indexOf(obj);
                int parentIndex = parents.get(nodeIndex);
                if(parentIndex == nodeIndex)
                    return obj;
                else {
                    Object rep = Find(nodes.get(parentIndex));
                    parents.set(nodeIndex, nodes.indexOf(rep));
                    return rep;
                }
            }
        }
        return null;
    }

    //merges two different sets and returns the final set representative
    public Object Union(Object node1, Object node2){

        int rep1, rep2;
        if(nodes.contains(node1) && nodes.contains(node2)){

            Object r1 = Find(node1);
            Object r2 = Find(node2);
            rep1 = nodes.indexOf(r1);
            rep2 = nodes.indexOf(r2);
            if(rep1 == rep2)
                return r1;
            if(size.get(rep1) > size.get(rep2)){
                size.set(rep1, size.get(rep1) + size.get(rep2));
                parents.set(rep2, rep1);
                return r1;
            }
            else{
                size.set(rep2, size.get(rep1)+size.get(rep2));
                parents.set(rep1,rep2);
                return r2;
            }

        }
        return null;
    }

}
