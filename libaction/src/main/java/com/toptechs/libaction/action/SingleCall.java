package com.toptechs.libaction.action;

/**
 * Created by jinyabo on 13/12/2017.
 *
 * 如果CallUnit验证模型中没有嵌套的验证模型，则可以直接使用SingleCall即可
 */

public class SingleCall {

    CallUnit callUnit = new CallUnit();

    public SingleCall addAction(Action action){
        clear();
        callUnit.setAction(action);
        return this;
    }


    public SingleCall addValid(Valid valid){
        //只添加无效的，验证不通过的。
        if(valid.check()){
            return this;
        }
        callUnit.addValid(valid);
        return this;
    }

    public void doCall(){

        //如果上一条valid难没有通过，是不允许再发起call的
        if(callUnit.getLastValid() != null && !callUnit.getLastValid().check() ){
            return;
        }

        //执行action
        if(callUnit.getValidQueue().size() == 0 && callUnit.getAction() != null){
            callUnit.getAction().call();
            //清空
            clear();
        }else{
            //执行验证。
            Valid valid = callUnit.getValidQueue().poll();
            callUnit.setLastValid(valid);
            valid.doValid();
        }

    }

    public void clear(){
        callUnit.getValidQueue().clear();
        callUnit.setAction(null);
        callUnit.setLastValid(null);
    }


    // 单一全局访问点
    public static SingleCall getInstance() {
        return SingletonHolder.mInstance;
    }

    // 静态内部类，第一次加载Singleton类时不会初始化mInstance，
    // 当调用getInstance()时才会初始化
    private static class SingletonHolder {
        private static SingleCall mInstance = new SingleCall();
    }
}
