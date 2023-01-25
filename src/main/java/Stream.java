public class Stream {
    private final int id;
    private final Type type;
    private final Genre genre;
    private final int streamerId;
    private final long length;
    private final long dateAdded;
    private String name;
    private long numStreams;

    public enum Type {
        MUSIC, PODCAST, AUDIOBOOK;
        
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

    public Type getType() {
        return type;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getStreamerId() {
        return streamerId;
    }

    public long getLength() {
        return length;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public String getName() {
        return name;
    }

    public long getNumStreams() {
        return numStreams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStream() {
        ++numStreams;
    }
}
