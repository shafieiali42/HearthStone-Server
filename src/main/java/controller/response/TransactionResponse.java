package controller.response;

public class TransactionResponse extends Response {


    private boolean successful;
    private String transactionType;


    public TransactionResponse(boolean successful,String transactionType) {
        this.successful = successful;
        this.transactionType=transactionType;
    }


    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
