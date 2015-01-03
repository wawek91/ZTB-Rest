package pl.edu.agh.ztb.model;

/**
 * Created by wawek on 03.01.15.
 */

public class FakeClass {

    private int someNumber;
    private String someString;

    public FakeClass(){

    }

    public FakeClass(int someNumber, String someString) {
        this.someNumber = someNumber;
        this.someString = someString;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public int getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(int someNumber) {
        this.someNumber = someNumber;
    }

    @Override
    public String toString() {
        return "someNumber = " + someNumber +
                " someString = " + someString;
    }
}
