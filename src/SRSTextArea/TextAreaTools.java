/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRSTextArea;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JTextArea;

/**
 *
 * @author helcio.soares
 */
public class TextAreaTools {

    public static JTextArea getJtaTexto(JTextArea textArea) {

        SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
        try {
            SpellChecker.registerDictionaries(new URL("file", null, ""), "pt", "pt");
        } catch (MalformedURLException e) {
        }
        SpellChecker.register(textArea);
        return textArea;
    }
}
