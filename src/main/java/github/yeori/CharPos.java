package github.yeori;

import java.util.HashMap;
import java.util.Map;

public class CharPos {
  /**
   * 1bit under the 0x80
   */
  final private static int BIT_ONE = 0x80;
  /**
   * 2 bits under the 0x800
   */
  final private static int BIT_TWO = 0x800;
  /**
   * 3 bits under the 0x10000
   */
  final private static int BIT_3 = 0X10000;
  /**
   * contains byte length from 0 to 0x0FFFF;
   */
  final private static Map<Integer, Integer> RANGE_MAP = new HashMap<>();

  static {
    for (int c = 0; c < BIT_3; c++) {
      if (c < BIT_ONE) {
        RANGE_MAP.put(c, 1);
      } else if (c < BIT_TWO) {
        RANGE_MAP.put(c, 2);
      } else {
        RANGE_MAP.put(c, 3);
      }
    }
  }

  /**
   * return the range of bytes which is utf-8 encoded.
   * <ul>
   * <li>under 0x80: 1 bit</li>
   * <li>under 0x800: 2 bits</li>
   * <li>under 0x10000: 3 bits</li>
   * </ul>
   * 
   * @param source
   * @return array indicating byte rnage of each characters in a source
   */
  static int[] range(String source) {
    int[] range = new int[source.length() + 1];
    int acc = 0;
    for (int i = 0; i < source.length(); i++) {
      int c = source.codePointAt(i);
      Integer len = RANGE_MAP.get((int) c);
      acc += len == null ? 4 : len;
      range[i + 1] = acc;
    }
    return range;
  }
}
