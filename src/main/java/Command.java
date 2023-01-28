public abstract class Command {
    protected CommandData data;

    public abstract void execute();

    protected Command(CommandData data) {
        this.data = data;
    }
}
