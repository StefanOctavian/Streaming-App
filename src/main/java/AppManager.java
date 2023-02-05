import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("java:S125")
public class AppManager {
    private static AppManager instance = null;
    private static Map<Integer, Streamer> streamers = new HashMap<>();
    private static Map<Integer, Stream> streams = new HashMap<>();
    private static Map<Integer, User> users = new HashMap<>();
    private static Deque<String> commands = new LinkedList<>();

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    // users

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

    // streams

    class CorruptedStreamException extends RuntimeException {
        public CorruptedStreamException(String message) {
            super("Dirty or corrupted stream data: " + message);
        }

        public CorruptedStreamException(int streamerId) {
            this("Streamer with id " + streamerId + " does not exist");
        }
    }

    public void addStream(Stream stream) {
        Streamer streamer = streamers.get(stream.getStreamerId());
        if (streamer == null)
            throw new CorruptedStreamException(stream.getStreamerId());

        streams.put(stream.getId(), stream);
        streamer.addStream(stream);
    }

    public void addStreams(List<Stream> newStreams) {
        for (Stream stream : newStreams)
            this.addStream(stream);
    }

    public Stream getStream(int id) {
        return streams.get(id);
    }

    public void removeStream(int id) {
        Stream stream = streams.get(id);
        if (stream == null)
            return;
        Streamer streamer = streamers.get(stream.getStreamerId());
        if (streamer == null)
            throw new CorruptedStreamException(stream.getStreamerId());

        streamer.removeStream(stream);
        for (User user : users.values())
            user.removeStreamFromHistory(id);

        streams.remove(id);
    }

    // streamers

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

    public Set<Streamer> getStreamers() {
        return new HashSet<>(streamers.values());
    }

    // commands

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
                case "ADD":
                    command = new AddCommand(data);
                    break;
                case "LIST":
                    command = new ListCommand(data);
                    break;
                case "DELETE":
                    command = new DeleteCommand(data);
                    break;
                case "LISTEN":
                    command = new ListenCommand(data);
                    break;
                case "RECOMMEND":
                    command = new RecommendCommand(data, RecommendCommand.Algo.PREFFERENCE);
                    break;
                case "SURPRISE":
                    command = new RecommendCommand(data, RecommendCommand.Algo.SURPRISE);
                    break;
                default:
                    System.out.println("Invalid command");
            }

            if (command != null)
                command.execute();
        }
    }

    public void initUsers() {
        for (User user : users.values())
            user.init();
    }

    public void clearAll() {
        users.clear();
        streams.clear();
        streamers.clear();
        commands.clear();
    }
}
