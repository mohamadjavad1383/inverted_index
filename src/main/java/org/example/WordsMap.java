package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class WordsMap {
    private HashMap<String, ArrayList<String>> wordMap = new HashMap<>();
    private List<String> docs = new ArrayList<>();

    public void createWords() {
        int i = 0;
        for (String document : this.docs) {
            for (String s : document.split(" ")) {
                s = s.toLowerCase();
                ArrayList<String> val;
                if (this.wordMap.containsKey(s)) {
                    val = this.wordMap.get(s);
                } else {
                    val = new ArrayList<>();
                }
                val.add("document " + i);
                this.wordMap.put(s, val);
            }
            i++;
        }
    }

    public HashMap<String, ArrayList<String>> getWordMap() {
        return wordMap;
    }

    public void addFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine())
            this.docs.add(scanner.nextLine());
    }
}
