public interface Parser {
    /**
     *Attempts tp parse the token steam give to this parser.
     * @throws: SyntaxError if the token stream cannot be parsed.
     */
    Expr parse() throws SyntaxError;
}
