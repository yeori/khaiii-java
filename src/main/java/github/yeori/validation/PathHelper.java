package github.yeori.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import github.yeori.KhaiiiException;

/*
 * cnv2hdn.lin config.json conv.2.fil conv.3.fil conv.4.fil conv.5.fil embed.bin errpatch.len
 * errpatch.tri errpatch.val hdn2tag.lin preanal.tri preanal.val restore.key restore.one restore.val
 */
public class PathHelper {
  private static void checkDir(File f, boolean shouldBeFile) {
    String target = shouldBeFile ? "File" : "Directory";
    if (!f.exists()) {
      throw new KhaiiiException("[" + f.getAbsolutePath() + "] does not exist");
    }
    boolean isFile = f.isFile();
    if (isFile != shouldBeFile) {
      throw new KhaiiiException("[" + f.getAbsolutePath() + "] is not " + target);
    }
  }

  private static void checkAbsolutePath(String dir) {
    if (!dir.startsWith("/")) {
      throw new KhaiiiException("use absolute path", null);
    }
  }

  public static void installLibraryPath(String libDir) {
    checkAbsolutePath(libDir);
    File dir = new File(libDir);
    checkDir(dir, false);

    File libFile = new File(dir, "libkhaiii.so");
    checkDir(libFile, true);

    System.setProperty("jna.library.path", libDir);
  }

  public static void validateResourcePath(String resourceDir) {
    checkAbsolutePath(resourceDir);
    File dir = new File(resourceDir);
    checkDir(dir, false);

    String[] files =
        "cnv2hdn.lin,config.json,conv.2.fil,conv.3.fil,conv.4.fil,conv.5.fil,embed.bin,errpatch.len,errpatch.tri,errpatch.val,hdn2tag.lin,preanal.tri,preanal.val,restore.key,restore.one,restore.val"
            .split(",");
    List<String> noutFound = new ArrayList<>();
    for (String fname : files) {
      File f = new File(dir, fname);
      if (!f.exists()) {
        noutFound.add(fname);
      }
    }
    if (noutFound.size() > 0) {
      throw new KhaiiiException("missing resource files: [" + String.join(", ", noutFound) + "]");
    }

  }
}
