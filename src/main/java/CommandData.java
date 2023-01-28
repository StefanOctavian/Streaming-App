import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandData {
    private int id = -1;
    private String name = "";
    private String[] args = new String[0];
    private int argsCount = 0;

    public CommandData(String line) throws CommandParseException {
        Pattern pattern = Pattern.compile("(\\d+) ([A-Z]+)(?: (.+))?");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches())
            throw new CommandParseException(line);

        try {
            this.id = Integer.parseInt(matcher.group(0));
        } catch (NumberFormatException e) {
            throw new CommandParseException(line, "Invalid ID");
        }

        this.name = matcher.group(1);
        String commandArgsString = matcher.group(2);

        if (commandArgsString != null) {
            this.args = commandArgsString.split(" ");
            this.argsCount = args.length;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getArgs() {
        return args;
    }

    public int getArgsCount() {
        return argsCount;
    }
}
