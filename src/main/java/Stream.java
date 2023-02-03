import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "streamerName", "noOfListenings", "length", "dateAdded" })
public class Stream implements Comparable<Stream> {
    private final int id;
    private final Type type;
    private final Genre genre;
    private final int streamerId;
    private final long length;
    private final long dateAdded;
    private String name;
    private long numStreams;

    public enum Type {
        SONG, PODCAST, AUDIOBOOK;

        public static final int COUNT = 3;
        private static final Type[] VALUES = Type.values();

        public static Type fromInt(int i) {
            return VALUES[i - 1];
        }
    }

    public interface Genre {
        enum Music implements Genre {
            POP, LATIN, HOUSE, DANCE, TRAP;

            public static final int COUNT = 5;
            private static final Music[] VALUES = Music.values();

            public static Music fromInt(int i) {
                return VALUES[i - 1];
            }
        }

        enum Podcast implements Genre {
            DOCUMENTARY, CELEBRITIES, TECH;

            public static final int COUNT = 3;
            private static final Podcast[] VALUES = Podcast.values();

            public static Podcast fromInt(int i) {
                return VALUES[i - 1];
            }
        }

        enum Audiobook implements Genre {
            FICTION, PERSONAL_DEVELOPMENT, CHILDREN;

            public static final int COUNT = 3;
            private static final Audiobook[] VALUES = Audiobook.values();

            public static Audiobook fromInt(int i) {
                return VALUES[i - 1];
            }
        }
    }

    public Stream(StreamBuilder builder) {
        this.id = builder.getId();
        this.type = builder.getType();
        this.genre = builder.getGenre();
        this.streamerId = builder.getStreamerId();
        this.length = builder.getLength();
        this.dateAdded = builder.getDateAdded();
        this.name = builder.getName();
        this.numStreams = builder.getNumStreams();
    }

    public int getId() {
        return id;
    }

    @JsonIgnore
    public Type getType() {
        return type;
    }

    @JsonIgnore
    public Genre getGenre() {
        return genre;
    }

    @JsonIgnore
    public int getStreamerId() {
        return streamerId;
    }

    @JsonProperty("streamerName")
    public String getStreamerName() {
        AppManager appManager = AppManager.getInstance();
        return appManager.getStreamer(streamerId).getName();
    }

    @JsonProperty("length")
    public String getLengthString() {
        if (length >= 3600)
            return String.format("%02d:%02d:%02d",
                (length / 3600), (length % 3600) / 60, (length % 60)
            );
        return String.format("%02d:%02d", (length / 60), (length % 60));
    }

    public long getLength() {
        return length;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public Date getDateAdded() {
        return Date.from(Instant.ofEpochSecond(dateAdded));
    }

    public String getName() {
        return name;
    }

    @JsonProperty("noOfListenings")
    public long getNumStreams() {
        return numStreams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incNumStreams() {
        ++numStreams;
    }

    @Override
    public String toString() {
        return "Stream [id=" + id + ", type=" + type + ", genre=" + genre +
                ", streamerId=" + streamerId + ", length=" + length + ", dateAdded=" +
                dateAdded + ", name=" + name + ", numStreams=" + numStreams + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stream other = (Stream) obj;
        return (id == other.id);
    }

    @Override
    public int compareTo(Stream o) {
        return Integer.compare(id, o.id);
    }
}
