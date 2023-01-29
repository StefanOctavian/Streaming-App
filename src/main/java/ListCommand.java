public class ListCommand extends Command {
    public ListCommand(CommandData data) {
        super(data);
    }
    
    @Override
    public void execute() {
        AppManager manager = AppManager.getInstance();
        Listable listable = null;

        User user = manager.getUser(data.getId());
        Streamer streamer = manager.getStreamer(data.getId());

        listable = (user != null) ? user : streamer;
        if (listable == null)
            return;

        listable.list();
    }
}
