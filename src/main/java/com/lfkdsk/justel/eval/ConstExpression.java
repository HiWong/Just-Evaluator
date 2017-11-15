package com.lfkdsk.justel.eval;

import com.lfkdsk.justel.context.JustContext;
import org.jetbrains.annotations.NotNull;

/**
 * Constant Expression
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/8/11.
 * @see com.lfkdsk.justel.ast.tree.AstProgram
 * @see com.lfkdsk.justel.JustEL
 */
public final class ConstExpression implements Expression {

    private final Object constVal;

    public ConstExpression(@NotNull Object constVal) {
        this.constVal = constVal;
    }

    @Override
    public Object eval(JustContext context) {
        return constVal;
    }
}
