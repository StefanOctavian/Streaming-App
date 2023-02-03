public class RecommendCommand extends Command {
    public enum Algo {
        PREFFERENCE, SURPRISE
    }
    private final Algo algorithm;

    public RecommendCommand(CommandData data, Algo algorithm) {
        super(data);
        this.algorithm = algorithm;
    }
    
    @Override
    public void execute() {
        String type = data.getArgs().get(0);
        int userId = data.getId();

        AppManager appManager = AppManager.getInstance();
        User user = appManager.getUser(userId);
        if (user == null)
            return;

        Recommender recommender = new Recommender(user, type);
        if (algorithm == Algo.PREFFERENCE)
            recommender.recommendByPrefference();
        else if (algorithm == Algo.SURPRISE)
            recommender.recommendBySurprise();
    }
}
