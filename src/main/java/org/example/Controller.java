package org.example;

import java.util.*;

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
        ArrayList<String> or = new ArrayList<>();
        ArrayList<String> neg = new ArrayList<>();
        ArrayList<String> norm = new ArrayList<>();
        getInput(input, or, neg, norm);
        Set<String> docs = addNormal(norm, words.getWordMap());
        docs = addOr(or, words.getWordMap(), docs);
        docs = deleteNeg(neg, words.getWordMap(), docs);
        System.out.println(docs);
    }

    public void getInput(String input, ArrayList<String> or, ArrayList<String> neg, ArrayList<String> norm) {
        for (String s : input.split(" ")) {
            if (s.charAt(0) == '+') or.add(s.substring(1));
            else if (s.charAt(0) == '-') neg.add(s.substring(1));
            else norm.add(s);
        }
    }

    public Set<String> deleteNeg(ArrayList<String> neg, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        Set<String> documents = new HashSet<>(docs);
        for (String s : neg) {
            if (allWords.get(s) != null) {
                documents.removeIf(doc -> allWords.get(s).contains(doc));
            }
        }
        return documents;
    }

    public Set<String> addOr(ArrayList<String> or, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        Set<String> documents = new HashSet<>(docs);
        for (String s : or) {
            if (allWords.get(s) != null)
                documents.addAll(allWords.get(s));
        }
        return documents;
    }

    public Set<String> addNormal(ArrayList<String> norm, HashMap<String, ArrayList<String>> allWords) {
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
