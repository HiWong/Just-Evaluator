/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.context.JustMapContext;
import org.junit.jupiter.api.Test;

import static com.lfkdsk.justel.parser.JustParserImplTest.runExpr;

/**
 * Created by liufengkai on 2017/8/2.
 */
class DotExprTest {

    class O {
        String ffff = "sss";

        public String lfk() {
            return "fffffff";
        }
    }

    @Test
    void testDotExpr() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        runExpr("lfkdsk.lfk()", true, context);
    }

    @Test
    void testDotValueExpr() {
        JustContext context = new JustMapContext();
        context.put("lfkdsk", new O());
        runExpr("lfkdsk.ffff", true, context);
    }
}