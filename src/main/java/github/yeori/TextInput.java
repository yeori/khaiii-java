package github.yeori;

public class TextInput {
  final private String input;
  final private int[] bits;
  private int cursor;

  public TextInput(String input) {
    this.input = input;
    this.bits = CharPos.range(input);
    this.cursor = 0;
  }

  public String getInput() {
    return this.input;
  }

  public int[] getBits() {
    return this.bits;
  }

  public int trace(Integer pos) {
    for (int i = cursor; i < bits.length; i++) {
      if (pos == bits[i]) {
        cursor = i;
        return i;
      }
    }
    return bits.length;
  }

  public String getTextAt(Integer begin, Integer end) {
    return this.input.substring(begin, end);
  }

  void reset() {
    cursor = 0;
  
  }

}
