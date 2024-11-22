package github.yeori;

import kr.bydelta.koala.khaiii.KhaiiiWord;
import java.util.ArrayList;
import java.util.List;
import github.yeori.formater.Formatters;

/**
 * Test Program
 *
 */
public class Sample00 {
  static List<KhaiiiWord> toList(KhaiiiWord word) {
    KhaiiiWord ref = word;
    ArrayList<KhaiiiWord> words = new ArrayList<>();
    while (ref != null) {
      words.add(ref);
      ref = word.next;
    }
    return words;
  }

  public static void main(String[] args) {
    String libraryDir = "/home/user/khaiii-jconnector/khaii-bin/lib";
    String resourceDir = "/home/user/khaiii-jconnector/khaii-bin/files";

    KhaiiiWrapper kw = new KhaiiiWrapper(resourceDir, libraryDir);
    KhaiiiResponse res = kw.analyze("나라면 주저없이 삼양라면을 선택하겠다.");

    System.out.println("[JSON]");
    String json = Formatters.formatJsonText(res);
    System.out.println(json);

    System.out.println("[Plain]");
    System.out.println(Formatters.formatPlainText(res));

    System.out.printf("%d millis\n", res.elapsedMillis);
  }
}
