import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandData {
    private int id = -1;
    private String name = "";
    private List<String> args = new ArrayList<>();

    public CommandData(String line) throws CommandParseException {
        Pattern pattern = Pattern.compile("^(\\d+) ([A-Z]+)(?: (.+))?$");
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches())
            throw new CommandParseException(line);

        try {
            this.id = Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException e) {
            throw new CommandParseException(line, "Invalid ID " + matcher.group(1));
        }

        this.name = matcher.group(2);

        String argsString = matcher.group(3);
        if (argsString == null)
            return;

        Pattern argPattern = Pattern.compile("(\\d+|[\\w\\s]+)(?: |$)");
        Matcher argMatcher = argPattern.matcher(argsString);

        while (argMatcher.find()) {
            this.args.add(argMatcher.group(1));
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }
}
