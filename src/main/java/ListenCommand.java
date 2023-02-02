public class ListenCommand extends Command {
    public ListenCommand(CommandData data) {
        super(data);
    }
    
    @Override
    public void execute() {
        AppManager appManager = AppManager.getInstance();
        
        int userId = data.getId();
        int streamId = Integer.parseInt(data.getArgs().get(0));

        User user = appManager.getUser(userId);
        Stream stream = appManager.getStream(streamId);
        if (user == null || stream == null)
            return;

        user.listen(stream);
        stream.incNumStreams();
    }
}
