public class RecommendCommand extends Command {
    public RecommendCommand(CommandData data) {
        super(data);
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
        recommender.recommend();
    }
}
