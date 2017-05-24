/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class MessageManager {
    private UserTuple userTuple;
    private MessageType message;

    public UserTuple getUserTuple() {
        return userTuple;
    }

    public void setUserTuple(UserTuple userTuple) {
        this.userTuple = userTuple;
    }

    public MessageType getMessage() {
        return message;
    }

    public void setMessage(MessageType message) {
        this.message = message;
    }
}
