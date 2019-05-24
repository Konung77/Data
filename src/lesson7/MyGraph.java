package lesson7;

import java.util.LinkedList;

public class MyGraph {
  private int vCount; // Число вершин графа
  private int eCount; // Число ребер графа
  private LinkedList<Integer>[] adjLists; // Списки смежности вершин графа

  public MyGraph (int vCount) {
    if (vCount < 0)
      throw new IllegalArgumentException("Граф не может иметь отрицательное число вершин");
    this.vCount = vCount;
    adjLists = new LinkedList[vCount];
    for (int i = 0; i < vCount; i++) {
      adjLists[i] = new LinkedList<>();
    }
  }

  public int vertexCount() {
    return vCount;
  }

  // Метод добавляет новое ребра графа
  public void addEdge (int v1, int v2) {
    if (v1 < 0 || v2 < 0 || v1 > vCount-1 || v2 > vCount-1)
      throw new IllegalArgumentException("Некорректный номер вершины");
    // Если такое ребро уже есть, то не увеличиваем число ребер
    if (!adjLists[v1].contains(v2)) {
      adjLists[v1].add(v2);
      adjLists[v2].add(v1);
      eCount++;
    }
  }

  // Метод возвращает таблицу смежности заданной вершины графа
  public LinkedList<Integer> adjList (int vertex) {
    if (vertex < 0 || vertex > vCount-1)
      throw new IllegalArgumentException("Некорректный номер вершины");
    return adjLists[vertex];
  }
}
