import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

/**
 * @author Elena Hardon
 * @date 4/10/17.
 */
public class MessageType {
    public static final String REQUEST_RECEIVED = "receive";
    public static final String REQUEST_APPROVED = "approved";
    public static final String REQUEST_DENIED = "denied";
    public static final String REQUEST_FINALISED = "done";
    public static final String REQUEST_SENT = "sent";

    private Map messageMap = new HashMap<String, String>();
    private String subject;

    public MessageType() {
        try {
            this.initFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initFiles() throws Exception {
        String jsonText = null;
        BufferedReader br = new BufferedReader(new FileReader("messages.json"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.getProperty("line.separator"));
                line = br.readLine();
            }
            jsonText = sb.toString();
        } finally {
            br.close();
        }

        JSONObject obj = new JSONObject(jsonText);

        this.subject = obj.getJSONObject("messages").getString("email-subject");

        Pattern pattern = Pattern.compile("[a-zA-Z0-9.,;:\"'()!?\\[\\] ]+");
        Matcher matcher;

        for (Iterator it = obj.getJSONObject("messages").keys(); it.hasNext(); ) {
            String key = (String) it.next();
            String message = obj.getJSONObject("messages").getString(key);

            matcher = pattern.matcher(message);

            if (!matcher.matches()) {
                throw new Exception("Invalid JSON message format!");
            }

            this.messageMap.put(key, message);
        }
    }

    public String getMessage(String type) {
        return (String) this.messageMap.get(type);
    }

    public String getSubject() {
        return this.subject;
    }
}
