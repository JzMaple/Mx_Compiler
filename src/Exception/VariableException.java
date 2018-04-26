package Exception;

public class VariableException extends MyException {
    public VariableException(String _message) {
        message = "Variable Error: " + _message;
        ++ exception_number;
        exception_list.add(this);
    }
}
