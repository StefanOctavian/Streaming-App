public class SimpleReadStrategy implements CsvReadStrategy {
    @Override
    public <B> void addBeans(List<B> beans) {
        AppManager.getInstance().addUsers((List<User>) beans);
    }
}
