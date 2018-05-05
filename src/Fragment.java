import java.io.Serializable;

public class Fragment implements Serializable {

    // Fragment Attributes

    String filename = null;             // File to which the fragment belongs
    String fragment_filename = null;    // File to which the fragment belongs
    byte[] bytes = null;                // Bytes contained by the fragment
    int seqno;                          // Sequence no. of fragment
    long size;                          // Size of fragment
    String username;

    // Initialize fragment parameters
    public Fragment (String filename, byte[] bytes, int seqno, String username) {
        this.filename = filename;
        this.bytes = bytes;
        this.seqno = seqno;
        this.size = bytes.length;
        this.fragment_filename = username + "_" + filename + "_" + seqno + ".dfs";
        this.username = username;
    }

    public byte[] getBytes () {
        return bytes;
    }

    public String getFragment_filename() {
        return fragment_filename;
    }
}