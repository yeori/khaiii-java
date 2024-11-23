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

  /**
   * returns surface
   * 
   * @return
   */
  public String getText() {
    return this.text;
  }

  /**
   * returns tag
   */
  public String getTag() {
    return this.tag;
  }

  /**
   * start offset of surface, inclusive(by character)
   * 
   * @return
   */
  public int begin() {
    return this.begin;
  }

  /**
   * end offset of surface, exclusive (by character)
   * 
   * @return
   */
  public int end() {
    return this.end;
  }
}
