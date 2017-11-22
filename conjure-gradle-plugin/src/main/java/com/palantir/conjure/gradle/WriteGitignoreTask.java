/*
 * (c) Copyright 2017 Palantir Technologies Inc. All rights reserved.
 */

package com.palantir.conjure.gradle;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

public class WriteGitignoreTask extends DefaultTask {

    @OutputFile
    private Path outputFile;

    private String contents;

    public final void setOutputDirectory(File outputDirectory) {
        this.outputFile = outputDirectory.toPath().resolve(".gitignore");
    }

    public final void setContents(String contents) {
        this.contents = contents;
    }

    @TaskAction
    public final void compileFiles() throws IOException {
        Files.createDirectories(outputFile.getParent());

        Files.write(outputFile, contents.getBytes(StandardCharsets.UTF_8));
    }
}
