package blockchain;

import java.sql.Timestamp;
import java.util.List;

public class BlockHeader {

    private StringBuilder timeStamp;
    private int version;
    private StringBuilder merkleRoot;
    private int difficultyTarget;
    private int nonce;
    private String previousHash;
    public BlockHeader()
    {
        this.version=0;
        this.nonce=0;
    }


}
