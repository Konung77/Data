package lesson3;

public class MyStack<T> {
  private Object[] stack = new Object[1];
  private int size = 0;

  // Проверка стека на пустоту
  private boolean isEmpty ()
  {
    return size == 0;
  }

  // Изменение размера вспомогательного массива
  private void resize (int capacity)
  {
    Object[] tmp = new Object[capacity];
    for (int i = 0; i < size; i++) {
      tmp[i] = stack[i];
    }
    stack = tmp;
  }

  // Увеличение размера вспомогательного массива
  private void increase ()
  {
    int new_size = stack.length*2;
    resize(new_size);
  }

  // Уменьшение размера вспомогательного массива
  private void decrease ()
  {
    int new_size = stack.length/2;
    resize(new_size);
  }

  // Размер стека (кол-во элементов)
  public int length ()
  {
    return size;
  }

  // Добавление элемента в стек
  public void push (T item)
  {
    if (size == stack.length) increase();
    stack[size++] = item;
  }

  // Извлечение элемента из стека
  public T pop ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Stack is empty");
    T tmp = (T)stack[size-1];
    size--;
    stack[size] = null;
    if ((size == stack.length/4) && (size > 0)) decrease();
    return tmp;
  }

  // Возвращает верхний элемент стека (без его извлечения)
  public T peek ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Stack is empty");
    return (T)stack[size-1];
  }

  // Печать стека
  public void print ()
  {
    if (isEmpty()) throw new IndexOutOfBoundsException("Stack is empty");
    for (int i = 0; i < size-1; i++) {
      System.out.print(stack[i] + ", ");
    }
    System.out.println(stack[size-1]);
  }
}
