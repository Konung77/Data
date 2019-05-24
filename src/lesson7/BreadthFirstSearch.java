package lesson7;

import java.util.LinkedList;

public class BreadthFirstSearch {
  private boolean[] marked;
  private int[] edgeTo;
  private int[] distTo;
  private int start; // Вершина, с которой начинается обход графа
  private int vCount;

  // Конструктор инициализирует все таблицы и запускает метод обхода графа
  public BreadthFirstSearch (MyGraph graph, int start) {
    if (start < 0 || start > graph.vertexCount()-1)
      throw new IllegalArgumentException("Задана несуществующая вершина графа");
    this.start = start;
    this.vCount = graph.vertexCount();
    marked = new boolean[vCount];
    edgeTo = new int[vCount];
    distTo = new int[vCount];
    for (int i = 0; i < vCount; i++) {
      distTo[i] = -1; // Инициализация таблицы расстояний. -1 значит, что маршрута нет
    }
    bfs(graph, start);
  }

  // Основной метод обхода графа в ширину
  private void bfs (MyGraph graph, int start) {
    int current;
    LinkedList<Integer> queue = new LinkedList<>();
    queue.addLast(start);
    marked[start] = true;
    distTo[start] = 0;
    while (!queue.isEmpty()) {
      current = queue.removeFirst();
      for (int i: graph.adjList(current)) {
        if (!marked[i]) {
          marked[i] = true;
          edgeTo[i] = current;
          distTo[i] = distTo[current] + 1;
          queue.addLast(i);
        }
      }
    }
  }

  // Проверка существования пути от начальной вершины до указанной
  private boolean hasPath (int vertex) {
    if (vertex < 0 || vertex > vCount-1)
      throw new IllegalArgumentException("Задана несуществующая вершина графа");
    return marked[vertex];
  }

  // Метод возвращает расстояние (количество ребер) от начальной вершины графа до указанной
  public int distTo (int vertex) {
    if (vertex < 0 || vertex > vCount-1)
      throw new IllegalArgumentException("Задана несуществующая вершина графа");
    return distTo[vertex];
  }

  // Метод возвращает путь от начальной вершины графа до указанной (если он существует)
  public LinkedList<Integer> pathTo (int vertex) {
    if (!hasPath(vertex)) return null;
    LinkedList<Integer> stack = new LinkedList<>();
    int cursor = vertex;
    while (cursor != start) {
      stack.push(cursor);
      cursor = edgeTo[cursor];
    }
    return stack;
  }
}
