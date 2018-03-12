package io.github.haohaozaici.bilibiliapppic.network;

/**
 * Created by haohao on 2017/11/9.
 */

public class MyException extends Exception {

  private Throwable throwable;
  private String message;


  public MyException() {
    super();
  }


  public MyException(Throwable throwable, String message) {
    this.throwable = throwable;
    this.message = message;
  }


  public Throwable getThrowable() {
    return throwable;
  }


  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
  }


  public String getMessage() {
    return message;
  }


  public void setMessage(String message) {
    this.message = message;
  }
}
