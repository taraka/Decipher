package decipher.main;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

import decipher.frequency.CharacterFrequencyAnalysis;
import decipher.frequency.PartnerFrequencyAnalysis;
import decipher.frequency.WordFrequencyAnalysis;

public class Decipher {
	
	protected Map<Character, Character> keyMap = new HashMap<Character, Character>();
	
	protected String cipherText =
			"IXDVMUFXLFEEFXSOQXYQVXSQTUIXWF*FMXYQVFJ*FXEFQUQXJFPTUFX" +
			"MX*ISSFLQTUQXMXRPQEUMXUMTUIXYFSSFI*MXKFJF*FMXLQXTIEUVFX" +
			"EQTEFXSOQXLQ*XVFWMTQTUQXTITXKIJ*FMUQXTQJMVX*QEYQVFQTHMX" +
			"LFVQUVIXM*XEI*XLQ*XWITLIXEQTHGXJQTUQXSITEFLQVGUQX*GXKIE" +
			"UVGXEQWQTHGXDGUFXTITXDIEUQXGXKFKQVXSIWQXAVPUFXWGXYQVXWQ" +
			"JPFVXKFVUPUQXQXSGTIESQTHGX*FXWFQFXSIWYGJTFXDQSFIXEFXGJP" +
			"UFXSITXRPQEUGXIVGHFITXYFSSFI*CXC*XSCWWFTIXSOQXCXYQTCXYI" +
			"ESFCX*FXCKVQFXVFUQTPUFXQXKI*UCXTIEUVCXYIYYCXTQ*XWCUUFTI" +
			"XLQFXVQWFXDCSQWWIXC*FXC*XDI**QXKI*IXEQWYVQXCSRPFEUCTLIX" +
			"LC*X*CUIXWCTSFTIXUPUUQX*QXEUQ**QXJFCXLQX*C*UVIXYI*IXKQL" +
			"QCX*CXTIUUQXQX*XTIEUVIXUCTUIXACEEIXSOQXTITXEPVJQCXDPIVX" +
			"LQ*XWCVFTXEPI*IXSFTRPQXKI*UQXVCSSQEIXQXUCTUIXSCEEIX*IX*";
	
	
	
	public Decipher()
	{
		this.setupKey();
		
		CharacterFrequencyAnalysis fa = new CharacterFrequencyAnalysis(cipherText);
		fa.printResults();
		
		PartnerFrequencyAnalysis pa = new PartnerFrequencyAnalysis(cipherText);
		pa.printResults();
		
		WordFrequencyAnalysis wa = new WordFrequencyAnalysis(cipherText, 2, 5);
		wa.printResults();
		
		wa = new WordFrequencyAnalysis(cipherText, 3, 4);
		wa.printResults();
		
		wa = new WordFrequencyAnalysis(cipherText, 4);
		wa.printResults();
		
		wa = new WordFrequencyAnalysis(cipherText, 5);
		wa.printResults();
		
		wa = new WordFrequencyAnalysis(cipherText, 6);
		wa.printResults();
		
		wa = new WordFrequencyAnalysis(cipherText, 7);
		wa.printResults();
		
		this.printPlainText();
	}
	
	private void printPlainText() {
		CharacterIterator it = new StringCharacterIterator(cipherText);
		String plainText = "";
		String hybrid = "";
		for (char ch=it.first(); ch != CharacterIterator.DONE; ch=it.next()) {
			if (keyMap.containsKey(ch)) {
				plainText += keyMap.get(ch);
				hybrid += keyMap.get(ch);
			}
			else {
				plainText += '_';
				hybrid += ch;
			}
		}
		
		System.out.println("Print plain text");
		System.out.println(plainText);
		System.out.println(hybrid);
		System.out.println();
	}

	private void setupKey() {
		keyMap.put('X', ' ');
		/*keyMap.put('M', 'e');
		keyMap.put('G', 'e');
		keyMap.put('C', 'e');
		keyMap.put('Z', 'e');
		keyMap.put('N', 'e');
		keyMap.put('B', 'e');*/
		
		keyMap.put('F', 'i');
		keyMap.put('I', 'a');
		keyMap.put('Q', 'e');
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Decipher();
	}

}
