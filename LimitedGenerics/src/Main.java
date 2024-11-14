public class Main{
    public static void main(String[] arg){
        Account<String> silv = new SilverAccount<String>("MaxSilver", 50_000);
        Account<String> gold = new GoldemAccount<String>("MarkGolden", 17_000);
        System.out.println("Silver user balance: " + silv.getBalance());
        System.out.println("Golden user balance: " + gold.getBalance());

        Transaction<Account, Account> transaction1 = new Transaction<Account,Account>(silv, gold, 9_000);
        transaction1.execute();
        System.out.println("Silver user balance: " + silv.getBalance());
        System.out.println("Golden user balance: " + gold.getBalance());

        Transaction<Account, Account> transaction2 = new Transaction<Account,Account>(gold, silv, 2_500);
        transaction2.execute();
        System.out.println("Silver user balance: " + silv.getBalance());
        System.out.println("Golden user balance: " + gold.getBalance());
    }
}

class Transaction <SENDER extends Account, RECEIVER extends Account>{
    final private SENDER from;
    final private RECEIVER to;
    final private Integer sum;

    Transaction(SENDER from, RECEIVER to, Integer sum){
        this.from = from;
        this.to = to;
        this.sum = sum;
    }
    public void execute(){
        if(from.getBalance() > this.sum) {
            from.withdrawMoney(sum);
            to.depositMoney(sum);
            System.out.printf("Средства в размере %d были переведены от %s к %s\n", this.sum, from.getID(), to.getID());
        } else {
            System.out.printf("Средства в размере %d не были перевед, this.sum, from.getID(), to.getID()ены от %s к %s ввиду недостаточности средств\n", this.sum, from.getID(), to.getID());
        }
    }
}

interface Account <G>{
    Integer getBalance();
    void setID(G ID);
    G getID();
    void withdrawMoney(Integer sum);
    void depositMoney(Integer sum);
}

class SilverAccount<G> implements Account<G>{
    G ID;
    Integer balance;

    SilverAccount(G ID, Integer balance){
        this.ID = ID;
        this.balance = balance;
    }

    @Override
    public G getID(){
        return ID;
    }
    @Override
    public Integer getBalance() {
        return this.balance;
    }
    @Override
    public void setID(G ID) {
        this.ID = ID;
    }

    @Override
    public void withdrawMoney(Integer sum) {
        this.balance -= sum;
    }

    @Override
    public void depositMoney(Integer sum) {
        this.balance += (sum);
    }
}

class GoldemAccount<G> implements Account<G>{
    G ID;
    Integer balance;

    GoldemAccount(G ID, Integer balance){
        this.ID = ID;
        this.balance = balance;
    }
    @Override
    public G getID(){
        return ID;
    }

    @Override
    public Integer getBalance() {
        return this.balance;
    }

    @Override
    public void setID(G ID) {
        this.ID = ID;
    }

    @Override
    public void withdrawMoney(Integer sum) {
        this.balance -= sum;
    }

    @Override
    public void depositMoney(Integer sum) {
        this.balance += (sum * 2);
    }
}