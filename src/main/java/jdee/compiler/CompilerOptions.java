package jdee.compiler;

import java.util.ArrayList;
import java.util.List;

class CompilerOptions {

    public static class StringCode {
        public final String name;
        public final String code;

        public StringCode(String name, String code) {
            this.name = name;
            this.code = code;
        }
    }

    final List<String> filesToCompile = new ArrayList<>();
    StringCode stringCode;
    List<String> annotatedClasses;
    List<String> options;
}
