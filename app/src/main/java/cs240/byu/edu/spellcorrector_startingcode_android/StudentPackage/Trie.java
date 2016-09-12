package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by slxn42 on 9/10/16.
 */
public class Trie implements ITrie {
    private Node root;
    private int word_count;
    private int node_count;
    public static int CHILDREN_SIZE = 26;
    public Trie(){
        root=new Node();
    }
    public void add(String word){
        int output=root.add(word.toLowerCase());
        if(output >0) word_count++;
        node_count+=output;
    }
    public INode find(String word){
        return root.find(word.toLowerCase());
    }
    public int getWordCount(){
        return word_count;
    }
    public int getNodeCount(){
        return node_count;
    }
    @Override
    public String toString(){
        return root.toString();
    }
    public String stringFromSet(){
        StringBuilder output=new StringBuilder();
        Set<String> words=root.toSet("");
        Iterator<String> it = words.iterator();
        while(it.hasNext()){
            output.append(it.next().toString());
            output.append("\n");
        }
        return output.toString();
    }
    @Override
    public int hashCode(){
        return 0;
    }
    @Override
    public boolean equals(Object o){
        return false;
    }
}
