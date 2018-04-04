package com.owe.wordladder;


import java.util.*;
import java.io.*;

public class WordLadder {
    private HashSet<String> words = new HashSet<String>();
    private HashSet<String> visitedWords = new HashSet<String>();
    private HashMap<String, String> path = new HashMap<String, String>();
    private String start = null;
    private String end = null;

    public WordLadder(String s, String e) {
        start = s;
        end = e;
        readWords();

    }

    public String getS() {
        return start;
    }

    public String getE() {
        return end;
    }


    private Vector<String> getNeighbors(String word) {
        Vector<String> neighbors = new Vector<String>();
        for (char insert_char = 'a'; insert_char <= 'z'; insert_char++) {
            for (int i = 0; i <= word.length(); i++) {
                StringBuffer s = new StringBuffer(word);
                s.insert(i, insert_char);
                neighbors.add(s.toString());

            }

        }
        for (char insert_char = 'a'; insert_char <= 'z'; insert_char++) {
            for (int i = 0; i < word.length(); i++) {
                if (insert_char != word.charAt(i)) {
                    StringBuffer s = new StringBuffer(word);
                    s.replace(i, i + 1, String.valueOf(insert_char));
                    neighbors.add(s.toString());
                }
            }

        }
        for (int i = 0; i < word.length(); i++) {
            StringBuffer s = new StringBuffer(word);
            s.replace(i, i + 1, "");
            neighbors.add(s.toString());
        }
        return neighbors;
    }

    public Vector<String> BFS() {
        ArrayDeque<String> queue = new ArrayDeque<String>();
        queue.addFirst(start);
        path.put(start, null);
        while (!queue.isEmpty()) {
            String now = queue.removeFirst();
            Vector<String> neighbors = getNeighbors(now);
            for (String s : neighbors) {
                if (s.equals(end)) {
                    path.put(end, now);
                    return getPath(s);
                }
                if (words.contains(s) && !visitedWords.contains(s)) {
                    path.put(s, now);
                    visitedWords.add(s);
                    queue.addLast(s);
                }
            }
        }
        return null;
    }

    private Vector<String> getPath(String end) {
        Vector<String> p = new Vector<String>();
        p.add(end);
        while (!path.get(end).equals(start)) {
            p.add(path.get(end));
            end = path.get(end);
        }
        p.add(start);
        return p;
    }

    private void readWords() {
        try {

            InputStream in = getClass().getClassLoader().getResourceAsStream("dictionary.txt");
            InputStreamReader inReader = new InputStreamReader(in);
            BufferedReader bufReader = new BufferedReader(inReader);
            String line = null;

            while ((line = bufReader.readLine()) != null) {
                words.add(line.trim());
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取" + "出错！");
        }

    }


}

