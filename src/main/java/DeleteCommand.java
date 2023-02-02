// Command to remove all data of a stream
public class DeleteCommand extends Command {
    public DeleteCommand(CommandData data) {
        super(data);
    }
    
    @Override
    public void execute() {
        AppManager appManager = AppManager.getInstance();
        
        appManager.removeStream(data.getId());
    }
}
