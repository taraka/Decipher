package decipher.frequency;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;

public class CharacterFrequencyAnalysis {

	protected String cipherText;
	
	protected Map<Character, Frequency> frequencies = new HashMap<Character, Frequency>();
	
	public CharacterFrequencyAnalysis(String cipherText) {
		this.cipherText = cipherText;
		this.start();
	}
	
	private void start()
	{
		CharacterIterator it = new StringCharacterIterator(cipherText);
		
		// Iterate over the characters in the forward direction
		for (char ch=it.first(); ch != CharacterIterator.DONE; ch=it.next()) {
			Frequency frequency;
			
			if (frequencies.containsKey(ch)) {
				frequency = frequencies.get(ch);
			}
			else {
				frequency = new Frequency();
				frequencies.put(ch, frequency);
			}
			
			frequency.increment();
		}
	}
	
	public void printResults()
	{
		System.out.println("Printing character frequency analysis");
		
		for (Map.Entry<Character, Frequency> entry: frequencies.entrySet()) {
			float value = ((entry.getValue().getCount() / (float)cipherText.length()) * 100);
			
			System.out.println(entry.getKey() + ": " + value + "%");
		}
		
		System.out.println();
	}
}
