import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProiectPOO {

    private static Path streamersPath;
    private static Path streamsPath;
    private static Path usersPath;
    private static Path commandsPath;
    private static final String RESOURCES = "src/main/resources/";

    public static void handleUsers() {
        AppManager appManager = AppManager.getInstance();

        CsvReadStrategy<User, User> strategy = new SimpleReadStrategy<>();
        CsvReader<User, User> reader = new CsvReader<>(strategy);
        List<User> users = reader.read(usersPath, User.class);
        appManager.addUsers(users);
    }

    public static void handleStreams() {
        AppManager appManager = AppManager.getInstance();

        CsvReadStrategy<CsvStreamBuilderAdapter, Stream> strategy = new BuilderReadStrategy<>(
                CsvStreamBuilderAdapter::build);
        CsvReader<CsvStreamBuilderAdapter, Stream> reader = new CsvReader<>(strategy);
        List<Stream> streams = reader.read(streamsPath, CsvStreamBuilderAdapter.class);
        appManager.addStreams(streams);
    }

    public static void handleStreamers() {
        AppManager appManager = AppManager.getInstance();

        CsvReadStrategy<Streamer, Streamer> strategy = new SimpleReadStrategy<>();
        CsvReader<Streamer, Streamer> reader = new CsvReader<>(strategy);
        List<Streamer> streamers = reader.read(streamersPath, Streamer.class);
        appManager.addStreamers(streamers);
    }

    public static void handleCommands() {
        AppManager appManager = AppManager.getInstance();

        try {
            List<String> lines = Files.readAllLines(commandsPath, StandardCharsets.UTF_8);
            appManager.addCommands(lines);
        } catch (Exception e) {
            System.out.println("Error while reading commands");
            e.printStackTrace();
        }

        appManager.executeCommands();
    }

    public static void main(String[] args) {
        String input = "inputs2/";
        String test = "test2/";
        args = new String[] { 
            input + "streamers.csv", 
            input + "streams.csv", 
            input + "users.csv", 
            test + "commands.txt"
        };
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        if (args.length != 4) {
            System.out.println("Invalid number of arguments");
            return;
        }

        streamersPath = Paths.get(RESOURCES, args[0]);
        streamsPath = Paths.get(RESOURCES, args[1]);
        usersPath = Paths.get(RESOURCES, args[2]);
        commandsPath = Paths.get(RESOURCES, args[3]);

        handleUsers();
        handleStreamers();
        handleStreams();
        handleCommands();
    }
}