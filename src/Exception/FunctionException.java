package Exception;

public class FunctionException extends MyException {
    public FunctionException(String _message) {
        message = "Function Error: " + _message;
        ++ exception_number;
        exception_list.add(this);
    }
}
