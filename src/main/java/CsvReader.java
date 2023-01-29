import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReader<B, R> {
    private CsvReadStrategy<B, R> strategy;

    public CsvReader(CsvReadStrategy<B, R> strategy) {
        this.strategy = strategy;
    }

    public List<R> read(Path path, Class<B> beanType) {
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<B> cb = new CsvToBeanBuilder<B>(reader)
                .withType(beanType)
                .build();

            List<B> beanList = cb.parse();
            return strategy.construct(beanList);
        } catch (IOException e) {
            System.out.println("Error while reading beans from " + path);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
