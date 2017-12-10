package com.toptechs.libaction.action;


import com.toptechs.libaction.annotation.Interceptor;
import com.toptechs.libaction.exp.ValidException;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by jinyabo on 8/12/2017.
 */

public class ActionManager {

    static ActionManager instance = new ActionManager();

    public static ActionManager instance() {

        return instance;
    }

    Stack<CallUnit> delaysActions = new Stack<>();

    /**
     * 根据条件判断，是否要执行一个action
     *
     * @param callUnit
     */
    public void postCallUnit(CallUnit callUnit) {

        //清除所有的actions
        delaysActions.clear();
        //执行check
        callUnit.check();
        //如果全部满足，则直接跳转目标方法
        if (callUnit.getValidQueue().size() == 0) {
            callUnit.getAction().call();
        } else {
            //加入到延迟执行体中来
            delaysActions.push(callUnit);

            Valid valid = callUnit.getValidQueue().peek();
            callUnit.setLastValid(valid);
            //是否会有后置任务
            valid.doValid();

        }
    }


    /**
     * 通过反射注解来组装(但是这个前提是无参的构造方法才行)
     *
     * @param action
     */
    public void postCallUnit(Action action) {
        Class clz = action.getClass();
        try {
            Method method = clz.getMethod("call");
            Interceptor interceptor = method.getAnnotation(Interceptor.class);
            Class<? extends Valid>[] clzArray = interceptor.value();
            CallUnit callUnit = new CallUnit(action);
            for (Class cla : clzArray) {
                callUnit.addValid((Valid) cla.newInstance());
            }

            postCallUnit(callUnit);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * 重新检查
     */
    public void checkValid() {

        if (delaysActions.size() > 0) {
            CallUnit callUnit = delaysActions.peek();

            if (callUnit.getLastValid().check() == false) {
                throw new ValidException(String.format("you must pass through the %s,and then reCall()", callUnit.getLastValid().getClass().toString()));

            }

            if (callUnit != null) {
                Queue<Valid> validQueue = callUnit.getValidQueue();

                validQueue.remove(callUnit.getLastValid());
                //valid已经执行完了，则表示此delay已经检验完了--执行目标方法
                if (validQueue.size() == 0) {
                    callUnit.getAction().call();
                    //把这个任务移出
                    delaysActions.remove(callUnit);
                } else {

                    Valid valid = callUnit.getValidQueue().peek();
                    callUnit.setLastValid(valid);
                    //是否会有后置任务
                    valid.doValid();
                }
            }
        }
    }



}
