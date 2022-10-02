package blockchain;


public class BlockHeader {

    private StringBuilder timeStamp;
    private final int version=0;
    private StringBuilder merkleRoot;
    private int difficultyTarget;
    private int nonce;
    private String previousHash;

    public StringBuilder getTimeStamp() {
        return this.timeStamp;
    }
    public int getDifficultyTarget()
    {
        return this.difficultyTarget;
    }
    public String getPreviousHash()
    {
        return this.previousHash;
    }
    public int getNonce()
    {
        return this.nonce;
    }
    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }
}
