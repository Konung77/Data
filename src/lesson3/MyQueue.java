package lesson3;

public class MyQueue<T> {
  private Object[] queue = new Object[1];
  private int size = 0;
  private int start = 0;
  private int end = 0;

  // Проверка очереди на пустоту
  private boolean isEmpty ()
  {
    return size == 0;
  }

  // Изменение размера вспомогательного массива
  private void resize (int capacity)
  {
    Object[] tmp = new Object[capacity];
    for (int i = 0; i < size; i++) {
      tmp[i] = queue[(i+start)%queue.length];
    }
    queue = tmp;
    start = 0;
    end = size;
  }

  // Увеличение размера вспомогательного массива
  private void increase ()
  {
    int new_size = queue.length*2;
    resize(new_size);
  }

  // Уменьшение размера вспомогательного массива
  private void decrease ()
  {
    int new_size = queue.length/2;
    resize(new_size);
  }

  // Размер очереди (кол-во элементов)
  public int length ()
  {
    return size;
  }

  // Добавление элемента в конец очереди
  public void enqueue (T item)
  {
    if (size == queue.length) increase();
    queue[end++] = item;
    end %= queue.length;
    size++;
  }

  // Извлечение элемента из начала очереди
  public T dequeue ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Queue is empty");
    T tmp = (T)queue[start];
    size--;
    queue[start++] = null;
    start %= queue.length;
    if ((size == queue.length/4) && (size > 0)) decrease();
    return tmp;
  }

  // Возвращает первый элемент очереди (без извлечения)
  public T peekFront ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Queue is empty");
    return (T)queue[start];
  }

  // Печать очереди
  public void print ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Queue is empty");
    for (int i = 0; i < size-1; i++) {
      System.out.print(queue[(i+start)%queue.length] + ", ");
    }
    System.out.println(queue[end-1]);
  }
}
