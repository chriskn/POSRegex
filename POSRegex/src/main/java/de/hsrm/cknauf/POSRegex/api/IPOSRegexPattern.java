package de.hsrm.cknauf.POSRegex.api;

import de.hsrm.cknauf.POSRegex.impl.MatchResult;

public interface IPOSRegexPattern {
		
	boolean matches(String text, String regex); 
	
	MatchResult match(String text, String regex); 
	
}
