package org.example;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Getter
public class WordsMap {
    private final HashMap<String, List<String>> wordMap = new HashMap<>();
    private final Map<String, String> docs = new HashMap<>();

    public void createWords() {
        for(Map.Entry<String, String> entry : docs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            this.addToMap(value, key);
        }
    }

    private void addToMap(String document, String numDocument) {
        for (String word : document.split(" ")) {
            this.wordMap.put(word, checkWord(word, numDocument));
        }
    }

    private List<String> checkWord(String word, String numDocument) {
        List<String> val = this.wordMap.getOrDefault(word.toLowerCase(), new ArrayList<>());
        val.add("document " + numDocument);
        return val;
    }

    public void addFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine())
            this.docs.put(file.getName(), scanner.nextLine());
    }
}
