package jdee.compiler;

import java.util.ArrayList;
import java.util.List;

class CompilerOptions {
    final List<String> filesToCompile = new ArrayList<>();
    List<String> annotatedClasses;
    List<String> options;
}
