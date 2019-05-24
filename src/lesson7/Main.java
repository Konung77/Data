package lesson7;

public class Main {
  static MyGraph graph = new MyGraph(10);

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      int a = (int)(Math.random()*10);
      int b = (int)(Math.random()*10);
      graph.addEdge(a, b);
    }
    BreadthFirstSearch bfs = new BreadthFirstSearch(graph,0);
    for (int i = 1; i < 10; i++) {
      System.out.println("Путь от 0 до "+i+": "+bfs.pathTo(i));
      System.out.println("Расстояние от 0 до "+i+": "+bfs.distTo(i));
      System.out.println();
    }
  }
}
