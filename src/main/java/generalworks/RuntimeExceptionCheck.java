package generalworks;

public class RuntimeExceptionCheck {

  public void thrower() {
    System.out.println("throwing Runtime Exception");
    throw new RuntimeException("Thrown");
  }

  public static void main(String[] args) {
    System.out.println("line 1");
    RuntimeExceptionCheck check = new RuntimeExceptionCheck();
    check.thrower();
    System.out.println("line 2");
  }
}
