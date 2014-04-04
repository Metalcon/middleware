package de.metalcon.middleware.springconfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Html5FormattetedFreeMarkerView extends FormattetedFreeMarkerView {

    @Override
    protected String formatTemplate(String template) {
        if (!template.contains("<!DOCTYPE html>")) {
            return template;
        }

        try {
            // TODO: don't call tidy with Files path arguments but rather
            // writing to stdin, and reading from stdout and stderr.

            Path tmpDir = Files.createTempDirectory("tidy");
            Path inputFile = tmpDir.resolve("in");
            Path outputFile = tmpDir.resolve("out");
            Path errorsFile = tmpDir.resolve("err");

            String tidyCmd = "tidy";
            //@formatter:off
            String tidyOptions =
                      "-i -utf8 "
                    + "--coerce-endtags n "
                    + "--drop-empty-elements n "
                    + "--drop-empty-paras n "
                    + "--fix-backslash n "
                    + "--fix-bad-comments n "
                    + "--fix-uri n "
                    + "--join-styles n "
                    + "--lower-literals n "
                    + "--merge-divs n "
                    + "--merge-emphasis n "
                    + "--merge-spans n "
                    + "--quote-ampersand n "
                    + "--quote-nbsp n "
                    + "--indent auto "
                    + "--indent-spaces 2 "
                    + "--wrap 0 "
                    + "--force-output y "
                    + "--quiet y "
                    + "--tidy-mark n";
            String tidyCall =
                      tidyCmd + " "
                    + tidyOptions + " "
                    + "-o " + outputFile.toString() + " "
                    + "-f " + errorsFile.toString() + " "
                    + inputFile.toString();
            //@formatter:on

            Files.write(inputFile, template.getBytes());

            Runtime.getRuntime().exec(tidyCall).waitFor();

            String output = new String(Files.readAllBytes(outputFile));
            System.err.print(new String(Files.readAllBytes(errorsFile)));

            inputFile.toFile().delete();
            outputFile.toFile().delete();
            errorsFile.toFile().delete();
            tmpDir.toFile().delete();

            return output;
        } catch (IOException | InterruptedException e) {
            // Use unformatted output as fallback, as formatting shouldn't
            // change semantics of output.
            return template;
        }
    }
}
