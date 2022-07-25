package blockchain;



import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import JSON.WriteFromFile;
import JSON.WriteToFile;
import users.*;

// TODO: 15.07.2022
/*
add junit tests
Transalte transactions into Strings, then connect each one of them and create hash
check if users aren't null
 */

/*
Done:
Creating users
 */


public class Main {


    public static void main(String[]Args){

        //variables to blockchain
        boolean  loop=true;
        LinkedList<Block> blockchain = new LinkedList<>();
        LinkedList<User> users = new LinkedList<>();

        //variables to menu
        Block currentBlock = new Block();
        Scanner scanner = new Scanner(System.in);
        int idNumber=0;
        int option;
        String tempUser1;
        String tempUser2;
        User user1=new User();
        User user2= new User();
        int choice;
        String tempHash;
        String name;
        //set first block
        String hashOption;
        blockchain.add(new Block());
        blockchain.get(0).setFirst();
        currentBlock=blockchain.get(0);
        blockchain.get(0).setId(0);
        idNumber++;

        System.out.println(user1.returnID());

        while(loop)
        {
            //menu options
            System.out.println("Current number of blocks:" + blockchain.size());
            System.out.println("Current number of users: "+users.size());
            System.out.println("Menu:");
            System.out.println("1. Add transactions to block");
            System.out.println("2. Show block by hash: ");
            System.out.println("3. Show block by index: ");
            System.out.println("4. Create users");
            System.out.println("5. Show user by name");
            System.out.println("6. Show last block");
            System.out.println("7. Send users to UsersDoc.json");
            System.out.println("8. Write from file");
            System.out.println("9. Quit");
            System.out.println("Choice: ");

            //create new block
            Block newBlock = new Block();
            option = scanner.nextInt();
            
            switch (option)
            {
                case 1->
                {
                    //works
                    newBlock.addTransactionToBlock(users);
                    blockchain.add(newBlock);
                    blockchain.get(blockchain.size()-1).setId(idNumber);
                    idNumber++;
                    blockchain.get(blockchain.size()-1).setBlockHeaderTimestamp();
                    //setPreviousBlockHeaderHash
                    blockchain.get(blockchain.size()-1).
                            setBlockHeaderPreviousBlockHeaderHashCode(
                                    blockchain.get(blockchain.size()-2));
                    //setPreviousBlockHash
                    blockchain.get(blockchain.size()-1).
                            setPreviousHash(
                                    blockchain.get(blockchain.size()-2));
                }
                case 2->
                {
                    System.out.println("Sign hash: ");
                    scanner.nextLine();
                    tempHash=scanner.nextLine();
                    for(Block block : blockchain)
                    {
                        if(block.getHash().equals(tempHash))
                        {
                            block.seeBlock();
                        }
                    }
                }
                case 3->
                {
                    System.out.println("Choose by ID: ");
                    choice= scanner.nextInt();
                    for(Block block : blockchain)
                    {
                        if(block.getId()==choice)
                        {
                            block.seeBlock();
                        }
                    }
                }
                case 4->
                {
                    //do poprawy
                    users.add(new User());
                    if(user1.returnID()==3)
                    {
                        //set first balance for 1000
                        users.get(0).setName();
                        users.get(0).setFirstBalance();
                        break;
                    }
                    //set others balance for 0
                    users.get(users.size()-1).setName();
                    users.get(users.size()-1).setWallet();

                }
                case 5->
                {
                    //works
                    //to take enter
                    scanner.nextLine();
                    System.out.println("Name: ");
                    name = scanner.nextLine();
                    for(User user : users)
                    {
                        if(user.getName().equals(name))
                        {
                            System.out.println("Name: "+ user.getName());
                            System.out.println("Wallet: "+ user.getWalletBalance());
                            System.out.println("Transactions: ");
                            for(Transaction transaction :user.getWalletTransactions())
                            {
                                System.out.println("Name: "+transaction.getTransactionName());
                                System.out.println("Value: "+transaction.getTransactionValue());
                                System.out.println("Time: "+transaction.getTimestamp());
                            }
                        }
                    }
                }
                case 6->
                {
                    blockchain.get(blockchain.size()-1).seeBlock();
                }
                case 7->
                {
                    try {
                        WriteToFile.writeToFile(users);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case 8->
                {

                    WriteFromFile.writeFromFile(users);
                }
                case 9->
                {
                    loop=false;
                }
            }
        }

    }


}
