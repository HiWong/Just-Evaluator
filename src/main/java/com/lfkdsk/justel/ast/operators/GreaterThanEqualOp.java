/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.operators;

import com.lfkdsk.justel.ast.base.AstNode;
import com.lfkdsk.justel.context.JustContext;
import com.lfkdsk.justel.token.SepToken;

import java.util.List;

import static com.lfkdsk.justel.utils.NumberUtils.computeValue;
import static com.lfkdsk.justel.utils.TypeUtils.isComparable;
import static com.lfkdsk.justel.utils.TypeUtils.isNumber;

/**
 * >=
 * Created by liufengkai on 2017/7/31.
 */
public class GreaterThanEqualOp extends OperatorExpr {
    public GreaterThanEqualOp(List<AstNode> children) {
        super(children);
    }

    @Override
    public String functionName() {
        return SepToken.GTE_TOKEN.getText();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object eval(JustContext env) {
        Object left = leftChild().eval(env);
        Object right = rightChild().eval(env);

        if (isNumber(left) && isNumber(right)) {
            return computeValue(left) >= computeValue(right);
        } else if (isComparable(left) && isComparable(right)) {
            return ((Comparable) left).compareTo(right) >= 0;
        }

        return super.eval(env);
    }
}