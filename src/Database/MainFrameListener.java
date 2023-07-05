package Database;

/**
 * The MainFrameListener interface defines the contract for listening to events related to the main frame.
 */
public interface MainFrameListener {

    /**
     * Called when the subject frame is closed.
     * Implementing classes should provide the necessary logic to handle this event.
     */
    void onSubjectFrameClosed();
}
