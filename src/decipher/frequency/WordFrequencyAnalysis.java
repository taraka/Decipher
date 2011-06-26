package decipher.frequency;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class WordFrequencyAnalysis {

	private String cipherText;
	private int wordLength;
	private int limit = 2;
	
	private Map<String, Frequency> frequencies = new HashMap<String, Frequency>();
	
	public WordFrequencyAnalysis(String cipherText, int wordLength) {
		this.cipherText = cipherText;
		this.wordLength = wordLength;
		this.start();
	}
	
	public WordFrequencyAnalysis(String cipherText, int wordLength, int limit) {
		this.cipherText = cipherText;
		this.wordLength = wordLength;
		this.limit = limit;
		this.start();
	}

	private void start()
	{
		for (int i=0; i<cipherText.length()-wordLength; i++) {
			
			String word = cipherText.substring(i, i+wordLength);
			
			Frequency frequency;
			
			if (frequencies.containsKey(word)) {
				frequency = frequencies.get(word);
			}
			else {
				frequency = new Frequency();
				frequencies.put(word, frequency);
			}
			
			frequency.increment();
		}
	}
	
	public void printResults()
	{
		System.out.println("Printing " + wordLength + " letter word frequency analysis");
		
		for (Entry<String, Frequency> entry: frequencies.entrySet()) {
			if (entry.getValue().getCount() > this.limit) {
				System.out.println(entry.getKey() + ": " + entry.getValue().getCount());
			}
		}
		
		System.out.println();
	}
}
