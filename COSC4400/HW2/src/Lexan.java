import java.io.*;
import java.util.HashMap;

/**
 * COSC 4400
 * Assignment 2
 * 
 * @author Liam Fruzyna
 */
public class Lexan {

	InputStreamReader in_reader = new InputStreamReader(System.in);

	public int lineNum = 1;
	public Object tokenVal = null; // Reference to any associated attributes
	public StringBuffer lexBuf = new StringBuffer();

	HashMap<String, VarEntry> symTable = null;
	
	// Tokens:
	public static final int NUM = 	256;  // Chosen greater than any ASCII code
	public static final int ID = 	257;
	public static final int DIV = 	258;
	public static final int MOD = 	259;
	public static final int ASIGN = 260;
	public static final int IF = 	261;
	public static final int THEN = 	262;
	public static final int WHILE = 263;
	public static final int DO = 	264;
	public static final int LABEL = 265;
	public static final int GOTO = 	266;
	public static final int BEGIN = 267;
	public static final int END = 	268;
	public static final int STORE = 269;
	public static final int DONE = 	0;
	public static final int EOF = 	-1;

	public Lexan(HashMap<String, VarEntry> st){
	  symTable = st;
	}
	
	public int nextToken(){
		int t;
		while(true){
			t = getChar();
			if (t == ' ' || t == '\t' || t == '\r'){
				continue;
			}
			else if (t == '\n'){
				lineNum++;
				continue;
			}
			else if (t == ':') {
				// find assignment
				t = getChar();
				if (t == '=') {
					return ASIGN;
				}
				// release character if it wasn't followed by =
				ungetChar(t);
				return ':';
			}
			else if (Character.isDigit((char)t)){
				int val = t - '0';
				t = getChar();
				while (Character.isDigit((char)t)){
					val = val*10 + t - '0';
					t = getChar();
				}
				ungetChar(t);
				tokenVal = new Integer(val);
				return NUM;
			}
			else if (Character.isLetter((char)t)){
				lexBuf.setLength(0);
			    while (Character.isLetterOrDigit((char)t)){
			    	lexBuf.append((char)t);
			    	t = getChar();
			    }
			    ungetChar(t);
			    String s = lexBuf.toString();
			    VarEntry e = (symTable.get(s));
			    if (e == null){
			    	e = new VarEntry(s, ID);
			    	symTable.put(s, e);
			    }
			    tokenVal = e;
			    return e.token;
			}
			else if (t == EOF || t == '~')
				// added ~ as backup because ctrl + d/z doesn't work reliably for me
				return DONE;
			// Otherwise...
			return t;
		}
	}

	int holdChar = EOF; // one char buffer to make ungetChar possible

	int getChar(){
		int c;
		// First check buffer
		if (holdChar != EOF){
			c = holdChar;
			holdChar = EOF;
			return c;
		}
		// Otherwise, read one character from in_reader and return it
		try{
			c = in_reader.read();
		} catch (IOException e){
			System.out.println("Trouble reading character");
			c = EOF;
		}
		return c;
	}

	void ungetChar(int c){
		// push c back onto input stream
		holdChar = c;
	}
}