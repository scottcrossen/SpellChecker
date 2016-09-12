package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.util.TreeSet;
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
    public int add(String sub_word){
        if(sub_word==""){
            count++;
            return 0;
        }
        if(children(sub_word.toLowerCase().charAt(0))==null){
            Node add_node=children(sub_word.toLowerCase().charAt(0));
            add_node=new Node();
            return 1+children(sub_word.toLowerCase().charAt(0)).add(sub_word.substring(1));
        }
        return children(sub_word.toLowerCase().charAt(0)).add(sub_word.substring(1));
    }
    public Set<String> toSet(String parents){
        Set<String> output=new TreeSet<String>();
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    output.add(parents+(char) (iter + 97));
                }
                output.addAll(nodes[iter].toSet(parents+(char) (iter + 97)));
            }
        }
        return output;
    }
    public String toString(String parents){
        StringBuilder output=new StringBuilder();
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    output.append(parents+(char) (iter + 97));
                    output.append("\n");
                }
                output.append(nodes[iter].toString(parents+(char) (iter + 97)));
            }
        }
        return output.toString();
    }
    public Node find(String word){
        if (word==""){
            if (count >0) return this;
            else return null;
        }
        if (children(word.charAt(0)) == null) return null;
        else return children(word.charAt(0)).find(word.substring(1));
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
    public void incrementCount() {
        count++;
    }
}