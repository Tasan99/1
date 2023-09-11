class WeightedGraph implements WeightedGraphFunctions
{   
    private final java.util.ArrayList<Integer> vertices;
    private final java.util.ArrayList<WeightedEdge> edges;

    WeightedGraph(){
        this.vertices = new java.util.ArrayList<Integer>();
        this.edges = new java.util.ArrayList<WeightedEdge>();
    }

    private Object getPath(int fromVertex, int toVertex, WeightedGraphReturnType typeOfInfo){
        java.util.PriorityQueue<WeightedVertex> minPriorityQueueByWeight = new java.util.PriorityQueue<>(vertices.size(), new WeightedVertexComparator());
        WeightedVertex[] verticeCost = new WeightedVertex[vertices.size()];
        int[] parent = new int[vertices.size()];
        
        int fromVertexIndex = getIndex(fromVertex);
		int toVertexIndex = getIndex(toVertex);

        if( (fromVertexIndex == -1) || (toVertexIndex == -1) ){
			if(typeOfInfo == WeightedGraphReturnType.HAS_PATH ){return Boolean.valueOf(false);}
			if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT){return Double.valueOf(Double.NaN);}
			return new WeightedEdge[0];
		}

        for(int i = 0; i < vertices.size(); i++)
        {
            parent[i] = -1;
            verticeCost[i] = new WeightedVertex(vertices.get(i), Double.POSITIVE_INFINITY);
        }

        parent[fromVertexIndex] = fromVertex;
        verticeCost[fromVertexIndex] = new WeightedVertex(vertices.get(fromVertexIndex), 0.0);
        
        for(int i = 0; i < vertices.size(); i++)
        {
            minPriorityQueueByWeight.add(verticeCost[i]);
        }

        
        int u = 0;
        double up = 0;
        boolean existed = false;
        while ((!existed) && (minPriorityQueueByWeight.size() > 0)){
            WeightedVertex verWei = minPriorityQueueByWeight.poll();
            
            if((parent[getIndex(toVertex)] == -1) && (verWei.getVertex() == toVertex)){break;}
            if( verWei.getVertex() == toVertex ){
				existed = true;
				if( typeOfInfo == WeightedGraphReturnType.HAS_PATH ){return Boolean.valueOf(true);}
				if( typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT ){return Double.valueOf(verWei.getWeight());}
			}

            if(!existed){
                for(int i = 0; i < edges.size(); i++){
                    if(edges.get(i).getFromVertex() == verWei.getVertex()){
                        u = edges.get(i).getToVertex();
                        int indexu = getIndex(u);
                        up = verWei.getWeight()+ edges.get(i).getWeight();
                        if(up < verticeCost[indexu].getWeight()){
    
                            minPriorityQueueByWeight.remove(verticeCost[indexu]);
                            verticeCost[indexu].setWeight(up);
                            minPriorityQueueByWeight.add(verticeCost[indexu]);
                            parent[indexu]=verWei.getVertex();
                        }
                    }
                }
            }
        }
            if( !existed ){
			    if(typeOfInfo == WeightedGraphReturnType.HAS_PATH ){return Boolean.valueOf(false);}			
			    if(typeOfInfo == WeightedGraphReturnType.GET_MINIMUM_WEIGHT){return Double.valueOf(Double.NaN);}
			    return new WeightedEdge[0];
		    }

            if(existed){
                java.util.ArrayList<Integer> reversePath = new java.util.ArrayList<Integer>();
                int p= toVertex;
                reversePath.add(Integer.valueOf(p));
                while( p != fromVertex){
                    p = parent[getIndex(p)];
                    reversePath.add(Integer.valueOf(p));
                }

                java.util.ArrayList<Integer> forwardPath = new java.util.ArrayList<Integer>();
                for( int i = reversePath.size()-1; i >= 0; i-- )
                {
                   forwardPath.add(reversePath.get(i));
                }

                WeightedEdge[] ThePath = new WeightedEdge[forwardPath.size()-1];
                for(int i = 0; i < ThePath.length; i++)
			    {
				int fromV = forwardPath.get(i).intValue();
				int toV = forwardPath.get(i+1).intValue();
				boolean edgeFound = false;
				for(int j = 0; (!edgeFound) && (j < edges.size()); j++)
				{
					WeightedEdge e = edges.get(j);
					if((e.getFromVertex() == fromV) && (e.getToVertex() == toV))
					{
						edgeFound = true;
						ThePath[i] = e;
					}
				}
			    }
                return ThePath;
            }            
        
        return new WeightedEdge[0];
    }


    public boolean hasPath(int fromVertex, int toVertex)
	{
		Object returnValue = getPath(fromVertex, toVertex, WeightedGraphReturnType.HAS_PATH);
        boolean rettype = Boolean.valueOf(((Boolean) returnValue).booleanValue()); 
		return rettype;
	}

  
    public double getMinimumWeight(int fromVertex, int toVertex){
        Object returnValue = getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_MINIMUM_WEIGHT);
        double rettype = Double.valueOf(((Double) returnValue).doubleValue());
		return rettype;
    }

    public int getIndex(int value)
	{
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).intValue() == value){return i;}
		}
		return 0;			
	}

    public WeightedEdge[] getPath(int fromVertex, int toVertex){
        Object returnValue = getPath(fromVertex, toVertex, WeightedGraphReturnType.GET_PATH);
        WeightedEdge[] rettype = (WeightedEdge[]) returnValue;
		return rettype;
    } 

    public boolean addVertex(int v){
        boolean rettype = false;
        if(!vertices.contains(Integer.valueOf(v)))
        {
            vertices.add(Integer.valueOf(v));
            rettype= true;
        }
        return rettype;
    }
    
    public boolean addWeightedEdge(int from, int to, double weight){
        
        for(int i = 0; i < edges.size(); i++){
            if(edges.get(i).getFromVertex() == from && edges.get(i).getToVertex() == to) return false;
        }

        edges.add(new WeightedEdge(from, to, weight));
        return true;
    }

    public String toString(){

        java.lang.StringBuilder e = new java.lang.StringBuilder();
        e = e.append("G = (V, E)\n");
        e = e.append("V = {");

        for(int i=0; i<vertices.size(); i++){

            e = e.append(vertices.get(i));
            if(i!= vertices.size()-1){

                e = e.append(",");

            }
        }

        e = e.append("}\n");
        e = e.append("E = {");

        for(int i=0; i<edges.size(); i++){

            e = e.append(edges.get(i));
            if(i!= edges.size()-1){

                e = e.append(",");

            }
        }

        e = e.append("}");
        return e.toString();

    }

}
