package NasmTranslate;

public class RegX86 {
    private String name;
    private int index;

    private RegX86(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static RegX86 get(int index) {
        switch (index) {
            case 0 : return rax;
            case 1 : return rcx;
            case 2 : return rdx;
            case 3 : return rbx;
            case 4 : return rsp;
            case 5 : return rbp;
            case 6 : return rsi;
            case 7 : return rdi;
            case 8 : return r8;
            case 9 : return r9;
            case 10 : return r10;
            case 11 : return r11;
            case 12 : return r12;
            case 13 : return r13;
            case 14 : return r14;
            case 15 : return r15;
            default : return null;
        }
    }

    public static RegX86 getParameter(int index) {
        switch (index) {
            case 0 : return rdi;
            case 1 : return rsi;
            case 2 : return rdx;
            case 3 : return rcx;
            case 4 : return r8;
            case 5 : return r9;
            default : return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public static RegX86 rax = new RegX86("rax", 0);
    public static RegX86 rcx = new RegX86("rcx", 1);
    public static RegX86 rdx = new RegX86("rdx", 2);
    public static RegX86 rbx = new RegX86("rbx", 3);
    public static RegX86 rsp = new RegX86("rsp", 4);
    public static RegX86 rbp = new RegX86("rbp", 5);
    public static RegX86 rsi = new RegX86("rsi", 6);
    public static RegX86 rdi = new RegX86("rdi", 7);
    public static RegX86 r8 = new RegX86("r8", 8);
    public static RegX86 r9 = new RegX86("r9", 9);
    public static RegX86 r10 = new RegX86("r10", 10);
    public static RegX86 r11 = new RegX86("r11", 11);
    public static RegX86 r12 = new RegX86("r12", 12);
    public static RegX86 r13 = new RegX86("r13", 13);
    public static RegX86 r14 = new RegX86("r14", 14);
    public static RegX86 r15 = new RegX86("r15", 15);
}
