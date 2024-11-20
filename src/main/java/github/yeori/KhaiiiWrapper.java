package github.yeori;

import java.util.Arrays;
import kr.bydelta.koala.khaiii.Khaiii;
import kr.bydelta.koala.khaiii.KhaiiiWord;

public class KhaiiiWrapper {

    private Khaiii khaiii;
    private String resourceDir;

    public KhaiiiWrapper(String resourceDir) {
        this.resourceDir = resourceDir;
        openKhaiii();
    }

    private void openKhaiii() {
        try {
            khaiii = new Khaiii(resourceDir);
            khaiii.open();
        } catch (Exception e) {
            throw new KhaiiiException("fail to open Khaiii", e);
        }
    }

    public KhaiiiResponse analyze(String text) {
        try {
            int[] bits = CharPos.range(text);
            KhaiiiWord word = khaiii.analyze(text);
            System.out.println(Arrays.toString(bits));
            return new KhaiiiResponse(word, bits);
        } catch (Throwable e) {
            throw new KhaiiiException("fail to analyze", e);
        }
    }
}
