package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.util.TreeSet;
import java.util.Set;

/**
 * Created by slxn42 on 9/10/16.
 */
public class Node implements ITrie.INode {
    private int count;
    public Node[] nodes;
    public Node() {
        nodes = new Node[Trie.CHILDREN_SIZE];
    }
    /* Returns amount of nodes added */
    public int add(String sub_word){
        int add_factor=0; // If new node is created add 1 to recursive return.
        if(children(sub_word.toLowerCase().charAt(0))==null){
            nodes[sub_word.toLowerCase().charAt(0)-'a'] = new Node();
            add_factor=1;
        }
        if(sub_word.length()==1) {
            children(sub_word.toLowerCase().charAt(0)).incrementCount();
            return add_factor;
        }
        return add_factor+children(sub_word.toLowerCase().charAt(0)).add(sub_word.substring(1));
    }
    public Set<String> toSet(String parents){
        Set<String> output=new TreeSet<String>();
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    output.add(parents.toLowerCase() + (char) (iter + 'a'));
                }
                output.addAll(nodes[iter].toSet(parents.toLowerCase() + (char) (iter + 'a')));
            }
        }
        return output;
    }
    public String toString(String parents){
        StringBuilder output=new StringBuilder();
        for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++){
            //System.out.println("trying character "+(char) (iter + 'a'));
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    output.append(parents.toLowerCase()+(char) (iter + 'a'));
                    output.append("\n");
                    //System.out.println("Parents: " + parents.toLowerCase() + " Char:" + (char) (iter + 'a'));
                }
                output.append(nodes[iter].toString(parents.toLowerCase()+(char) (iter + 'a')));
            }
        }
        return output.toString();
    }
    public Node find(String word){
        if (children(word.toLowerCase().charAt(0)) == null) return null;
        if(word.length()==1){
            //System.out.println("Visit Count: "+children(word.toLowerCase().charAt(0)).getValue());
            if (children(word.toLowerCase().charAt(0)).getValue()>0)
                return children(word.toLowerCase().charAt(0));
            else return null;
        }
        else return children(word.toLowerCase().charAt(0)).find(word.substring(1));
    }
    public int getValue(){
        return count;
    }
    public Node children(char letter){
        return nodes[letter-'a'];
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