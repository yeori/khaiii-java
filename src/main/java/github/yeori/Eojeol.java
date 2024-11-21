package github.yeori;

import kr.bydelta.koala.khaiii.KhaiiiMorph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Eojeol implements Iterable<Morpheme>, Iterator<Morpheme> {

  final String text;
  final Integer begin;
  final Integer end;
  public final boolean last;
  private KhaiiiMorph morph;
  final TextInput input;
  private int offsetDelta = 0;

  Eojeol(String text, int begin, int end, boolean last, KhaiiiMorph morph, TextInput input) {
    this.text = text;
    this.begin = begin;
    this.end = end;
    this.last = last;
    this.morph = morph;
    this.input = input;
  }

  public Integer begin() {
    return this.begin;
  }

  public Integer end() {
    return this.end;
  }

  public String getText() {
    return this.text;
  }

  private Morpheme buildMorpheme() {
    String text = morph.lex;
    try {
      int begin = this.begin + this.offsetDelta;
      int end = begin + text.length();
      return new Morpheme(text, morph.tag, begin, end, morph.next == null);
    } finally {
      offsetDelta += text.length();
    }
  }

  @Override
  public Iterator<Morpheme> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    return morph != null;
  }

  @Override
  public Morpheme next() {
    if (!hasNext())
      throw new NoSuchElementException("No more morpheme");
    try {
      return buildMorpheme();
    } finally {
      morph = morph.next;
    }
  }
}
