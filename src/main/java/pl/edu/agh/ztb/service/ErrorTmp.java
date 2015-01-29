package pl.edu.agh.ztb.service;

import pl.edu.agh.ztb.mod2.model.*;

import java.sql.Timestamp;

/**
 * Created by Marek on 2015-01-29.
 */
public class ErrorTmp extends pl.edu.agh.ztb.mod2.model.Error {
    public ErrorTmp() {
        super(1, 1, new Timestamp(1231312), "1");
    }
}
