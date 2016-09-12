package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by slxn42 on 9/9/16.
 */
public class SpellCorrector implements ISpellCorrector{
    private Trie dictionary;
    public SpellCorrector(){
        dictionary = new Trie();
    }
    public void useDictionary(InputStreamReader dictionaryFile) throws IOException{
        Scanner dictionary_input=new Scanner(dictionaryFile);
        while (dictionary_input.hasNext())
            dictionary.add(dictionary_input.next());
        dictionary_input.close();
    }
    @Override
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {
        Set<String> suggestions = new TreeSet<String>();
        suggestions.add(inputWord);
        String found_word = findBestWord(suggestions);
        if (found_word != null) {
            return found_word;
        }
        suggestions = findMoreWords(suggestions);
        found_word = findBestWord(suggestions);
        if (found_word != null) {
            return found_word;
        }
        suggestions = findMoreWords(suggestions);
        found_word = findBestWord(suggestions);
        if (found_word != null) {
            return found_word;
        }
        throw new NoSimilarWordFoundException();
    }
    private String findBestWord(Set<String> words) {
        Iterator<String> iter = words.iterator();
        int best_count = 0;
        String best_word = null;
        while (iter.hasNext()) {
            String current = iter.next();
            ITrie.INode current_node = dictionary.find(current);
            if (current_node != null){
                if (current_node.getValue() > best_count) {
                    best_count = current_node.getValue();
                    best_word = current;
                }
            }
        }
        return best_word;
    }
    private Set<String> findMoreWords(Set<String> suggestions) {
        Set<String> new_suggestions = new TreeSet<String>();
        new_suggestions.addAll(deletionChange(suggestions));
        new_suggestions.addAll(transpositionChange(suggestions));
        new_suggestions.addAll(alterationChange(suggestions));
        new_suggestions.addAll(insertionChange(suggestions));
        return new_suggestions;
    }
    private Set<String> deletionChange(Set<String> old_suggestions) {
        System.out.println("Flag 1");
        Set<String> new_suggestions=new TreeSet<String>();
        Iterator<String> iter = old_suggestions.iterator();
        while (iter.hasNext()) {
            String current_word = iter.next();
            for (int index = 0; index < current_word.length(); index++) {
                new_suggestions.add(current_word.substring(0, index)+current_word.substring(index+1));
            }
        }
        return new_suggestions;
    }
    private Set<String> transpositionChange(Set<String> old_suggestions) {
        System.out.println("Flag 2");
        Set<String> new_suggestions=new TreeSet<String>();
        Iterator<String> iter = old_suggestions.iterator();
        while (iter.hasNext()) {
            String current_word = iter.next();
            for (int index = 0; index < current_word.length()-1; index++) {
                new_suggestions.add(current_word.substring(0, index)+current_word.substring(index+1, index+2)+current_word.substring(index, index+1)+current_word.substring(index+2));
            }
        }
        return new_suggestions;
    }
    private Set<String> alterationChange(Set<String> old_suggestions) {
        System.out.println("Flag 3");
        Set<String> new_suggestions=new TreeSet<String>();
        Iterator<String> iter = old_suggestions.iterator();
        while (iter.hasNext()) {
            String current_word = iter.next();
            for (int index = 0; index < current_word.length(); index++) {
                for (char to_insert = 'a'; to_insert <= 'z'; to_insert++) {
                    new_suggestions.add(current_word.substring(0, index)+to_insert+current_word.substring(index+1));
                }
            }
        }
        return new_suggestions;
    }
    private Set<String> insertionChange(Set<String> old_suggestions) {
        System.out.println("Flag 4");
        Set<String> new_suggestions=new TreeSet<String>();
        Iterator<String> iter = old_suggestions.iterator();
        while (iter.hasNext()) {
            String current_word = iter.next();
            for (int index = 0; index <= current_word.length(); index++) {
                for (char to_insert = 'a'; to_insert <= 'z'; to_insert++) {
                    new_suggestions.add(current_word.substring(0, index)+to_insert+current_word.substring(index));
                }
            }
        }
        return new_suggestions;
    }
}
