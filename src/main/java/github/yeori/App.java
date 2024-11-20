package github.yeori;

import kr.bydelta.koala.khaiii.Khaiii;
import kr.bydelta.koala.khaiii.KhaiiiMorph.ByReference;
import kr.bydelta.koala.khaiii.KhaiiiWord;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
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
        // System.setProperty("jna.library.path", libkhaiiiPath);
        // KhaiiiLibrary cls = Native.load("libkhaiii.so", KhaiiiLibrary.class);
        // System.out.println(cls.khaiii_version());

        // NativeLibrary.getInstance("khaiii", App.class.getClassLoader());

        // String libkhaiiiPath = "/home/user/khaiii-jconnector/khaii-bin/lib";
        // System.setProperty("jna.platform.library.path", libkhaiiiPath);
        System.setProperty("jna.library.path", libkhaiiiPath);
        String resourceDir = "/home/user/khaiii-jconnector/khaii-bin/files";

        KhaiiiWrapper kw = new KhaiiiWrapper(resourceDir);
        KhaiiiResponse res = kw.analyze("오늘은 kotlin때문에 고생을 했지만 그래도 날씨가 덜 추워서 살만하다.");
        for (Phrase phrase : res) {
            System.out.printf("%d, %d\n", phrase.begin(), phrase.end());
        }
        try {
            // var kai = new Khaiii(resourceDir);
            // System.out.println(kai.version());
            // KhaiiiWord word = kai.analyze("반갑습니다.");
            // List<KhaiiiWord> words = toList(word);

            // words.forEach((wd) -> {
            // ByReference mpm = wd.morphs;
            // while (mpm != null) {
            // String surpface = mpm.lex;
            // String pos = mpm.tag;
            // System.out.println(surpface + " " + pos);
            // mpm = mpm.next;
            // }
            // });
        } catch (

        UnsatisfiedLinkError e) {
            System.err.println("Error loading native library: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
