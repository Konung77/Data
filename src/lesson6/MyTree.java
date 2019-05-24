package lesson6;

import java.util.NoSuchElementException;

public class MyTree<Key extends Comparable<Key>, Value> {
  // Вспомогательный класс - узел дерева
  private class Node {
    Key key;
    Value value;
    Node left;
    Node right;
    int size;
    int height;
    public Node (Key key, Value value, int size, int height) {
      this.key = key;
      this.value = value;
      this.size = size;
      this.height = height;
    }
  }

  // Переменная, хранящая ссылку на корень дерева
  private Node root = null;

  // Вспомогательный метод для вычисления размера дерева с заданным корнем
  private int size (Node node) {
    if (node == null) return 0;
    return node.size;
  }

  // Вспомогательный метод для вычисления высоты дерева с заданным корнем
  private int height (Node node) {
    if (node == null) return 0;
    return node.height;
  }

  // Вспомогательный метод дл проверки сбалансированности заданного узла
  // (дерево сбалансировано, если для каждого его узла высота его (узла) правого и
  // левого поддеревьев отличается не более, чем на 1)
  // Пустое дерево и дерево из одного узла сбалансированы
  private boolean isBalanced (Node node) {
    if (node == null) return true;
    if (Math.abs(height(node.left)-height(node.right)) > 1) return false;
    return isBalanced(node.left) && isBalanced(node.right);
  }

  // Вспомогательный метод для добавления/изменения узла дерева
  private Node put (Node node, Key key, Value value) {
    if (key == null) throw new NoSuchElementException("Must be not null key");
    if (node == null) return new Node(key, value, 1, 0);
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      node.value = value;
      return node;
    }
    if (cmp < 0) node.left = put(node.left, key, value);
    else node.right = put(node.right, key, value);
    node.size = size(node.left) + size(node.right) + 1;
    node.height = Math.max(height(node.left),height(node.right)) + 1;
    return node;
  }

  // Вспомогательный метод для получения значения узла дерева
  private Value get (Node node, Key key) {
    if (key == null) throw new NoSuchElementException("Must be not null key");
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0) return node.value;
    if (cmp < 0) return get(node.left, key);
    else return get(node.right, key);
  }

  // Вспомогательный метод поиска узла с максимальным ключом, начиная с заданного узла
  private Node max (Node node) {
    if (node == null) return null;
    if (node.right == null) return node;
    return max(node.right);
  }

  // Вспомогательный метод удаления узла дерева с наибольшим ключом
  // (возвращает ссылку на его левое поддерево)
  private Node removeMax (Node node) {
    if (node.right == null) return node.left;
    node.right = removeMax(node.right);
    return node;
  }

  // Вспомогательный метод удаления узла с заданным ключом
  // из дерева с заданным корнем
  private Node remove (Node node, Key key) {
    if (key == null) throw new NoSuchElementException("Key can't be empty");
    if (node == null) return null;
    int cmp = key.compareTo(node.key);
    if (cmp == 0) {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      // если у узла есть оба поддерева
      Node tmp = node;
      node = max(node.left);
      node.left = removeMax(tmp.left);
      node.right = tmp.right;
      tmp = null;
    }
    else if (cmp < 0) node.left = remove(node.left, key);
    else node.right = remove(node.right, key);
    node.size = size(node.left) + size(node.right) + 1;
    if (node.size == 1) node.height = 0;
    else node.height = Math.max(height(node.left),height(node.right)) + 1;
    return node;
  }

  // Проверка, пустое ли дерево
  public boolean isEmpty() {
    return root == null;
  }

  // Проверка сбалансированности дерева
  public boolean isBalanced() {
    return isBalanced(root);
  }

  // Вычисление размера дерева
  public int size () {
    return size(root);
  }

  // Вычисление высоты дерева
  public int height () {
    return height(root);
  }

  // Добавление нового или замена существующего узла дерева
  public void put (Key key, Value value) {
    root = put(root, key, value);
  }

  // Получение значения узла дерева по его ключу
  public Value get (Key key) {
    return get(root, key);
  }

  // Удаление узла дерева по заданному ключу
  public void remove (Key key) {
    root = remove(root, key);
  }
}
