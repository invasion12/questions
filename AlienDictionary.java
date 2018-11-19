import java.util.*; 
  
class Graph 
{ 
    
    private final List<List<Integer>> adjacencyList;
  
    Graph(int nVertices) 
    { 
        adjacencyList = new ArrayList<>();
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) 
            adjacencyList.add(vertexIndex, new ArrayList<Integer>());
    } 
  
    void addEdge(int startVertex, int endVertex) 
    { 
        adjacencyList.get(startVertex).add(endVertex);  
    } 
  
    private int getNoOfVertices() 
    { 
        return adjacencyList.size(); 
    } 
  
    private void topologicalSortUtil(int currentVertex, boolean[] visited, 
                                     Stack<Integer> stack) 
    { 
        visited[currentVertex] = true; 
  
        for (int adjacentVertex : adjacencyList.get(currentVertex))
        { 
            if (!visited[adjacentVertex]) 
            { 
                topologicalSortUtil(adjacentVertex, visited, stack); 
            } 
        } 
  
        stack.push(currentVertex); 
    } 
  
    void topologicalSort() 
    { 
        Stack<Integer> stack = new Stack<>(); 
  
        boolean[] visited = new boolean[getNoOfVertices()]; 
        for (int i = 0; i < getNoOfVertices(); i++) 
        { 
            visited[i] = false; 
        } 
  
        // Call the recursive helper function to store Topological  
        // Sort starting from all vertices one by one 
        for (int i = 0; i < getNoOfVertices(); i++) 
        { 
            if (!visited[i]) 
            { 
                topologicalSortUtil(i, visited, stack); 
            } 
        } 
  
        // Print contents of stack 
        while (!stack.isEmpty()) 
        { 
            System.out.print((char)('a' + stack.pop()) + " "); 
        } 
    } 
} 
  
public class AlienDictionary 
{ 
    private static void printOrder(String[] words, int alpha) 
    { 
        Graph graph = new Graph(alpha); 
  
        for (int i = 0; i < words.length - 1; i++) 
        { 
            String word1 = words[i]; 
            String word2 = words[i+1]; 
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) 
            { 
                if (word1.charAt(j) != word2.charAt(j)) 
                { 
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j)- 'a'); 
                    break; 
                } 
            } 
        } 
        graph.topologicalSort(); 
    } 
  
    public static void main(String[] args) 
    { 
        String[] words = {"caa", "aaa", "aab"}; 
        printOrder(words, 3); 
    } 
} 
