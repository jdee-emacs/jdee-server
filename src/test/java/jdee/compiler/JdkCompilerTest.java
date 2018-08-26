package jdee.compiler;

import org.junit.Test;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JdkCompilerTest {

    private JdkCompiler compiler = new JdkCompiler();

    private List<Diagnostic<? extends JavaFileObject>> compilerOutput = new ArrayList<>();

    private DiagnosticListener<JavaFileObject> diagnosticListener = new DiagnosticListener<JavaFileObject>() {
        @Override
        public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
            compilerOutput.add(diagnostic);
        }
    };

    @Test
    public void shouldCompileGivenCodeWithoutErrors() {
        CompilerOptions options = new CompilerOptions();
        options.filesToCompile.add(pathTo("HelloWorld.java"));
        options.options = asList(
                "-d", "./target",
                "-deprecation",
                "-source", "8",
                "-sourcepath", "src/test/resources/otherDeps",
                "-Xlint:all",
                "-Xmaxerrs", "100",
                "-Xmaxwarns", "100"
        );

        compiler.compile(diagnosticListener, options);

        assertEquals("Compiler output: " + compilerOutput, 2, compilerOutput.size());
    }

    @Test
    public void shouldCompileClass() {
        CompilerOptions options = new CompilerOptions();
        options.filesToCompile.add(pathTo("CorrectAnnotatedClass.java"));
        options.options = asList(
                "-d", "./target",
                "-source", "1.8",
                "-sourcepath", "src/test/resources/otherDeps",
                "-Xlint:all"
        );

        compiler.compile(diagnosticListener, options);

        assertEquals("Compiler output: " + compilerOutput, 0, compilerOutput.size());
    }

    private String pathTo(String filename) {
        String path = Paths.get("src/test/resources", filename).toAbsolutePath().toString();
        System.out.println("Resolved path: " + path);
        return path;
    }

}