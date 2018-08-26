package jdee.compiler;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public interface CompilationUnitProvider {

    Iterable<? extends JavaFileObject> findCompilationUnits(CompilerOptions options, StandardJavaFileManager fileManager);
}
