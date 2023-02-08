import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public class Streamer implements Listable {
    @CsvBindByName(column = "id")
    private int id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvCustomBindByName(
        column = "streamerType", 
        converter = IntegerStreamTypeConverter.class
    )
    private Stream.Type type;

    private List<Stream> streams = new ArrayList<>();

    public Streamer() {}
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

    public List<Stream> getStreams() {
        return streams;
    }

    public void addStream(Stream stream) {
        streams.add(stream);
    }

    @Override
    public void list() {
        JSONWriter writer = new JSONWriter();
        writer.writeToStdout(streams);
    }

    public void removeStream(Stream stream) {
        streams.remove(stream);
    }
}
