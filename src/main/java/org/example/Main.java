package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> documents = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            readFile(documents, i);
        }
        HashMap<String, ArrayList<String>> allWords = createMap(documents);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        ArrayList<String> or = new ArrayList<>();
        ArrayList<String> neg = new ArrayList<>();
        ArrayList<String> norm = new ArrayList<>();
        getInput(input, or, neg, norm);
        Set<String> docs = new HashSet<>();
        add_normal(norm, allWords, docs);
        add_or(or, allWords, docs);
        del_neg(neg, allWords, docs);
        System.out.println(docs);
    }

    public static HashMap<String, ArrayList<String>> createMap(ArrayList<String> documents) {
        HashMap<String, ArrayList<String>> allWords = new HashMap<>();
        int i = 0;
        for (String document : documents) {
            for (String s : document.split(" ")) {
                ArrayList<String> val;
                if (allWords.containsKey(s)) {
                    val = allWords.get(s);
                } else {
                    val = new ArrayList<>();
                }
                val.add("document " + i);
                allWords.put(s, val);
            }
            i++;
        }
        return allWords;
    }

    public static void readFile(ArrayList<String> documents, int i) {
        File myObj = new File("src/main/java/org/example/filename" + i + ".txt");
        Scanner myReader;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        StringBuilder data = new StringBuilder();
        while (myReader.hasNextLine())
            data.append(myReader.nextLine());
        documents.add(String.valueOf(data));
    }

    public static void getInput(String input, ArrayList<String> or, ArrayList<String> neg, ArrayList<String> norm) {
        for (String s : input.split(" ")) {
            if (s.charAt(0) == '+') or.add(s.substring(1));
            else if (s.charAt(0) == '-') neg.add(s.substring(1));
            else norm.add(s);
        }
    }
    public static void del_neg(ArrayList<String> neg, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        for (String s : neg) {
            if (allWords.get(s) != null) {
                docs.removeIf(doc -> allWords.get(s).contains(doc));
            }
        }
    }

    public static void add_or(ArrayList<String> or, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        for (String s : or) {
            if (allWords.get(s) != null)
                docs.addAll(allWords.get(s));
        }
    }

    public static void add_normal(ArrayList<String> norm, HashMap<String, ArrayList<String>> allWords, Set<String> docs) {
        if (norm.size() != 0) {
            docs.addAll(allWords.get(norm.get(0)));
            for (String s : norm) {
                if (allWords.get(s) != null) docs.retainAll(allWords.get(s));
            }
        }
    }

}