import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvReader {
    private CsvReadStrategy strategy;

    public CsvReader(CsvReadStrategy strategy) {
        this.strategy = strategy;
    }

    public <B> List<B> read(Path path, Class<B> beanType) {
        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<B> cb = new CsvToBeanBuilder<B>(reader)
                .withType(beanType)
                .build();

            List<B> beanList = cb.parse();
            return strategy.construct(beanList);
        } catch (Exception e) {
            System.out.println("Error while reading beans from " + path);
        }
    }
}
