package controller.response;

public class ShowWalletResponse extends Response {


    private int money;

    public ShowWalletResponse(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
