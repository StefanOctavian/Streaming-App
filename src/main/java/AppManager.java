import java.util.ArrayList;
import java.util.List;

public class AppManager {
    private static AppManager instance = null;
    private static List<Streamer> streamers = new ArrayList<>();
    private static List<Stream> streams = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    private AppManager() {}

    public static AppManager getInstance() {
        if (instance == null)
            instance = new AppManager();
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addStream(Stream stream) {
        streams.add(stream);
    }

    public void addStreamer(Streamer streamer) {
        streamers.add(streamer);
    }

    public void addUsers(List<User> newUsers) {
        users.addAll(newUsers);
    }

    public void addStreams(List<Stream> newStreams) {
        streams.addAll(newStreams);
    }

    public void addStreamers(List<Streamer> newStreamers) {
        streamers.addAll(newStreamers);
    }
}
