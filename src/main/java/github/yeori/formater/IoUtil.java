package github.yeori.formater;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import github.yeori.KhaiiiException;

public class IoUtil {
  private final static String QUOT = "\"";
  private final static String UTF8 = "utf-8";
  private final static int DEFAULT_BUF_SIZE = 16 * 1024;

  static void close(Writer out) {
    try {
      out.close();
    } catch (IOException e) {
      throw new KhaiiiException("fail to close writer", e);
    }
  }

  static void write(Writer out, String value, String ... more) {
    try {
      out.write(value);
      for (String str : more) {
        out.write(str);
      }
    } catch (IOException e) {
      throw new KhaiiiException("fail to write [" + value + "]", e);
    }
  }

  static void flush(Writer out) {
    try {
      out.flush();
    } catch (IOException e) {
      throw new KhaiiiException("fail to flush writer", e);
    }
  }

  static void jsonString(Writer out, String prop) {
    write(out, QUOT + prop + QUOT);
  }

  /**
   * 주어진 스트림을 감싸는 BufferedWriter를 생성합니다.
   * 
   * @param out
   * @param encoding
   * @param bufferSize
   * @return
   */
  public static BufferedWriter toStream(OutputStream out, String encoding, int bufferSize) {
    if (bufferSize <= 0) {
      bufferSize = DEFAULT_BUF_SIZE;
    }
    Charset charset = Charset.forName(encoding);
    return new BufferedWriter(new OutputStreamWriter(out, charset), bufferSize);
  }

  /**
   * 주어진 스트림을 감싸는 BufferedWriter를 생성합니다.
   * 
   * @param out
   * @return
   */
  public static BufferedWriter toStream(OutputStream out) {
    return toStream(out, UTF8, DEFAULT_BUF_SIZE);
  }

  /**
   * 주어진 파일로 출력하는 BufferedWriter를 생성합니다.
   * 
   * @param file
   * @param encoding
   * @param bufferSize
   * @return
   */
  public static BufferedWriter toFile(File file, String encoding, int bufferSize) {
    try {
      FileOutputStream fout = new FileOutputStream(file);
      return toStream(fout, encoding, bufferSize);
    } catch (FileNotFoundException fnf) {
      throw new KhaiiiException("file not found", fnf);
    }
  }

  /**
   * 주어진 파일로 출력하는 BufferedWriter를 생성합니다.
   * 
   * @param file
   * @return BufferedWriter("utf-8", 16KB 버퍼)
   */
  public static BufferedWriter toFile(File file) {
    return toFile(file, UTF8, DEFAULT_BUF_SIZE);
  }
}
