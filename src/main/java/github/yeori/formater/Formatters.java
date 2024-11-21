package github.yeori.formater;

import java.io.StringWriter;
import github.yeori.KhaiiiResponse;

public class Formatters {

  public static String formatJsonText(KhaiiiResponse response) {
    response.reset();
    StringWriter sw = new StringWriter();
    new JsonFormatter(sw, false).format(response);
    return sw.toString();
  }

  public static char[] formatPlainText(KhaiiiResponse response) {
    response.reset();
    StringWriter sw = new StringWriter();
    new PlainTextFormatter(sw).format(response);
    return sw.toString().toCharArray();
  }
}
