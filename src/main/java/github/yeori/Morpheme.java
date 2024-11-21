package github.yeori;

/**
 * Wrapper class for `kr.bydelta.koala.khaiii.KhaiiiMorph`
 */
public class Morpheme {

  private String text;
  private String tag;
  private int begin;
  private int end;
  public final boolean last;

  Morpheme(String text, String tag, int begin, int end, boolean last) {
    this.text = text;
    this.tag = tag;
    this.begin = begin;
    this.end = end;
    this.last = last;
  }

  public String getText() {
    return this.text;
  }

  public String getTag() {
    return this.tag;
  }

  public int begin() {
    return this.begin;
  }

  public int end() {
    return this.end;
  }
}
