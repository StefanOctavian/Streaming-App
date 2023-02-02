import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Recommender {
    private User user;
    private String type;

    public Recommender(User user, String type) {
        this.user = user;
        this.type = type;
    }

    public void recommend() {
        Set<Stream> listenedStreams = new HashSet<>(user.getStreams());
        List<Streamer> streamers = user.getStreams().stream().map(stream ->
            AppManager.getInstance().getStreamer(stream.getStreamerId())
        ).filter(streamer -> streamer.getType().toString().equals(this.type)).toList();

        Queue<Stream> orderedStreams = new PriorityQueue<>();
        for (Streamer streamer : streamers) {
            Queue<Stream> streamerStreams = new PriorityQueue<>(streamer.getStreams());
            int count = 5;
            while (count > 0 && !streamerStreams.isEmpty()) {
                Stream stream = streamerStreams.remove();
                if (!listenedStreams.contains(stream)) {
                    orderedStreams.add(stream);
                    --count;
                }
            }
        }

        List<Stream> streams = new ArrayList<>();
        for (int i = 0; (i < 5) && (!orderedStreams.isEmpty()); ++i)
            streams.add(orderedStreams.remove());

        JSONWriter writer = new JSONWriter();
        writer.writeToStdout(streams);
    }
}
