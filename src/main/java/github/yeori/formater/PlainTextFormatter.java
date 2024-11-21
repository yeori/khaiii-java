package github.yeori.formater;

import java.io.Writer;
import github.yeori.Eojeol;
import github.yeori.KhaiiiResponse;
import github.yeori.Morpheme;

/**
 * Default output formatter
 * 
 * <pre>
 * 밖에서  밖/NNG+에서/JKB
 * 기다릴  기다리/VV+ㄹ/ETM
 * 때는    때/NNG+는/JX
 * 시간이  시간/NNG+이/JKS
 * 느리게  느리/VA+게/EC
 * 흐르지만        흐르/VV+지만/EC
 * </pre>
 */
public class PlainTextFormatter implements IFormatter {
  private static final String NL = System.getProperty("line.separator");
  private static final String TAB = "\t";
  private Writer out;
  private boolean closeStream;

  public PlainTextFormatter(Writer out) {
    this(out, false);
  }

  public PlainTextFormatter(Writer out, boolean closeStream) {
    this.out = out;
    this.closeStream = closeStream;
  }

  public void format(KhaiiiResponse response) {
    for (Eojeol ej : response) {
      IoUtil.write(out, ej.getText());
      IoUtil.write(out, TAB);
      for (Morpheme morph : ej) {
        IoUtil.write(out, morph.getText(), "/", morph.getTag());
        if (!morph.last) {
          IoUtil.write(out, "+");
        }
      }
      IoUtil.write(out, NL);
    }
    if (closeStream) {
      IoUtil.close(this.out);
    }
  }
}
