package jdee.compiler;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

import static java.util.Collections.singletonList;

public class CompilationUnitFromString implements CompilationUnitProvider {

    @Override
    public Iterable<? extends JavaFileObject> findCompilationUnits(
            CompilerOptions options, StandardJavaFileManager fileManager) {
        return singletonList(new JavaCodeFromString(options.stringCode));
    }
}
