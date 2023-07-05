package org.example;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;

@AllArgsConstructor
public class FileRead {
    private final String path;

    public void readFilesFromPath(WordsMap words) {
        File folder = new File(this.path);
        this.listFilesForFolder(folder, words);
    }

    private void listFilesForFolder(final File folder, WordsMap words) {

        try {
            for (final File fileEntry : folder.listFiles()) {
                File file = new File(path + "/" + fileEntry.getName());
                words.addFile(file);
                words.createWords();
            }
        } catch (FileNotFoundException e) {
            System.out.println("no file in this directory");
            System.exit(0);
        }
    }
}
