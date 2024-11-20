package github.yeori;

import kr.bydelta.koala.khaiii.KhaiiiWord;

public class Phrase {

    final KhaiiiWord word;
    int cursor;
    final int[] ranges;
    final int begin;
    final int end;

    Phrase(KhaiiiWord word, int cursor, int[] ranges) {
        this.word = word;
        this.cursor = cursor;
        this.ranges = ranges;
        this.begin = trace(this.word.begin);
        this.end = trace(this.word.length + this.word.begin);
    }

    private int trace(int pos) {
        for (int i = cursor; i < ranges.length; i++) {
            if (pos == ranges[i]) {
                cursor = i;
                return i;
            }
        }
        return ranges.length;
    }

    public Integer begin() {
        return this.begin;
    }

    public Integer end() {
        // return this.word.length + this.word.begin;
        return this.end;
    }
}
