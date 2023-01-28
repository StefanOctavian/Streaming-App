public class CommandParseException extends Exception {
    public CommandParseException(String line) {
        super("Malformed command: " + line);
    }

    public CommandParseException(String line, String reason) {
        super("Malformed command: " + line + " (" + reason + ")");
    }
}
