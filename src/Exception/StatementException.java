package Exception;

public class StatementException extends MyException{
    public StatementException(String _message) {
        message = "Statement error: " + _message;
        ++ exception_number;
        exception_list.add(this);
    }
}
