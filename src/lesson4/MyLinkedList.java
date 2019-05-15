package lesson4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable{

  @Override
  public Iterator iterator() {
    return new MyIterator();
  }

  private class MyIterator implements Iterator<T> {
    private Node cursor = first;

    @Override
    public boolean hasNext() {
      return (cursor != null);
    }

    @Override
    public T next() {
      if (!hasNext()) throw new NoSuchElementException("No such element in the list");
      T tmp = cursor.data;
      cursor = cursor.next;
      return tmp;
    }
  }

  private class Node {
    private T data;
    private Node next = null;
    private Node previous = null;

    private Node (T data, Node next, Node previous) {
      this.data = data;
      this.next = next;
      this.previous = previous;
    }
  }

  private Node first = null;
  private Node last = null;
  private int size = 0;

  // Получить индекс элемента, равного заданному
  public int getIndex (T element) {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    int currentIndex = 0;
    Node currentNode = first;
    while (currentIndex < size) {
      if (currentNode.data.equals(element)) return currentIndex;
      currentIndex++;
      currentNode = currentNode.next;
    }
    return -1;
  }

  // Размер списка
  public int size () { return size; }

  // Пуст ли список
  public boolean isEmpty () { return size == 0; }

  // Добавление элемента в начало списка
  public void addFirst (T element) {
    Node node = new Node (element, first, null);
    if (isEmpty()) last = node;
    else first.previous = node;
    first = node;
    size++;
  }

  // Получение данных, хранящихся в первом элементе списка
  public T getFirst () {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    return first.data;
  }

  // Удаление первого элемента списка
  public T removeFirst () {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    T tmp = first.data;
    Node second = first.next;
    first.next = null;
    if (second != null) {
      second.previous = null;
    }
    first = second;
    size--;
    return tmp;
  }

  // Добавление элемента в конец списка
  public void addLast (T element) {
    Node node = new Node (element, null, last);
    if (isEmpty()) first = node;
    else last.next = node;
    last = node;
    size++;
  }

  // Получение данных, хранящихся в последнем элементе списка
  public T getLast () {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    return last.data;
  }

  // Удаление последнего элемента списка
  public T removeLast () {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    T tmp = last.data;
    Node beforeLast = last.previous;
    last.previous = null;
    if (beforeLast != null) {
      beforeLast.next = null;
    }
    last = beforeLast;
    size--;
    return tmp;
  }

  // Получение узла списка с индексом index
  private Node getNode (int index) {
    int currentIndex;
    Node currentNode;

    if (index < 0 || index > size-1) throw new IndexOutOfBoundsException("No such index in the list");
    if (index <= size/2) {
      currentIndex = 0;
      currentNode = first;
      while (currentIndex < index) {
        currentIndex++;
        currentNode = currentNode.next;
      }
    }
    else {
      currentIndex = size - 1;
      currentNode = last;
      while (currentIndex > index) {
        currentIndex--;
        currentNode = currentNode.previous;
      }
    }
      return currentNode;
  }

  // Получение элемента списка с заданным инедексом
  public T get (int index) {
    return getNode(index).data;
  }

  // Элемент с индексом index установить равным element
  public void set (int index, T element) {
    int currentIndex;
    Node currentNode;

    if (index < 0 || index > size-1) throw new IndexOutOfBoundsException("No such index in the list");
    if (index <= size/2) {
      currentIndex = 0;
      currentNode = first;
      while (currentIndex < index) {
        currentIndex++;
        currentNode = currentNode.next;
      }
    }
    else {
      currentIndex = size - 1;
      currentNode = last;
      while (currentIndex > index) {
        currentIndex--;
        currentNode = currentNode.previous;
      }
    }
      currentNode.data = element;
  }

  // Добавление нового элемента в список с индексом index
  public void add (int index, T element) {
    int currentIndex;
    Node currentNode;

    if (index < 0 || index > size) throw new IndexOutOfBoundsException("No such index in the list");
    if (index == 0) addFirst(element);
    else if (index == size) addLast(element);
    else {
      if (index <= size/2) {
        currentIndex = 0;
        currentNode = first;
        while (currentIndex < index) {
          currentIndex++;
          currentNode = currentNode.next;
        }
      }
      else {
        currentIndex = size-1;
        currentNode = last;
        while (currentIndex > index) {
          currentIndex--;
          currentNode = currentNode.previous;
        }
      }
      Node previous = currentNode.previous;
      Node next = currentNode;
      currentNode = new Node(element, next, previous);
      previous.next = currentNode;
      next.previous = currentNode;
      size++;
    }
  }

  // Удаление из списка элемента с заданным индексом
  public T removeByIndex (int index) {
    Node currentNode;
    if (index < 0 || index > size) throw new IndexOutOfBoundsException("No such index in the list");
    if (index == 0) return removeFirst();
    else if (index == size-1) return removeLast();
    else {
      currentNode = getNode(index);
      Node previous = currentNode.previous;
      Node next = currentNode.next;
      if (previous != null) previous.next = next;
      if (next != null) next.previous = previous;
    }
    size--;
    return currentNode.data;
  }

  // Удаление из списка первого элемента, равного данному
  public boolean remove (T element) {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    int index = getIndex(element);
    if (index > -1) {
      removeByIndex(index);
      return true;
    }
    else return false;
  }

  // Преобразование элементов списка в строку
  public String toString () {
    if (isEmpty()) throw new NoSuchElementException("List is empty");
    StringBuffer str = new StringBuffer();
    Node currentNode = first;
    int currentIndex = 0;
    while (currentIndex < size-1) {
      str.append(currentNode.data + ", ");
      currentNode = currentNode.next;
      currentIndex++;
    }
    str.append(currentNode.data);
    return str.toString();
  }
}
