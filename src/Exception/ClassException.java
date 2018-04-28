package Exception;

public class ClassException extends MyException {
    public ClassException(String _message, int _line) {
        message = "CLASS ERROR - " + _message;
        line = _line;
        ++ exception_number;
        exception_list.add(this);
    }
}
