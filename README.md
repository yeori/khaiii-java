# Khaiii Java Wrapper

## 0. 배경

C++로 작성된 Khaiii([https://github.com/kakao/khaiii](https://github.com/kakao/khaiii))를 java 또는 kotlin에서 사용할 수 있는 인터페이스 구현체가 이미 존재합니다.

> [Koalanlp-khaiii](https://github.com/koalanlp/koalanlp/blob/master/khaiii/index.md)

```xml
<dependency>
    <groupId>kr.bydelta</groupId>
    <artifactId>koalanlp-khaiii</artifactId>
    <version>2.1.4</version>
</dependency>
```

하지만 maven 중앙 저장소에 배포된 2.1.4 버전은 linux 환경에서 khaiii 라이브러리를 로드하지 못합니다. 버그 수정판은 현재 2.1.5-SNAPSHOT이지만 중앙 저장소에 배포되지 않은 상태입니다.

게다가 프로젝트는 3년 전을 마지막으로 더이상의 유지보수는 없어서 부득이하게 버그를 수정한 jar 파일을 같이 첨부합니다.

추가로, 프로그래밍 전개에 필요한 기능을 덧붙입니다.

## 1. 설치 준비

형태소 분석기 khaiii를 실행하기 위해서는 최종적으로 다음의 파일들이 필요합니다.

#### 1) libkhaiii.so

형태소 분석기 구현체인 공유 라이브러리입니다.

[Khaiii](https://github.com/kakao/khaiii) 저장소에서 소스코드를 내려받은 후 직접 빌드하거나 `lib/linux` 디렉토리의 파일을 사용합니다.. 빌드 방법은 [Khaiii](https://github.com/kakao/khaiii)에서 확인하실 수 있습니다.


#### 2) khaiii resource files

1)에서 성공적으로 build를 했다면 아래의 파일들이 생성됩니다.

```
cnv2hdn.lin
config.json
conv.2.fil
conv.3.fil
conv.4.fil
conv.5.fil
embed.bin
errpatch.len
errpatch.tri
errpatch.val
hdn2tag.lin
preanal.tri
preanal.val
restore.key
restore.one
restore.val
```

#### 3) koalanlp-khaiii:2.1.5.jar

Maven 중앙 저장소의 2.1.4 버전을 수정한 custom jar 입니다.

`lib/koalanlp-khaiii-2.1.5.jar`에 있습니다.


## 2. 설치

pom.xml 에서는 중앙 저장소에 존재하지 않는 `2.1.5`버전을 사용합니다.

```xml
<project ..>
  <dependencies>
    <dependency>
      <groupId>kr.bydelta</groupId>
      <artifactId>koalanlp-khaiii</artifactId>
      <version>2.1.5</version>
    </dependency>
  </dependencies>
</project>
```
로컬 저장소에 `koalanlp-khaiii-2.1.5.jar`를 생성해야 합니다.

```bash
$ mvn install:install-file \
    -Dfile=koalanlp-khaiii-2.1.5.jar \
    -DgroupId=kr.bydelta \
    -DartifactId=koalanlp-khaiii \
    -Dversion=2.1.5 \
    -Dpackaging=jar
```

## 3. 실행

테스트코드 참고

```java
public static void main(String[] args) {
    String libraryDir = "/home/user/khaiii-jconnector/khaii-bin/lib";
    String resourceDir = "/home/user/khaiii-jconnector/khaii-bin/files";

    KhaiiiWrapper kw = new KhaiiiWrapper(resourceDir, libraryDir);
    KhaiiiResponse res =
        kw.analyze("나라면 주저없이 삼양라면을 선택하겠다.");

    System.out.println("[JSON]");
    String json = Formatters.formatJsonText(res);
    System.out.println(json);

    System.out.println("[Plain]");
    System.out.println(Formatters.formatPlainText(res));

    System.out.printf("%d millis\n", res.elapsedMillis);
  }
```
```
[JSON]
[{"begin":0,"end":3,"text":"나라면","morphs":[{"begin":0,"end":1,"text":"나","tag":"NP"},{"begin":1,"end":2,"text":"이","tag":"VCP"},{"begin":2,"end":4,"text":"라면","tag":"EC"}]},{"begin":4,"end":8,"text":"주저없이","morphs":[{"begin":4,"end":5,"text":"주","tag":"MAG"},{"begin":5,"end":6,"text":"저","tag":"NNG"},{"begin":6,"end":8,"text":"없이","tag":"MAG"}]},{"begin":9,"end":14,"text":"삼양라면을","morphs":[{"begin":9,"end":13,"text":"삼양라면","tag":"NNP"},{"begin":13,"end":14,"text":"을","tag":"JKO"}]},{"begin":15,"end":21,"text":"선택하겠다.","morphs":[{"begin":15,"end":17,"text":"선택","tag":"NNG"},{"begin":17,"end":18,"text":"하","tag":"XSV"},{"begin":18,"end":19,"text":"겠","tag":"EP"},{"begin":19,"end":20,"text":"다","tag":"EF"},{"begin":20,"end":21,"text":".","tag":"SF"}]}]
[Plain]
나라면  나/NP+이/VCP+라면/EC
주저없이        주/MAG+저/NNG+없이/MAG
삼양라면을      삼양라면/NNP+을/JKO
선택하겠다.     선택/NNG+하/XSV+겠/EP+다/EF+./SF

36 millis
```