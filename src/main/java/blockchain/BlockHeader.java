package blockchain;


public class BlockHeader {

    public void setTimeStamp(StringBuilder timeStamp) {
        this.timeStamp = timeStamp;
    }

    private StringBuilder timeStamp;
    private static final int version=0;
    private String merkleRoot;
    private static int difficultyTarget;
    private int nonce;
    private String previousHash;

    public StringBuilder getTimeStamp() {
        return this.timeStamp;
    }
    public static int getDifficultyTarget()
    {
        return difficultyTarget;
    }
    public int getNonce()
    {
        return this.nonce;
    }
    public static void setDifficultyTarget(int difficultyTarget) {
        BlockHeader.difficultyTarget = difficultyTarget;
    }
    public void setNonce(int nonce)
    {
        this.nonce= nonce;
    }
    public void setMerkleRoot(String merkleRoot)
    {
        this.merkleRoot = merkleRoot;
    }
    public String getMerkleRoot()
    {
        return this.merkleRoot;
    }
    public static int getVersion()
    {
        return BlockHeader.version;
    }
    public void setPreviousHash(String previousHash) {
        this.previousHash=previousHash;
    }
}
