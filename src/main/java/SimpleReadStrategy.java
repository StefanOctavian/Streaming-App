import java.util.List;

public class SimpleReadStrategy<B> implements CsvReadStrategy<B, B> {
    @Override
    public List<B> construct(List<B> beans) {
        return beans;
    }
}
