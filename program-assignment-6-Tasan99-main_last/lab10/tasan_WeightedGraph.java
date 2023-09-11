public class tasan_WeightedGraph implements WeightedGraphFunctions {

    private final java.util.ArrayList <WeightedEdge> WeightedEdges;
    private final java.util.ArrayList <Integer> WeightedVertexes;

    tasan_WeightedGraph(){

        this.WeightedEdges = new java.util.ArrayList<>();
        this.WeightedVertexes = new java.util.ArrayList<>();
    }

    @Override
    public boolean hasPath(int fromVertex, int toVertex) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public double getMinimumWeight(int fromVertex, int toVertex) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public WeightedEdge[] getPath(int fromVertex, int toVertex) {
        // TODO Auto-generated method stub

        return new WeightedEdge[0];
    }

    @Override
    public boolean addVertex(int v) {
        // TODO Auto-generated method stub

        if(!WeightedVertexes.contains(Integer.valueOf(v)))
        {
            WeightedVertexes.add(Integer.valueOf(v));
            return true;
        }

        return false;
    }

    @Override
    public boolean addWeightedEdge(int from, int to, double weight) {
        // TODO Auto-generated method stub

        for(WeightedEdge i: WeightedEdges){
            if(i.getFromVertex() == from && i.getToVertex() == to) return false;

        }

        WeightedEdges.add(new WeightedEdge(from, to, weight));
        return true;
    }

    public String toString(){

        java.lang.StringBuilder e = new java.lang.StringBuilder();
        e = e.append("G = (V, E)\n");
        e = e.append("V = {");

        for(int i=0; i<WeightedVertexes.size(); i++){

            e = e.append(WeightedVertexes.get(i));
            if(i!= WeightedVertexes.size()-1){

                e = e.append(",");

            }
        }

        e = e.append("}\n");
        e = e.append("E = {");

        for(int i=0; i<WeightedEdges.size(); i++){

            e = e.append(WeightedEdges.get(i));
            if(i!= WeightedEdges.size()-1){

                e = e.append(",");

            }
        }

        e = e.append("}");
        return e.toString();

    }

}








