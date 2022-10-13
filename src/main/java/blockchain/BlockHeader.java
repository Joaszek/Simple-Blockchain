package blockchain;


public class BlockHeader {

    public void setTimeStamp(StringBuilder timeStamp) {
        this.timeStamp = timeStamp;
    }

    private StringBuilder timeStamp;
    private final int version=0;
    private String merkleRoot;
    private static int difficultyTarget;
    private int nonce;
    private String previousHash;

    public StringBuilder getTimeStamp() {
        return this.timeStamp;
    }
    public int getDifficultyTarget()
    {
        return difficultyTarget;
    }
    public String getPreviousHash()
    {
        return this.previousHash;
    }
    public int getNonce()
    {
        return this.nonce;
    }
    public static void setDifficultyTarget(int difficultyTarget) {
        difficultyTarget = difficultyTarget;
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
}
