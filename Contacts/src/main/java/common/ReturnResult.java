package common;

import org.graalvm.compiler.api.replacements.Snippet;

public class ReturnResult {
    private ReturnCode code;
    private String result;

    public ReturnResult(@Snippet.NonNullParameter ReturnCode code, @Snippet.NonNullParameter String result) {
        this.code = code;
        this.result = result;
    }

    public ReturnResult(@Snippet.NonNullParameter ReturnCode code) {
        this.code = code;
        result = "";
    }

    public ReturnResult() {
        code = ReturnCode.Continue;
        result = "";
    }

    public ReturnCode getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }
}
