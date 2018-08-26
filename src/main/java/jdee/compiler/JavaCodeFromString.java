package jdee.compiler;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

import static javax.tools.JavaFileObject.Kind.SOURCE;

class JavaCodeFromString extends SimpleJavaFileObject {

    private final String code;

    JavaCodeFromString(CompilerOptions.StringCode stringCode) {
        super(formatURI(stringCode.name), SOURCE);
        this.code = stringCode.code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

    private static URI formatURI(String name) {
        return URI.create("string:///" + name.replace('.','/') + SOURCE.extension);
    }
}