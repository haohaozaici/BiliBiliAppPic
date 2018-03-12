package io.github.haohaozaici.bilibiliapppic.util;

import io.github.haohaozaici.bilibiliapppic.network.MyException;
import java.io.IOException;

/**
 * Created by haoyuan on 2018/2/5.
 */

public class ExtendException extends ProduceException {

  @Override
  public void abstractException() throws MyException, IOException {
    throw new IOException();
  }
}
