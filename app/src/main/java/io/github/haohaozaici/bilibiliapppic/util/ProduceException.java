package io.github.haohaozaici.bilibiliapppic.util;

import io.github.haohaozaici.bilibiliapppic.network.MyException;
import java.io.IOException;

/**
 * Created by haoyuan on 2018/2/5.
 */

public abstract class ProduceException {

  public void exception() throws IOException {
    throw new IOException();
  }


  public void myException() throws MyException {
    throw new MyException();
  }


  public abstract void abstractException() throws MyException, IOException;

}
