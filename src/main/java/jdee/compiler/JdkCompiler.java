package jdee.compiler;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

class JdkCompiler {

    private JavaCompiler javac = ToolProvider.getSystemJavaCompiler();

    void compile(DiagnosticListener<? super JavaFileObject> diagnosticListener, CompilerOptions options) {
        StandardJavaFileManager fileManager = getStandardFileManager(diagnosticListener);

        Writer additionalOutputWriter = null;

        CompilationTask task = javac.getTask(additionalOutputWriter, fileManager, diagnosticListener,
                options.options, options.annotatedClasses, findCompilationUnits(options, fileManager));
        if (task.call()) {
            System.out.println("Successful compilation.");
        } else {
            System.out.println("Finished with errors.");
        }
    }

    private Iterable<? extends JavaFileObject> findCompilationUnits(CompilerOptions options, StandardJavaFileManager fileManager) {
        System.out.println("Scanning files: " + options.filesToCompile);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(options.filesToCompile);
        System.out.println("Found compilation units: " + compilationUnits);
        return compilationUnits;
    }

    private StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        return javac.getStandardFileManager(diagnosticListener, Locale.getDefault(), StandardCharsets.UTF_8);
    }
}
