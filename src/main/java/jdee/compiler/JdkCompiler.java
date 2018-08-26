package jdee.compiler;

import javax.tools.*;
import javax.tools.JavaCompiler.CompilationTask;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

class JdkCompiler {

    private final JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
    private final CompilationUnitProvider fileCompilationUnitProvider = new CompilationUnitFromFile();
    private final CompilationUnitProvider stringCompilationUnitProvider = new CompilationUnitFromString();

    void compile(DiagnosticListener<? super JavaFileObject> diagnosticListener, CompilerOptions options) {
        if (options.stringCode != null) {
            compile(diagnosticListener, options, stringCompilationUnitProvider);
        } else {
            compile(diagnosticListener, options, fileCompilationUnitProvider);
        }
    }

    private void compile(DiagnosticListener<? super JavaFileObject> diagnosticListener,
                         CompilerOptions options, CompilationUnitProvider compilationUnitProvider) {
        StandardJavaFileManager fileManager = getStandardFileManager(diagnosticListener);
        Iterable<? extends JavaFileObject> compilationUnits
                = compilationUnitProvider.findCompilationUnits(options, fileManager);

        Writer additionalOutputWriter = null;
        CompilationTask task = javac.getTask(additionalOutputWriter, fileManager, diagnosticListener,
                options.options, options.annotatedClasses, compilationUnits);
        if (task.call()) {
            System.out.println("Successful compilation.");
        } else {
            System.out.println("Finished with errors.");
        }
    }

    private StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener) {
        return javac.getStandardFileManager(diagnosticListener, Locale.getDefault(), StandardCharsets.UTF_8);
    }
}
