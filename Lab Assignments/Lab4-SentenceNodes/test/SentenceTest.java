import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This interface represents a sentence made of nodes.
 */
public class SentenceTest {
  private Sentence sentenceOne;
  private Sentence sentenceTwo;
  private Sentence sentenceThree;
  private Sentence sentenceFour;
  private Sentence sentenceFive;
  private Sentence sentenceSix;
  private Sentence sentenceSeven;


  @Before
  public void setup() {
    sentenceOne = new WordNode("this", new WordNode(
            "sentence", new WordNode(
            "has", new WordNode(
            "punctuation", new PunctuationNode(
            "!", new EmptyNode())))));

    sentenceTwo = new WordNode("this", new WordNode(
            "is", new WordNode(
            "a", new WordNode(
            "good", new WordNode(
            "sentence", new EmptyNode())))));

    sentenceThree = new WordNode("we", new WordNode(
            "are", new WordNode(
            "champions", new EmptyNode())));

    sentenceFour = new WordNode("but", new WordNode(
            "not", new WordNode(
            "really", new EmptyNode())));

    sentenceFive = new WordNode("something", new EmptyNode());
    sentenceSix = new EmptyNode();
    sentenceSeven = new WordNode("wow", new EmptyNode());

  }

  // Tests get number of words for empty node
  @Test
  public void testGetNumberOfWordsEmptyNode() {
    assertEquals(0, sentenceSix.getNumberOfWords());
  }

  // Tests get number of words for single node
  @Test
  public void testGetNumberOfWordsSingleNode() {
    assertEquals(1, sentenceFive.getNumberOfWords());
  }

  // Tests get number of words for many node
  @Test
  public void testGetNumberOfWordsManyNodes() {
    assertEquals(5, sentenceTwo.getNumberOfWords());
  }

  // Tests get longest word for a empty node
  @Test
  public void testGetLongestWordEmptyNode() {
    assertEquals("", sentenceSix.longestWord());
  }

  // Tests get longest word for a single node
  @Test
  public void testGetLongestWordSingleNode() {
    assertEquals("something", sentenceFive.longestWord());
  }

  // Tests get longest word for multiple nodes
  @Test
  public void testGetLongestWordMultipleNodes() {
    assertEquals("sentence", sentenceTwo.longestWord());
  }

  // Tests empty node toString method.
  @Test
  public void testToStringEmptyNode() {
    assertEquals("", sentenceSix.toString());
  }

  // Tests one node toString method without punctuation.
  @Test
  public void testToStringSingleNode() {
    assertEquals(" something", sentenceFive.toString());
  }

  // Tests multiple nodes toString method with punctuation.
  @Test
  public void testToStringMultipleNodesNoPunctuation() {
    assertEquals(" this is a good sentence", sentenceTwo.toString());
  }

  // Tests cloning empty nodes.
  @Test
  public void testCloneEmptyNode() {
    assertEquals("", sentenceSix.clone().toString());
  }

  // Tests cloning multiple nodes without punctuation
  @Test
  public void testCloneSentenceNoPunctuation() {
    assertEquals(" this is a good sentence", sentenceTwo.clone().toString());
  }

  // Tests cloning multiple nodes without punctuation
  @Test
  public void testCloneSentenceWithPunctuation() {
    assertEquals(" this sentence has punctuation!", sentenceOne.clone().toString());
  }

  // Tests merging two empty nodes
  @Test
  public void testMergeSentenceEmptyNodes() {
    assertEquals("", sentenceSix.merge(sentenceSix).toString());
  }

  // Tests empty with single node.
  @Test
  public void testMergeSentenceEmptyAndWord() {
    assertEquals(" something", sentenceSix.merge(sentenceFive).toString());
  }

  // Tests merging two sentences without punctuation.
  @Test
  public void testMergeSentenceNoPunctuation() {
    assertEquals(" we are champions but not really",
            sentenceThree.merge(sentenceFour).toString());
  }

  // Tests merging two sentences with punctuation.
  @Test
  public void testMergeSentenceWithPunctuation() {
    assertEquals(" wow this sentence has punctuation!",
            sentenceSeven.merge(sentenceOne).toString());
  }

}