package de.hsrm.cknauf.POSRegex.impl;

public class Token {

	private String word;
	private String posTag;
	private String lemma;
	private int begin;
	private int end;

	/**
	 * @param word
	 * @param posTag
	 * @param lemma
	 * @param begin
	 * @param end
	 */
	Token(String word, String posTag, String lemma, int begin, int end) {
		super();
		this.word = word;
		this.posTag = posTag;
		this.lemma = lemma;
		this.begin = begin;
		this.end = end;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the posTag
	 */
	public String getPosTag() {
		return posTag;
	}

	/**
	 * @param posTag
	 *            the posTag to set
	 */
	public void setPosTag(String posTag) {
		this.posTag = posTag;
	}

	/**
	 * @return the lemma
	 */
	public String getLemma() {
		return lemma;
	}

	/**
	 * @param lemma
	 *            the lemma to set
	 */
	public void setLemma(String lemma) {
		this.lemma = lemma;
	}

	/**
	 * @return the begin
	 */
	public int getBegin() {
		return begin;
	}

	/**
	 * @param begin
	 *            the begin to set
	 */
	public void setBegin(int begin) {
		this.begin = begin;
	}

	/**
	 * @return the end
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(int end) {
		this.end = end;
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
		result = prime * result + begin;
		result = prime * result + end;
		result = prime * result + ((lemma == null) ? 0 : lemma.hashCode());
		result = prime * result + ((posTag == null) ? 0 : posTag.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		if (!(obj instanceof Token)) {
			return false;
		}
		Token other = (Token) obj;
		if (begin != other.begin) {
			return false;
		}
		if (end != other.end) {
			return false;
		}
		if (lemma == null) {
			if (other.lemma != null) {
				return false;
			}
		} else if (!lemma.equals(other.lemma)) {
			return false;
		}
		if (posTag == null) {
			if (other.posTag != null) {
				return false;
			}
		} else if (!posTag.equals(other.posTag)) {
			return false;
		}
		if (word == null) {
			if (other.word != null) {
				return false;
			}
		} else if (!word.equals(other.word)) {
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
		builder.append("Token [word=").append(word).append(", posTag=")
				.append(posTag).append(", lemma=").append(lemma)
				.append(", begin=").append(begin).append(", end=").append(end)
				.append("]");
		return builder.toString();
	}
}
