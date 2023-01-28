import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class IntegerStreamTypeConverter extends AbstractBeanField<Stream, Integer> {
    @Override
    protected Stream.Type convert(String value) throws CsvDataTypeMismatchException {
        try {
            int type = Integer.parseInt(value);
            if (type < 1 || type > Stream.Type.COUNT)
                throw new CsvDataTypeMismatchException("Invalid type");
            return Stream.Type.fromInt(type);
        } catch (NumberFormatException e) {
            throw new CsvDataTypeMismatchException("Invalid type");
        }
    }
}
