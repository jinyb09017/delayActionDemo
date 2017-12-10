package com.toptechs.libaction.action;

/**
 * Created by jinyabo on 8/12/2017.
 */

public interface Valid {

    /**
     * 是否满足检验器的要求，如果不满足的话，则执行doAction方法。如果满足，则执行目标action
     * @return
     */
    boolean check();

    void doValid();
}
