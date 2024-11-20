package github.yeori;

import kr.bydelta.koala.khaiii.KhaiiiWord;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KhaiiiResponse implements Iterable<Phrase>, Iterator<Phrase> {
    private Phrase current;

    public KhaiiiResponse(KhaiiiWord word, int[] bits) {
        this.current = word == null ? null : new Phrase(word, 0, bits);
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public Phrase next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        try {
            return this.current;
        } finally {
            current = current.word.next != null ? new Phrase(current.word.next, current.cursor, current.ranges )
                    : null;
        }
    }

    @Override
    public Iterator<Phrase> iterator() {
        return this;
    }
}

