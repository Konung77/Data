package lesson5;

import java.util.NoSuchElementException;

public class Program {
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
    else if (degree == 1) return basis;
    else return exponentRec(basis,degree-1)*basis;
  }

  public static void main(String[] args) {
    System.out.println(exponent(2,8));
    System.out.println(exponentRec(2,8));
  }
}
