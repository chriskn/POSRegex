package de.hsrm.cknauf.POSRegex.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hsrm.cknauf.POSRegex.api.IPOSRegexPattern;
import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.tokensregex.TokenSequenceMatcher;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class POSRegexPattern implements IPOSRegexPattern {

	private static final StanfordCoreNLP NLP_PIPELINE;

	private static final String GROUP_NAME_PATTERN = "\\$[A-Za-z_][A-Za-z0-9_]*";

	static {
		Properties properties = new Properties();
		properties.put("annotators", "tokenize, ssplit, pos, lemma");
		NLP_PIPELINE = new StanfordCoreNLP(properties);
	}

	@Override
	public boolean matches(String sentence, String regex) {
		TokenSequencePattern pattern = TokenSequencePattern.compile(regex);
		List<CoreLabel> tokens = getTokens(sentence, regex); 
		TokenSequenceMatcher matcher = pattern.getMatcher(tokens);
		return  matcher.matches();
	}
	
	@Override
	public MatchResult match(String sentence, String regex) {
		TokenSequencePattern pattern = TokenSequencePattern.compile(regex);
		List<CoreLabel> tokens = getTokens(sentence, regex);
		TokenSequenceMatcher matcher = pattern.matcher(tokens);
		List<String> groupNames = extractGroupNames(regex);
		MatchResult result = new MatchResult();
		result.setMatched(matcher.matches());
		while (matcher.find()) {
			for (String groupName : groupNames) {
				@SuppressWarnings("unchecked")
				List<CoreLabel> labelsForGroup = (List<CoreLabel>) matcher
						.groupNodes(groupName);
				List<Token> createdTokens = createTokensFromLabels(labelsForGroup);
				String gName = groupName.replace("$", "");
				Map<String, List<List<Token>>> tokensByGroup = result
						.getTokensByGroup();
				if (tokensByGroup.containsKey(gName)) {
					tokensByGroup.get(gName).add(createdTokens);
				} else {
					List<List<Token>> tokenTokenList = new ArrayList<>();
					tokenTokenList.add(createdTokens);
					tokensByGroup.put(gName, tokenTokenList);
				}
			}
		}
		for (CoreLabel label : tokens) {
			result.getTokens().add(createTokenFromLabel(label));
		}
		return result;
	}

	private List<CoreLabel> getTokens(String sentence, String regex) {
		Annotation document = new Annotation(sentence);
		NLP_PIPELINE.annotate(document);
		CoreMap _sentence = document.get(SentencesAnnotation.class).get(0);
		return new ArrayList<CoreLabel>(_sentence.get(TokensAnnotation.class));
	}

	private List<String> extractGroupNames(String pattern) {
		Pattern groupNamePattern = Pattern.compile(GROUP_NAME_PATTERN);
		Matcher matcher = groupNamePattern.matcher(pattern);
		List<String> groupNames = new ArrayList<>();
		while (matcher.find()) {
			groupNames.add(matcher.group());
		}
		return groupNames;
	}

	private static Token createTokenFromLabel(CoreLabel label) {
		String word = label.originalText();
		String posTag = label.get(PartOfSpeechAnnotation.class);
		String lemma = label.get(LemmaAnnotation.class);
		int begin = label.beginPosition();
		int end = label.endPosition();
		return new Token(word, posTag, lemma, begin, end);
	}

	private static List<Token> createTokensFromLabels(List<CoreLabel> labels) {
		List<Token> tokensForGroup = new LinkedList<>();
		for (CoreLabel label : labels) {
			tokensForGroup.add(createTokenFromLabel(label));
		}
		return tokensForGroup;
	}
}
