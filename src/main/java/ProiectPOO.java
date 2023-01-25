import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProiectPOO {

    private static Path streamersPath;
    private static Path streamsPath;
    private static Path usersPath;
    private static Path commandsPath;

    public static void handleUsers() {
        AppManager appManager = AppManager.getInstance();

        CsvReadStrategy<User, User> strategy = new SimpleReadStrategy<>();
        CsvReader<User, User> reader = new CsvReader<>(strategy);
        List<User> users = reader.read(usersPath, User.class);
        appManager.addUsers(users);
    }

    public static void handleStreams() {
        AppManager appManager = AppManager.getInstance();

        CsvReadStrategy<CsvStreamBuilderAdapter, Stream> strategy =
            new BuilderReadStrategy<>(CsvStreamBuilderAdapter::build);
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

    public static void main(String[] args) {
        if (args == null) {
            System.out.println("Nothing to read here");
            return;
        }

        if (args.length != 4) {
            System.out.println("Invalid number of arguments");
            return;
        }

        streamersPath = Paths.get(args[0]);
        streamsPath = Paths.get(args[1]);
        usersPath = Paths.get(args[2]);
        commandsPath = Paths.get(args[3]);

        handleUsers();
    }
}