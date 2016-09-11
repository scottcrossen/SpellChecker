package cs240.byu.edu.spellcorrector_startingcode_android.StudentPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by slxn42 on 9/9/16.
 */
public class SpellCorrector implements ISpellCorrector{
    public SpellCorrector(){
        trie = new Trie();
    }
    private Trie trie;
    public void useDictionary(InputStreamReader dictionaryFile) throws IOException{
        BufferedReader input_dictionary=new BufferedReader(dictionaryFile);
    }
    public String suggestSimilarWord(String inputWord) throws NoSimilarWordFoundException {

        return inputWord;
    }
}
