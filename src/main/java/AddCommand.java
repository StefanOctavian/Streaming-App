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
            .build();
        AppManager.getInstance().addStream(stream);
    }

    @Override
    public void execute() {
        // get the data from the command
        String[] args = data.getArgs();
        if (args.length < 5) {
            System.out.println("Invalid command");
            return;
        }

        int streamType = Integer.parseInt(args[0]);
        int streamId = Integer.parseInt(args[1]);
        int streamGenre = Integer.parseInt(args[2]);
        long streamLength = Long.parseLong(args[3]);
        String streamName = args[4];

        addStream(streamType, streamId, streamGenre, streamLength, streamName);
    }
}
