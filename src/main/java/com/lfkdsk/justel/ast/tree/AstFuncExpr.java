/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.lfkdsk.justel.ast.tree;

import com.lfkdsk.justel.ast.base.AstList;
import com.lfkdsk.justel.ast.base.AstNode;

import java.util.List;

/**
 * Created by liufengkai on 2017/7/27.
 */
public class AstFuncExpr extends AstList {
    public AstFuncExpr(List<AstNode> children) {
        super(children, AstNode.FUNCTION_EXPR);
    }

    public static boolean isAstFuncExpr(AstNode child) {
        return child.childCount() >= 2 &&
                child.child(1) instanceof AstFuncArguments;
    }
}
