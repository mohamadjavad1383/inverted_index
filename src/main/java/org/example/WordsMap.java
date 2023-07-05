package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordsMap {
    private HashMap<String, ArrayList<String>> wordMap = new HashMap<>();
    private Map<String, String> docs = new HashMap<>();

    public void createWords() {
        for(Map.Entry<String, String> entry : docs.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            this.addToMap(value, key);
        }
    }

    private void addToMap(String document, String numDocument) {
        for (String word : document.split(" ")) {
            word = word.toLowerCase();
            ArrayList<String> val;
            if (this.wordMap.containsKey(word)) {
                val = this.wordMap.get(word);
            } else {
                val = new ArrayList<>();
            }
            val.add("document " + numDocument);
            this.wordMap.put(word, val);
        }
    }

    public HashMap<String, ArrayList<String>> getWordMap() {
        return wordMap;
    }

    public void addFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        if (scanner.hasNextLine())
            this.docs.put(file.getName(), scanner.nextLine());
    }
}
