package pl.edu.agh.ztb.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wawek on 03.01.15.
 */

@XmlRootElement
public class FakeClass {

    private int someNumber;
    private String someString;

    public FakeClass(){

    }

    public FakeClass(int someNumber, String someString) {
        this.someNumber = someNumber;
        this.someString = someString;
    }
}
