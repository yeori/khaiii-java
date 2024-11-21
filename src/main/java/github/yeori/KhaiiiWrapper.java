package github.yeori;

import kr.bydelta.koala.khaiii.Khaiii;
import kr.bydelta.koala.khaiii.KhaiiiWord;

public class KhaiiiWrapper {

  private Khaiii khaiii;
  private String resourceDir;

  /**
   * Create a new KhaiiiWrapper instance.
   *
   * @param resourceDir The directory containing the Khaiii resources
   */
  public KhaiiiWrapper(String resourceDir) {
    this.resourceDir = resourceDir;
    openKhaiii();
  }

  private void openKhaiii() {
    try {
      khaiii = new Khaiii(resourceDir);
      khaiii.open();
    } catch (Exception e) {
      throw new KhaiiiException("fail to open Khaiii", e);
    }
  }

  /**
   * Analyze the given text using Khaiii.
   *
   * @param text
   * @return KhaiiiResponse containing the analyzed eojeols
   */
  public KhaiiiResponse analyze(String text) {
    try {
      long t = System.nanoTime();
      KhaiiiWord word = khaiii.analyze(text);
      t = (System.nanoTime() - t) / 1_000_000;
      return new KhaiiiResponse(word, new TextInput(text), t);
    } catch (Throwable e) {
      throw new KhaiiiException("fail to analyze", e);
    }
  }
}
