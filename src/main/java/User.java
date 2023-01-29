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
    private List<Integer> streams;
    
    public User() {}

    public User(int id, String name, List<Integer> streams) {
        this.id = id;
        this.name = name;
        this.streams = streams;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getStreams() {
        return streams;
    }

    @Override
    public void list() {
        AppManager manager = AppManager.getInstance();
        List<Stream> streamList = new ArrayList<>();
        for (int streamId : this.streams) {
            Stream stream = manager.getStream(streamId);
            if (stream != null)
                streamList.add(stream);
        }
        JSONWriter writer = new JSONWriter();
        writer.writeToStdout(streamList);
    }
}
