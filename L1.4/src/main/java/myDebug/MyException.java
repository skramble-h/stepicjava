package myDebug;

/**
 * Created by skramble.h
 */
public class MyException extends Exception {

}

class Test1{
    public static void main(String[] args) {
        Test1 test1 = new Test1();
        //test1.go();
        test1.getException();
    }

    Exception getException(){ return new MyException(); }

    public void go(){
        try {
            throw new MyException();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}

