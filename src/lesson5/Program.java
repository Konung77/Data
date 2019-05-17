package lesson5;

import lesson3.MyStack;

import java.util.NoSuchElementException;

public class Program {
  static MyStack<Integer>[] sticks = new MyStack[3]; // 3 стека - аналог 3 стержней с кольцами
  static int count = 10; // 10 целых чисел - аналог колец на стержнях

  // Циклическое возведение в степень
  public static double exponent (float basis, int degree) {
    double result = 1.0;
    if (degree < 0) throw new NoSuchElementException();
    for (int i = 0; i < degree; i++) {
      result *= basis;
    }
    return result;
  }

  // Рекурсивное возведение в степень
  public static double exponentRec (float basis, int degree) {
    if (degree < 0) throw new NoSuchElementException();
    else if (degree == 0) return 1.0;
    else if (degree%2 == 0) return exponentRec(basis*basis, degree/2);
    else return exponentRec(basis,degree-1)*basis;
  }

  /* Ханойские башни
  У нас есть 3 стека. В первом хранятся целые числа (пусть будет 8), отсортированные по убыванию.
  Внизу стека самое большое, вверху - самое маленькое.
  Задача - переложить их в том же порядке в третий стек. Перекладывать можно только по одному числу.
  Большее число не может располагаться выше меньшего.
  */
  // Метод, реализующий алгоритм. Указываем откуда надо переложить (номер стека), куда (номер) и сколько чисел
  public static void hanoiTowers(int from, int to, int cnt) {
    MyStack<Integer> fromStick = sticks[from];
    MyStack<Integer> toStick = sticks[to];

    if (cnt == 1) {
      int tmp = fromStick.pop();
      if (!toStick.isEmpty() && tmp > (int)toStick.peek()) {
        System.out.println("Нельзя положить большее на меньшее - "+tmp);
        toStick.print();
        return;
      }
      toStick.push(tmp);
    }
    else {
      hanoiTowers(from,3-from-to, cnt-1); // Перекладываем все, кроме нижнего в промежуточный стек
      hanoiTowers(from, to,1); // Перекладываем последний элемент куда надо
      hanoiTowers(3-from-to, to,cnt-1); // Перекладываем все с промежуточного стека в нужный
    }
  }

  public static void main(String[] args) {
    sticks[0] = new MyStack<>();
    sticks[1] = new MyStack<>();
    sticks[2] = new MyStack<>();

    // Заполняем первый стек числами от большего к меньшему
    for (int i = 0; i < count; i++) {
      sticks[0].push(count-i);
    }
    // Выводим стеки на экран перед началом работы алгоритма
    sticks[0].print();
    sticks[1].print();
    sticks[2].print();
    // Запускаем алгоритм
    hanoiTowers(0,2, count);
    // Смотрим, что получилось в итоге
    sticks[0].print();
    sticks[1].print();
    sticks[2].print();
  }
}
