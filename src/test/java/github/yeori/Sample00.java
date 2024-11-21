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
    String libkhaiiiPath = "/home/user/khaiii-jconnector/khaii-bin/lib";
    System.setProperty("jna.library.path", libkhaiiiPath);
    String resourceDir = "/home/user/khaiii-jconnector/khaii-bin/files";

    KhaiiiWrapper kw = new KhaiiiWrapper(resourceDir);
    KhaiiiResponse res =
        kw.analyze("아인슈타인의 상대성이론은 화장실에서도 유효하다.\n밖에서 기다릴 때는 시간이 느리게 흐르지만 안에서는 시간 가는 줄 모른다.");

    System.out.println("[JSON]");
    String json = Formatters.formatJsonText(res);
    System.out.println(json);

    System.out.println("[Plain]");
    System.out.println(Formatters.formatPlainText(res));

    System.out.printf("%d millis\n", res.elapsedMillis);
  }
}
