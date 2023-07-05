package org.example;

import java.io.File;
import java.io.FileNotFoundException;

public class FileRead {
    private final String path;

    public FileRead(String path) {
        this.path = path;
    }

    public void readFilesFromPath(Words words) {
        File folder = new File(this.path);
        this.listFilesForFolder(folder, words);
    }

    private void listFilesForFolder(final File folder, Words words) {

        try {
            for (final File fileEntry : folder.listFiles()) {
                File file = new File(path + "/" + fileEntry.getName());
                words.addFile(file);
            }
        } catch (NullPointerException e) {
            System.out.println("no file in this directory");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        words.createWords();
    }
}
