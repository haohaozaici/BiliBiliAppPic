package io.github.haohaozaici.bilibiliapppic;

import java.util.List;
import org.junit.Test;

/**
 * Created by haoyuan on 2018/2/12.
 */

public class ArrayLearning {


  @Test
  public void test() {
    int[] nums = {0, 0, 1, 2, 1, 3, 4, 2};

    System.out.println(removeDuplicates(nums));
  }

  public static int removeDuplicates(int[] nums) {
    int length = nums.length;
    System.out.println("total length: " + length);

    for (int i = 0; i < nums.length; i++) {
      int current = nums[i];
      System.out.println("current: " + current);
      System.out.println("current length: " + length);

      for (int j = 0; j < nums.length; j++) {
        if (i < j && current == nums[j]) {
          length--;
          System.out.println("length--: " + length + ", i=" + i + ", j=" + j);
        }
      }
    }

    return length;
  }

  public List<Integer> removeDuplicatesList(List<Integer> integerList) {

    return null;
  }

  @Test
  public void test2() {


  }

}
