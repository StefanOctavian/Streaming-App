import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;

public class Streamer {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvCustomBindByPosition(position = 2, converter = IntegerStreamTypeConverter.class)
    private Stream.Type type;

    public Streamer(int id, String name, Stream.Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stream.Type getType() {
        return type;
    }
}
