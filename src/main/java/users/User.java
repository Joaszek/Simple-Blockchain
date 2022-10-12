package users;

import wallet.Wallet;

import java.util.UUID;


public class User {
    private UUID uuid;
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    private Wallet wallet;
    public User()
    {
        UserManager userManager = new UserManager();
        userManager.setWallet(this);
        this.setUuid(UUID.randomUUID());

    }
    public User(boolean isFirst)
    {
        UserManager manager = new UserManager();
        manager.setWallet(this);
        manager.setFirstBalance(this);
        this.setUuid(UUID.randomUUID());
        this.setName("Satoshi Nakamoto");
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
