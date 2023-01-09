public interface Tokenizer {
    /**
     * Checks if there are more tokens to be read.
     * @return true if there are more tokens to be read, false otherwise.
     */
    boolean hasNext();

    /**
     * @return the next token.
     */
    String peek();

    /**
     * @return the next token.
     * effects: remove the next token from the stream.
     */
    String consume();

    /**
     * To check if the next token is the expected one.
     * @param s the expected token.
     * @return true if the next token is the expected one, false otherwise.
     */
    boolean peek(String s);

    /**
     * To consume the next token if it is the expected one.
     * @param s the expected token.
     * @return true if the next token is the expected one, false otherwise.
     */
    boolean consume(String s) throws SyntaxError;
}
