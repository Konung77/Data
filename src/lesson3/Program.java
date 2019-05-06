package lesson3;

import java.util.Scanner;

public class Program {
  private static MyStack<Character> stack = new MyStack<>();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    System.out.println("Введите строку:");
    String stroka = scanner.nextLine();
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < stroka.length(); i++) {
      stack.push(stroka.charAt(i));
    }
    int len = stack.length();
    for (int i = 0; i < len; i++) {
      str.append(stack.pop());
    }
    System.out.println("Перевёрнутая строка:");
    System.out.println(str);
  }
}
