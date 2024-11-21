package github.yeori.formater;

import java.io.Writer;
import github.yeori.Eojeol;
import github.yeori.KhaiiiResponse;
import github.yeori.Morpheme;

/**
 * It prints response to json text.
 */
public class JsonFormatter implements IFormatter {
  final private static String L_BLOCK = "{";
  final private static String R_BLOCK = "}";
  final private static String L_ARR = "[";
  final private static String R_ARR = "]";
  final private static String COMMA = ",";
  final private static String COLON = ":";
  final private static String[] BEGIN = "begin,B".split(",");
  final private static String[] END = "end,E".split(",");
  final private static String[] TEXT = "text,T".split(",");
  final private static String[] TAG = "tag,G".split(",");
  final private static String[] MORPHS = "morphs,M".split(",");


  private Writer out;
  private boolean close;
  private int idx = 0;

  public JsonFormatter(Writer out, boolean close, boolean minified) {
    this.out = out;
    this.close = close;
    this.idx = minified ? 1 : 0;
  }

  public JsonFormatter(Writer out, boolean close) {
    this(out, close, false);
  }

  /**
   * write response to json format.
   */
  @Override
  public void format(KhaiiiResponse res) {
    array(() -> {
      for (Eojeol ej : res) {
        eojeol(ej);
        if (!ej.last) {
          IoUtil.write(out, COMMA);
        }
      }
    }).run();
    IoUtil.flush(out);
    if (close) {
      IoUtil.close(out);
    }
  }

  private void eojeol(Eojeol ej) {
    block(() -> {
      entry(BEGIN[idx], ej.begin(), true);
      entry(END[idx], ej.end(), true);
      entry(TEXT[idx], ej.getText(), true);
      entry(MORPHS[idx], array(() -> {
        for (Morpheme morph : ej) {
          morpheme(morph);
          if (!morph.last) {
            IoUtil.write(out, COMMA);
          }
        }
      }), false);
    });
  }

  private void morpheme(Morpheme morph) {
    block(() -> {
      entry(BEGIN[idx], morph.begin(), true);
      entry(END[idx], morph.end(), true);
      entry(TEXT[idx], morph.getText(), true);
      entry(TAG[idx], morph.getTag(), false);
    });
  }

  private void entry(String prop, Runnable value, boolean comma) {
    IoUtil.jsonString(out, prop);
    IoUtil.write(out, COLON);
    value.run();
    if (comma) {
      IoUtil.write(out, COMMA);
    }
  }

  private void entry(String prop, Object value, boolean comma) {
    IoUtil.jsonString(out, prop);
    IoUtil.write(out, COLON);
    String normalized = null;
    if (CharSequence.class.isAssignableFrom(value.getClass())) {
      normalized = "\"" + value.toString() + "\"";
    } else {
      normalized = value.toString();
    }
    IoUtil.write(out, normalized);
    if (comma) {
      IoUtil.write(out, COMMA);
    }
  }

  private Runnable array(Runnable body) {
    return () -> {
      IoUtil.write(out, L_ARR);
      body.run();
      IoUtil.write(out, R_ARR);
    };

  }

  private void block(Runnable body) {
    IoUtil.write(out, L_BLOCK);
    body.run();
    IoUtil.write(out, R_BLOCK);
  }
}
