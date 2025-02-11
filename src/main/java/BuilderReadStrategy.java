import java.util.ArrayList;
import java.util.List;

public class BuilderReadStrategy<B, R> implements CsvReadStrategy<B, R> {
    @FunctionalInterface
    public interface BuildFunction<B, R> {
        public R apply(B bean);
    }

    private BuildFunction<B, R> builder;

    public BuilderReadStrategy(BuildFunction<B, R> builder) {
        this.builder = builder;
    }

    @Override
    public List<R> construct(List<B> beans) {
        List<R> result = new ArrayList<>();
        for (B bean : beans)
            result.add(builder.apply(bean));
        return result;
    }
}
