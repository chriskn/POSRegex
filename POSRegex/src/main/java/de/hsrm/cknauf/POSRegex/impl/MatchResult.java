package de.hsrm.cknauf.POSRegex.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatchResult {

	private Map<String, List<List<Token>>> tokensByGroup;
	private List<Token> tokens;
	private boolean matched;

	MatchResult() {
		tokensByGroup = new HashMap<>();
		tokens = new LinkedList<>();
	}

	/**
	 * @param tokensByGroup
	 * @param tokens
	 */
	MatchResult(Map<String, List<List<Token>>> tokensByGroup, List<Token> tokens) {
		this.tokensByGroup = tokensByGroup;
		this.tokens = tokens;
	}

	/**
	 * @return the tokensByGroup
	 */
	public Map<String, List<List<Token>>> getTokensByGroup() {
		return tokensByGroup;
	}

	/**
	 * @param tokensByGroup
	 *            the tokensByGroup to set
	 */
	public void setTokensByGroup(Map<String, List<List<Token>>> tokensByGroup) {
		this.tokensByGroup = tokensByGroup;
	}

	/**
	 * @return the tokens
	 */
	public List<Token> getTokens() {
		return tokens;
	}

	/**
	 * @param tokens
	 *            the tokens to set
	 */
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	/**
	 * @return the matched
	 */
	public boolean isMatched() {
		return matched;
	}

	/**
	 * @param matched
	 *            the matched to set
	 */
	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
		result = prime * result
				+ ((tokensByGroup == null) ? 0 : tokensByGroup.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MatchResult)) {
			return false;
		}
		MatchResult other = (MatchResult) obj;
		if (tokens == null) {
			if (other.tokens != null) {
				return false;
			}
		} else if (!tokens.equals(other.tokens)) {
			return false;
		}
		if (tokensByGroup == null) {
			if (other.tokensByGroup != null) {
				return false;
			}
		} else if (!tokensByGroup.equals(other.tokensByGroup)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchResult:[\nmatched=").append(matched)
				.append("\ntokensByGroup=").append(tokensByGroup)
				.append("\ntokens=").append(tokens).append("]");
		return builder.toString();
	}

}
