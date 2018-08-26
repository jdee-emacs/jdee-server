package jdee.compiler;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

public class CompilationUnitFromFile implements CompilationUnitProvider {

    @Override
    public Iterable<? extends JavaFileObject> findCompilationUnits(
            CompilerOptions options, StandardJavaFileManager fileManager) {
        System.out.println("Scanning files: " + options.filesToCompile);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(options.filesToCompile);
        System.out.println("Found compilation units: " + compilationUnits);
        return compilationUnits;
    }
}
