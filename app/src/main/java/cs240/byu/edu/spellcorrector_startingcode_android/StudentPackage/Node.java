package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by slxn42 on 9/10/16.
 */
public class Node implements ITrie.INode {
    private int count;
    //private String word;
    public Node[] nodes;
    public Node() {
        nodes = new Node[Trie.CHILDREN_SIZE];
    }
    /* Returns amount of nodes added */
    public int add(String subWord){
        if(subWord==""){
            count++;
            return 0;
        }
        if(children(subWord.toLowerCase().charAt(0))==null){
            Node add_node=children(subWord.toLowerCase().charAt(0));
            add_node=new Node();
            return 1+children(subWord.toLowerCase().charAt(0)).add(subWord.substring(1));
        }
        return children(subWord.toLowerCase().charAt(0)).add(subWord.substring(1));
    }
    public Set<String> toString(String parents){
        Set<String> output=new HashSet();
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    /*StringBuilder newWord=new StringBuilder();
                    newWord.append(parents);
                    newWord.append((char) (iter + 97));
                    output.add(newWord.toString());*/
                    output.add(parents+(char) (iter + 97));
                }
                output.addAll(nodes[iter].toString(parents+(char) (iter + 97)));
            }
        }
        return output;
    }
    public int getValue(){
        return count;
    }
    public Node children(char letter){
        return nodes[letter-97];
    }
    /*public int getNodeCount(){
        int children_size=0;
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            if(nodes[iter] != null) children_size+=nodes[iter].getNodeCount();
        }
        return children_size;
    }*/
    /*public String getWord(){
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }*/
    public void incrementCount() {
        count++;
    }
}