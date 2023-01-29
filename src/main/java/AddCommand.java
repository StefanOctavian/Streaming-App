import java.util.List;

public class AddCommand extends Command {
    public AddCommand(CommandData data) {
        super(data);
        // the reciever of this command is the app manager
    }

    private void addStream(int type, int streamId, int genre, long length, String name) {
        Stream stream = new StreamBuilder()
            .setType(type)
            .setId(streamId)
            .setGenre(genre)
            .setLength(length)
            .setName(name)
            .setStreamerId(data.getId())
            .build();
        AppManager.getInstance().addStream(stream);
    }

    @Override
    public void execute() {
        // get the data from the command
        List<String> args = data.getArgs();
        if (args.size() < 5) {
            System.out.println("Invalid command");
            return;
        }

        int streamType = Integer.parseInt(args.get(0));
        int streamId = Integer.parseInt(args.get(1));
        int streamGenre = Integer.parseInt(args.get(2));
        long streamLength = Long.parseLong(args.get(3));
        String streamName = args.get(4);

        addStream(streamType, streamId, streamGenre, streamLength, streamName);
    }
}
