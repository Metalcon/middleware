package de.metalcon.middleware.springconfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Html5FormattetedFreeMarkerView extends FormattetedFreeMarkerView {

    @Override
    protected String formatTemplate(String template) {
        if (!template.contains("<html")) {
            return template;
        }

        try {
            Path tmpDir = Files.createTempDirectory("tidy");
            Path inputFile = tmpDir.resolve("in");
            Path outputFile = tmpDir.resolve("out");
            Path errorsFile = tmpDir.resolve("err");

            String tidyCmd = "tidy";
            //@formatter:off
            String options =
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
            String call =
                      tidyCmd
                    + " " + options
                    + " -o " + outputFile.toString()
                    + " -f " + errorsFile.toString()
                    + " " + inputFile.toString();
            //@formatter:on

            PrintWriter writer = new PrintWriter(inputFile.toString(), "UTF-8");
            writer.println(template);
            writer.close();

            Runtime.getRuntime().exec(call).waitFor();

            StringBuilder output = new StringBuilder();
            for (String line : Files.readAllLines(outputFile,
                    Charset.defaultCharset())) {
                output.append(line);
                output.append("\n");
            }

            for (String line : Files.readAllLines(errorsFile,
                    Charset.defaultCharset())) {
                System.err.println(line);
            }

            inputFile.toFile().delete();
            outputFile.toFile().delete();
            errorsFile.toFile().delete();
            tmpDir.toFile().delete();

            return output.toString();
        } catch (IOException | InterruptedException e) {
            // If we can't tidy output then we don't :)
            return template;
        }
    }
}
