package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {
    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public void run() {
        WordsMap words = new WordsMap();
        FileRead fileRead = new FileRead("src/main/resources");
        fileRead.readFilesFromPath(words);
        Scanner scanner = new Scanner(System.in);
        System.out.println("input: \n");
        String input = scanner.nextLine();
        List<String> or = new ArrayList<>();
        List<String> neg = new ArrayList<>();
        List<String> norm = new ArrayList<>();
        getInput(input, or, neg, norm);
        Set<String> docs = addNormal(norm, words.getWordMap());
        docs = addOr(or, words.getWordMap(), docs);
        docs = deleteNeg(neg, words.getWordMap(), docs);
        System.out.println(docs);
    }

    public void getInput(String input, List<String> or, List<String> neg, List<String> norm) {
        for (String s : input.split(" ")) {
            if (s.charAt(0) == '+') or.add(s.substring(1));
            else if (s.charAt(0) == '-') neg.add(s.substring(1));
            else norm.add(s);
        }
    }

    public Set<String> deleteNeg(List<String> neg, HashMap<String, List<String>> allWords, Set<String> docs) {
        Set<String> documents = new HashSet<>(docs);
        for (String s : neg) {
            if (allWords.get(s) != null) {
                documents = documents.stream().filter(doc -> !allWords.get(s).contains(doc)).collect(Collectors.toSet());
            }
        }
        return documents;
    }

    public Set<String> addOr(List<String> or, HashMap<String, List<String>> allWords, Set<String> docs) {
        Set<String> documents = new HashSet<>(docs);
        for (String s : or) {
            if (allWords.get(s) != null)
                documents.addAll(allWords.get(s));
        }
        return documents;
    }

    public Set<String> addNormal(List<String> norm, HashMap<String, List<String>> allWords) {
        Set<String> docs = new HashSet<>();
        if (norm.size() != 0) {
            docs.addAll(allWords.get(norm.get(0)));
            for (String s : norm) {
                if (allWords.get(s) != null) docs.retainAll(allWords.get(s));
            }
        }
        return docs;
    }
}
