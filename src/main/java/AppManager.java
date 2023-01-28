import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("java:S125")
public class AppManager {
    private static AppManager instance = null;
    private static Map<Integer, Streamer> streamers = new HashMap<>();
    private static List<Stream> streams = new ArrayList<>();
    private static Map<Integer, User> users = new HashMap<>();
    private static Deque<String> commands = new LinkedList<>();

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addUsers(List<User> newUsers) {
        for (User user : newUsers)
            this.addUser(user);
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public void addStream(Stream stream) {
        streams.add(stream);
    }

    public void addStreams(List<Stream> newStreams) {
        streams.addAll(newStreams);
    }

    public void addStreamer(Streamer streamer) {
        streamers.put(streamer.getId(), streamer);
    }

    public void addStreamers(List<Streamer> newStreamers) {
        for (Streamer streamer : newStreamers)
            this.addStreamer(streamer);
    }

    public Streamer getStreamer(int id) {
        return streamers.get(id);
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void addCommands(List<String> newCommands) {
        commands.addAll(newCommands);
    }

    public void addImmediateCommand(String command) {
        commands.addFirst(command);
    }

    public void executeCommands() {
        while (!commands.isEmpty()) {
            String commandLine = commands.removeFirst();

            // parse the line
            CommandData data = null;
            try {
                data = new CommandData(commandLine);
            } catch (CommandParseException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                continue;
            }

            Command command = null;
            
            switch (data.getName()) {
                case "ADD" -> command = new AddCommand(data);
                // case "LIST" -> command = new ListCommand(data);
                // case "DELETE" -> command = new DeleteCommand(data);
                // case "LISTEN" -> command = new ListenCommand(data);
                // case "RECCOMEND" -> command = new RecommendCommand(data);
                // case "SURPRISE" -> command = new SurpriseCommand(data);
                default -> System.out.println("Invalid command");
            }

            if (command != null)
                command.execute();
        }
    }
}
