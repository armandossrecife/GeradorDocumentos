/*    */ package visao.view.com.inet.jorthodictionaries;
/*    */ 
/*    */ import java.io.BufferedOutputStream;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileInputStream;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.io.Reader;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.zip.Deflater;
/*    */ import java.util.zip.DeflaterOutputStream;
/*    */ 
/*    */ public class WordList2Dictionary
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws IOException
/*    */   {
/* 54 */     if (args.length != 2) {
/* 55 */       System.out.println("Convert a plain txt file with UTF8 encoding to a dictionary file.");
/* 56 */       System.out.println("  Usage:");
/* 57 */       System.out.println("\tjava com.inet.jorthodictionaries.WordList2Dictionary <txt file> <dic file>");
/* 58 */       System.out.println("\t<txt file>\ttext file with words in UTF8 coding, every word is in its own line");
/* 59 */       System.out.println("\t<dic file>\toutput file name of the created dictionary");
/* 60 */       System.exit(1);
/*    */     }
/*    */ 
/* 64 */     File dictFile = new File(args[1]);
/* 65 */     OutputStream dict = new FileOutputStream(dictFile);
/* 66 */     dict = new BufferedOutputStream(dict);
/* 67 */     Deflater deflater = new Deflater();
/* 68 */     deflater.setLevel(9);
/* 69 */     dict = new DeflaterOutputStream(dict, deflater);
/* 70 */     dict = new BufferedOutputStream(dict);
/* 71 */     PrintStream dictPs = new PrintStream(dict, false, "UTF8");
/*    */ 
/* 74 */     File txtFile = new File(args[0]);
/* 75 */     FileInputStream fis = new FileInputStream(txtFile);
/* 76 */     Reader reader = new InputStreamReader(fis, "UTF8");
/* 77 */     BufferedReader txt = new BufferedReader(reader);
/*    */ 
/* 80 */     ArrayList wordList = new ArrayList();
/* 81 */     String word = txt.readLine();
/* 82 */     while (word != null) {
/* 83 */       wordList.add(word);
/* 84 */       word = txt.readLine();
/*    */     }
/*    */ 
/* 88 */     String[] words = (String[])wordList.toArray(new String[wordList.size()]);
/* 89 */     Arrays.sort(words);
/* 90 */     for (int i = 0; i < words.length; i++) {
/* 91 */       dictPs.print(words[i] + '\n');
/*    */     }
/* 93 */     dictPs.close();
/*    */   }
/*    */ }

/* Location:           C:\Users\Rafael\Documents\NetBeansProjects\JOrtho - Corretor Ortográfico Em Java\JOrtho - Corretor Ortográfico Em Java.jar
 * Qualified Name:     com.inet.jorthodictionaries.WordList2Dictionary
 * JD-Core Version:    0.6.2
 */