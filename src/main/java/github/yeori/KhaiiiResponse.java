package github.yeori;

import kr.bydelta.koala.khaiii.KhaiiiWord;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a response from the Khaiii morphological analyzer. It provides an iterator
 * over the Eojeols in the response.
 *
 * Example usage:
 * 
 * <pre>
 * KhaiiiWord word = ...; // Get the first word from the Khaiii response
 * TextInput input = ...; // Get the input text
 * KhaiiiResponse response = new KhaiiiResponse(word, input);
 *
 * for (Eojeol eojeol : response) {
 *     System.out.println(eojeol.getText());
 * }
 * </pre>
 *
 * @see Eojeol
 * @see KhaiiiWord
 * @see TextInput
 */
public class KhaiiiResponse implements Iterable<Eojeol>, Iterator<Eojeol> {
  final private KhaiiiWord head;
  private KhaiiiWord word;
  private Eojeol current;
  final private TextInput input;
  final public long elapsedMillis;

  public KhaiiiResponse(KhaiiiWord word, TextInput input, long elapsedMillis) {
    this.head = word;
    this.input = input;
    this.elapsedMillis = elapsedMillis;
    this.reset();
  }

  private Eojeol buildEojeol() {
    if (word == null) {
      return null;
    }
    int begin = input.trace(word.begin);
    int end = input.trace(word.begin + word.length);
    String text = input.getTextAt(begin, end);
    return new Eojeol(text, begin, end, word.next == null, word.morphs, input);
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Eojeol next() {
    if (!hasNext()) {
      throw new NoSuchElementException("end of eojeol");
    }
    try {
      return this.current;
    } finally {
      word = word.next;
      current = buildEojeol();
    }
  }

  @Override
  public Iterator<Eojeol> iterator() {
    return this;
  }

  public void reset() {
    this.word = head;
    this.input.reset();
    this.current = buildEojeol();
  }
}

