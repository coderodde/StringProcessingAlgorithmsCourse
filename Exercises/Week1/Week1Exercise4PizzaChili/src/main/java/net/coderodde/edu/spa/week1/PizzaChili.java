package net.coderodde.edu.spa.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PizzaChili {
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("So where is the Pizza Chili?");
            return;
        }
        
        File file = new File(args[0]);
        
        if (!file.exists()) {
            System.out.println("File \"" + args[0] + "\" does not exist.");
            return;
        }
        
        if (!file.isFile()) {
            System.out.println("\"" + args[0] + "\" is not an ordinary file.");
            return;
        }
        
        List<String> topWordList = processPizzaChili(file, 10);
        
        for (String word : topWordList) {
            System.out.println(word);
        }
    }
    
    private static List<String> processPizzaChili(File file, int amount) {
        List<String> ret = new ArrayList<>(amount);
        Scanner scanner;
        
        try {
            scanner = new Scanner(new FileReader(file));
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            return ret; // Return an empty list.
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        // Build the histogram.
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            if (line.trim().isEmpty()) {
                continue;
            }
            
            String[] words = line.split("(\\s|\\p{Punct})+");
            
            for (String word : words) {
                word = word.toLowerCase();
                
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        
        List<String> allWordList = new ArrayList<>(map.keySet());
        
        Collections.sort(allWordList, (String a, String b) -> {
            return -Integer.compare(map.get(b), map.get(a));
        });
        
        ret.addAll(allWordList.subList(0, 
                                       Math.min(amount, 
                                                allWordList.size())));
        return ret;
    }
    
}
