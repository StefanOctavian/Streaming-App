import java.util.List;

public interface CsvReadStrategy<B, R> {
    public List<R> construct(List<B> beans);
}
