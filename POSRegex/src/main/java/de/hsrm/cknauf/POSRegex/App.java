package de.hsrm.cknauf.POSRegex;

import de.hsrm.cknauf.POSRegex.api.IPOSRegexPattern;
import de.hsrm.cknauf.POSRegex.impl.MatchResult;
import de.hsrm.cknauf.POSRegex.impl.POSRegexPattern;

public class App {
	
	public static void main(String[] args) {
		String regex = "(?$noun([pos:VBG]?[pos:NN|pos:NNS|pos:NNP|pos:NNPS]){1,2})"; 
		String sentence = "The printing module will provide the client with the ability to print documents."; 
		IPOSRegexPattern pattern = new POSRegexPattern();
		MatchResult result = pattern.match(sentence, regex); 

		System.out.println(pattern.matches(sentence, regex)); 
		System.out.println(result);
	}
}