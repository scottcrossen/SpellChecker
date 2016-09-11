package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

/**
 * Created by slxn42 on 9/10/16.
 */
public class Trie implements ITrie {
    private Node root;
    private int wordCount;
    private int nodeCount;
    public static int CHILDREN_SIZE = 26;
    public Trie(){
        root=new Node();
    }
    public void add(String word){
        int output=root.add(word);
        if(output >0) wordCount++;
        nodeCount+=output;
    }
    public INode find(String word){
        return new Node();
    }
    public int getWordCount(){
        return wordCount;
    }
    public int getNodeCount(){
        return nodeCount;
    }
    @Override
    public String toString(){
        return new String();
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
