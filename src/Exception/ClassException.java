package Exception;

public class ClassException extends MyException {
    public ClassException(String _message) {
        message = "Class Error: " + _message;
        ++ exception_number;
        exception_list.add(this);
    }
}
