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
        count = 0;
    }
    /* Returns amount of nodes added */
    public int[] add(String sub_word){
        int[] output= new int[2]; // Yeah this is a crappy method to do this but I don't want to change my code.
        output[0]=0; // If new node is created add 1 to recursive return.
        if(children(sub_word.toLowerCase().charAt(0))==null){
            nodes[sub_word.toLowerCase().charAt(0)-'a'] = new Node();
            output[0]=1;
        }
        if(sub_word.length()==1) {
            children(sub_word.toLowerCase().charAt(0)).incrementCount();
            if(children(sub_word.toLowerCase().charAt(0)).getValue()==1)
                output[1]=1;
            return output;
        }
            int[] temp_output = children(sub_word.toLowerCase().charAt(0)).add(sub_word.substring(1));
            output[0] = output[0] + temp_output[0];
            output[1] = temp_output[1];
            return output;
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
            if(nodes[iter] != null){
                if(nodes[iter].count != 0) {
                    output.append(parents.toLowerCase()+(char) (iter + 'a'));
                    output.append("\n");
                }
                output.append(nodes[iter].toString(parents.toLowerCase() + (char) (iter + 'a')));
            }
        }
        return output.toString();
    }
    public Node find(String word){
        if (word==null) return null;
        if(word.length()==0) return null;
        if (children(word.toLowerCase().charAt(0)) == null) return null;
        if(word.length()==1){
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
    @Override
    public boolean equals(Object o){
        if (o == this)
            return true;
        else if (o == null)
            return false;
        else if (getClass() != o.getClass())
            return false;
        else{
            Node other = (Node)o;
            if (getValue() != other.getValue())
                return false;
            else {
                for(int iter=0; iter<Trie.CHILDREN_SIZE; iter++) {
                    if (nodes[iter]==null && other.nodes[iter]!=null) return false;
                    if (nodes[iter]!=null)
                        if (!(nodes[iter].equals(other.nodes[iter]))) return false;
                }
                return true;
            }
        }
    }
    public void incrementCount() {
        count++;
    }
}