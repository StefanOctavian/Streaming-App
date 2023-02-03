public class StreamBuilder {
    private int id;
    private Stream.Type type;
    private Stream.Genre genre;
    private int streamerId;
    private long length;
    private long dateAdded;
    private String name;
    private long numStreams;

    private Stream stream;

    public void reset() {
        this.id = 0;
        this.type = Stream.Type.SONG;
        this.genre = Stream.Genre.Music.POP;
        this.streamerId = 0;
        this.length = 0;
        this.dateAdded = 0;
        this.name = "";
        this.numStreams = 0;
        this.stream = null;
    }

    public static boolean isGenreValid(Stream.Type type, int genre) {
        if (genre < 1)
            return false;
        switch (type) {
            case SONG:
                return genre <= Stream.Genre.Music.COUNT;
            case PODCAST:
                return genre <= Stream.Genre.Podcast.COUNT;
            case AUDIOBOOK:
                return genre <= Stream.Genre.Audiobook.COUNT;
            default:
                return false;
        }
    }

    public StreamBuilder() {
        this.reset();
    }

    public StreamBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public StreamBuilder setType(int type) {
        if (type < 1 || type > Stream.Type.COUNT)
            throw new IllegalArgumentException("Invalid type");
        this.type = Stream.Type.fromInt(type);
        return this;
    }

    private Stream.Genre getGenreFromType(Stream.Type type, int genre) {
        if (!isGenreValid(type, genre))
            throw new IllegalArgumentException("Invalid genre");

        switch (type) {
            case SONG:
                return Stream.Genre.Music.fromInt(genre);
            case PODCAST:
                return Stream.Genre.Podcast.fromInt(genre);
            case AUDIOBOOK:
                return Stream.Genre.Audiobook.fromInt(genre);
            default:
                // This case is impossible
                return null;
        }
    }

    public StreamBuilder setGenre(int genre) {
        this.genre = getGenreFromType(this.type, genre);
        return this;
    }

    public StreamBuilder setStreamerId(int streamerId) {
        this.streamerId = streamerId;
        return this;
    }

    public StreamBuilder setLength(long length) {
        this.length = length;
        return this;
    }

    public StreamBuilder setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public StreamBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StreamBuilder setNumStreams(long numStreams) {
        this.numStreams = numStreams;
        return this;
    }

    public int getId() {
        return id;
    }

    public Stream.Type getType() {
        return type;
    }

    public Stream.Genre getGenre() {
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

    public Stream build() {
        if (stream == null)
            stream = new Stream(this);
        return stream;
    }
}
