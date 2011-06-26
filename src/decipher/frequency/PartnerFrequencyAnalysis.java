package decipher.frequency;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PartnerFrequencyAnalysis {

	protected String cipherText;
	protected Map<Character, Boolean> foundChars = new HashMap<Character, Boolean> ();
	
	protected Map<Character, Map<Character, Frequency>> frequencies = new HashMap<Character, Map<Character, Frequency>>();
	
	public PartnerFrequencyAnalysis(String cipherText) {
		this.cipherText = cipherText;
		this.start();
	}

	private void start() {
		Character previous = null;
		CharacterIterator it = new StringCharacterIterator(cipherText);
		
		// Iterate over the characters in the forward direction
		for (char ch=it.first(); ch != CharacterIterator.DONE; ch=it.next()) {
			if (!foundChars.containsKey(ch)) {
				foundChars.put(ch, true);
			}
			
			if (previous != null) {
				Map<Character, Frequency> submap;
				Frequency frequency;
				
				if (frequencies.containsKey(previous)) {
					submap = frequencies.get(previous);
				}
				else {
					submap = new HashMap<Character, Frequency>();
					frequencies.put(previous, submap);
				}
				
				if (submap.containsKey(ch)) {
					frequency = submap.get(ch);
				}
				else {
					frequency = new Frequency();
					submap.put(ch, frequency);
				}
				
				frequency.increment();
			}
			
			previous = ch;
		}
	}

	public void printResults() {
		System.out.println("Printing character relation table");
		String line = "  ";
		
		for (Entry<Character, Boolean> entry: foundChars.entrySet()) {
			line += entry.getKey() + "  ";
		}
		
		System.out.println(line);
		
		Map<Character, Frequency> sublist;
		
		for (Entry<Character, Boolean> entry: foundChars.entrySet()) {
			line = entry.getKey() + ":";
			sublist = frequencies.get(entry.getKey());
			
			for (Entry<Character, Boolean> subentry: foundChars.entrySet()) {
				if (sublist.containsKey(subentry.getKey())) {
					String valueString = Integer.toString(sublist.get(subentry.getKey()).getCount());
					line += valueString + (valueString.length() == 1 ? "  " : " ");
				}
				else {
					line += "0  ";
				}
			}
			
			System.out.println(line);
		}
		
		/*for (Map.Entry<Character, Map<Character, Frequency>> line: frequencies.entrySet()) {
			
			String line1 = "   ";
			String line2 = line.getKey() + ": ";
			
			for (Map.Entry<Character, Frequency> entry: line.getValue().entrySet()) {
				line1 += entry.getKey() + " ";
				line2 += entry.getValue().getCount() + " ";
			}
			
			System.out.println(line1);
			System.out.println(line2);
			System.out.println();
		}*/
		
		System.out.println();
	}

}
