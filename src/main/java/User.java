import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;

public class User {
    @CsvBindByPosition(position = 0)
    private int id;
    @CsvBindByPosition(position = 1)
    private String name;
    @CsvBindAndSplitByPosition(position = 2, elementType = Integer.class, splitOn = " ")
    private List<Integer> streams;
    
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
}
