package users;

import wallet.Wallet;

import java.math.BigDecimal;
import java.util.UUID;


public class User {
    private UUID uuid;
    private String name;

    private Wallet wallet = new Wallet();

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

    public User (String name) {
        this.uuid = UUID.randomUUID();
        this.name = name;
    }

    public User(UUID uuid, String name)
    {
        this.uuid = uuid;
        this.name = name;
    }

    static public User createFirstUser(String name) {
        User user = new User(name);
        user.getWallet().addBalance(BigDecimal.valueOf(1000));
        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
