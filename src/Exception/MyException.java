package Exception;

import sun.util.resources.cldr.mas.CalendarData_mas_KE;

import java.util.Vector;

public class MyException extends Exception {
    protected static int exception_number = -1;
    private static int exception_print_number = -1;
    protected static Vector<MyException> exception_list = new Vector<>();
    protected String message;
    protected int line;

    public MyException() {}

    public String getMessage() {
        return message;
    }

    public void printException(){
        //todo: print Exception
        while (exception_print_number < exception_number) {
            ++exception_print_number;
            System.err.println(exception_list.get(exception_print_number).getMessage());
        }
    }
}
