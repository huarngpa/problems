# Java Concurrency

See [jenkov.com](http://tutorials.jenkov.com/java-concurrency/index.html) for the original source.

## Threading

Two ways to specify code a thread should run:

### Thread Subclass

```java
// Defined class
public class MyThread extends Thread {
  public void run() {
    // ...
  }
}

Thread myThread = new MyThread();
myThread.start();

// Anonymous class
Thread thread = new Thread() {
  public void run() {
    // ...
  }
}
thread.start();
```

### Runnable Interface

```java
// Given by the standard library
public interface Runnable() {
  public void run();
}

// Implementation
public class MyRunnable implements Runnable {
  public void run() {
    // ...
  }
}

// Anonymous implementation
Runnable myRunnable = new Runnable() {
  public void run() {
    // ...
  }
}

// Lambda
Runnable runnable = () -> { /* Do something */ };

// Starting using a Runnable
Thread thread = new Thread(runnable);
thread.start();
```
