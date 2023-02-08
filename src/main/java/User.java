import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class User implements Listable {
    @CsvBindByName(column = "id")
    private int id;
    @CsvBindByName(column = "name")
    private String name;
    @CsvBindAndSplitByName(column = "streams", elementType = Integer.class, splitOn = " ")
    private List<Integer> streamIds;
    private List<Stream> streams;
    
    public User() {}

    public User(int id, String name, List<Stream> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    // This method is called after the CSV file is read
    // Users are initialized before streams, so we can't get the streams
    // at the time of initialization
    public void init() {
        if (streamIds == null)
            return;

        streams = new ArrayList<>();
        for (int sId : streamIds) {
            Stream stream = AppManager.getInstance().getStream(sId);
            if (stream != null)
                streams.add(stream);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Stream> getStreams() {
        return streams;
    }

    @Override
    public void list() {
        JSONWriter writer = new JSONWriter();
        writer.writeToStdout(streams);
    }

    public void removeStreamFromHistory(int id) {
        streamIds.remove(id);
        streams.remove(new StreamBuilder().setId(id).build());
    }

    public void listen(Stream stream) {
        streams.add(stream);
    }
}
