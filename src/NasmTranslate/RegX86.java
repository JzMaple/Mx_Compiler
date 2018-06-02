package NasmTranslate;

public class RegX86 {
    private String name;

    private RegX86(String name) {
        this.name = name;
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

    public static int allocNum = 10;

    public static RegX86 allocReg(int index) {
        switch (index) {
            case -1: return null;
            case 0 : return r8;
            case 1 : return r9;
            case 2 : return r10;
            case 3 : return r11;
            case 4 : return r12;
            case 5 : return r13;
            case 6 : return r14;
            case 7 : return r15;
            case 8 : return rsi;
            case 9 : return rdi;
            default : return null;
        }
    }

    public static int getParaOrd(int index) {
        switch (index) {
            case -1: return -1;
            case 0 : return 4;
            case 1 : return 5;
            case 2 : return 6;
            case 3 : return 6;
            case 4 : return 6;
            case 5 : return 6;
            case 6 : return 6;
            case 7 : return 6;
            case 8 : return 1;
            case 9 : return 0;
            default : return -1;
        }
    }

    public static RegX86 rax = new RegX86("rax");
    public static RegX86 rcx = new RegX86("rcx");
    public static RegX86 rdx = new RegX86("rdx");
    public static RegX86 rbx = new RegX86("rbx");
    public static RegX86 rsp = new RegX86("rsp");
    public static RegX86 rbp = new RegX86("rbp");
    public static RegX86 rsi = new RegX86("rsi");
    public static RegX86 rdi = new RegX86("rdi");
    public static RegX86 r8 = new RegX86("r8");
    public static RegX86 r9 = new RegX86("r9");
    public static RegX86 r10 = new RegX86("r10");
    public static RegX86 r11 = new RegX86("r11");
    public static RegX86 r12 = new RegX86("r12");
    public static RegX86 r13 = new RegX86("r13");
    public static RegX86 r14 = new RegX86("r14");
    public static RegX86 r15 = new RegX86("r15");
}
