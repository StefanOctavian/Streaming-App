import com.opencsv.bean.CsvBindByPosition;

public class CsvStreamBuilderAdapter extends StreamBuilder {
    @CsvBindByPosition(position = 0)
    private int typeAsInt;
    @CsvBindByPosition(position = 1)
    private int id;
    @CsvBindByPosition(position = 2)
    private int genreAsInt;
    @CsvBindByPosition(position = 3)
    private int streamerId;
    @CsvBindByPosition(position = 4)
    private long length;
    @CsvBindByPosition(position = 5)
    private long dateAdded;
    @CsvBindByPosition(position = 6)
    private String name;
    @CsvBindByPosition(position = 7)
    private long numStreams;

    @Override
    public Stream build() {
        super.setType(typeAsInt);
        super.setId(id);
        super.setStreamerId(streamerId);
        super.setGenre(genreAsInt);
        super.setLength(length);
        super.setDateAdded(dateAdded);
        super.setName(name);
        super.setNumStreams(numStreams);

        return super.build();
    }
}
