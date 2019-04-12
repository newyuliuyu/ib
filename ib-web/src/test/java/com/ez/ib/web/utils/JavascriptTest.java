package com.ez.ib.web.utils;

import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * ClassName: JavascriptTest <br/>
 * Function:  ADD FUNCTION. <br/>
 * Reason:  ADD REASON(可选). <br/>
 * date: 18-12-28 下午2:55 <br/>
 *
 * @author liuyu
 * @version v1.0
 * @since JDK 1.7+
 */
public class JavascriptTest {

    @Test
    public void test() throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        engine.eval("function a(){return 1;}");
        engine.eval("function b(){return 2;}");
        Invocable inv2 = (Invocable) engine;
        Object obj1  = inv2.invokeFunction("a");
        Object obj2  = inv2.invokeFunction("b");
        System.out.println();
    }
}
