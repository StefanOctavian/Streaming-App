import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Predicate;

public class Recommender {
    private User user;
    private String type;

    public Recommender(User user, String type) {
        this.user = user;
        this.type = type;
    }

    public void customRecommend(final int maxRecs, Set<Streamer> streamers,
    Comparator<Stream> comparator, Predicate<Stream> filter) {
        Queue<Stream> orderedStreams = new PriorityQueue<>(comparator.reversed());
        for (Streamer streamer : streamers) {
            List<Stream> streamerStreams = new ArrayList<>(streamer.getStreams());
            streamerStreams.sort(comparator.reversed());
            int count = maxRecs;
            int size = streamerStreams.size();
            for (int i = 0; (i < size) && (count > 0); ++i) {
                Stream stream = streamerStreams.get(i);
                if (filter == null || !filter.test(stream)) {
                    orderedStreams.add(stream);
                    --count;
                }
            }
        }

        List<Stream> streams = new ArrayList<>();
        for (int i = 0; (i < maxRecs) && (!orderedStreams.isEmpty()); ++i)
            streams.add(orderedStreams.remove());

        JSONWriter writer = new JSONWriter();
        writer.writeToStdout(streams);
    }

    public void customRecommend(final int maxRecs, Set<Streamer> streamers,
    Comparator<Stream> comparator) {
        customRecommend(maxRecs, streamers, comparator, null);
    }

    public void recommendByPrefference() {
        Set<Stream> listenedStreams = new HashSet<>(user.getStreams());
        Set<Streamer> streamers = new HashSet<>();
        Collections.addAll(streamers, 
            user.getStreams().stream().map(stream ->
                AppManager.getInstance().getStreamer(stream.getStreamerId())
            ).filter(streamer -> 
                streamer.getType().toString().equals(this.type)
            ).toArray(Streamer[]::new)
        );

        customRecommend(5, streamers, 
            Comparator.naturalOrder(), 
            listenedStreams::contains
        );
    }

    public void recommendBySurprise() {
        Set<Streamer> listenedStreamers = new HashSet<>();
        Collections.addAll(listenedStreamers, 
            user.getStreams().stream().map(stream ->
                AppManager.getInstance().getStreamer(stream.getStreamerId())
            ).toArray(Streamer[]::new)
        );

        Set<Streamer> streamers = new HashSet<>();
        Collections.addAll(streamers, 
            AppManager.getInstance().getStreamers().stream().filter(streamer ->
                streamer.getType().toString().equals(this.type) &&
                !listenedStreamers.contains(streamer)
            ).toArray(Streamer[]::new)
        );

        customRecommend(3, streamers, (s1, s2) -> {
            int dateDiff = s1.getDateAdded().compareTo(s2.getDateAdded());
            if (dateDiff != 0)
                return dateDiff;
            return s1.compareTo(s2);
        });
    }
}
