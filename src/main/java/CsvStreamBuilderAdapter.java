import com.opencsv.bean.CsvBindByName;

public class CsvStreamBuilderAdapter extends StreamBuilder {
    @CsvBindByName(column = "streamType")
    private int csvType;
    @CsvBindByName(column = "id")
    private int csvId;
    @CsvBindByName(column = "streamGenre")
    private int csvGenre;
    @CsvBindByName(column = "streamerId")
    private int csvStreamerId;
    @CsvBindByName(column = "length")
    private long csvLength;
    @CsvBindByName(column = "dateAdded")
    private long csvDateAdded;
    @CsvBindByName(column = "name")
    private String csvName;
    @CsvBindByName(column = "noOfStreams")
    private long csvNumStreams;

    @Override
    public Stream build() {
        super.setType(csvType);
        super.setId(csvId);
        super.setStreamerId(csvStreamerId);
        super.setGenre(csvGenre);
        super.setLength(csvLength);
        super.setDateAdded(csvDateAdded);
        super.setName(csvName);
        super.setNumStreams(csvNumStreams);

        return super.build();
    }
}
