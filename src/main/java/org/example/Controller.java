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
        Words words = new Words();
        FileRead fileRead = new FileRead("src/main/resources");
        fileRead.readFilesFromPath(words);
        Scanner scanner = new Scanner(System.in);
        System.out.println("input: \n");
        String input = scanner.nextLine();
        System.out.println(input);
        ArrayList<String> or = new ArrayList<>();
        ArrayList<String> neg = new ArrayList<>();
        ArrayList<String> norm = new ArrayList<>();
        getInput(input, or, neg, norm);
        Set<String> docs = add_normal(norm, words.getWordMap());
        add_or(or, words.getWordMap(), docs);
        del_neg(neg, words.getWordMap(), docs);
        System.out.println(docs);
    }

    public void getInput(String input, ArrayList<String> or, ArrayList<String> neg, ArrayList<String> norm) {
        for (String s : input.split(" ")) {
            if (s.charAt(0) == '+') or.add(s.substring(1));
            else if (s.charAt(0) == '-') neg.add(s.substring(1));
            else norm.add(s);
        }
    }
    public void del_neg(ArrayList<String> neg, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        for (String s : neg) {
            if (allWords.get(s) != null) {
                docs.removeIf(doc -> allWords.get(s).contains(doc));
            }
        }
    }

    public void add_or(ArrayList<String> or, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        for (String s : or) {
            if (allWords.get(s) != null)
                docs.addAll(allWords.get(s));
        }
    }

    public Set<String> add_normal(ArrayList<String> norm, HashMap<String, ArrayList<String>> allWords) {
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
