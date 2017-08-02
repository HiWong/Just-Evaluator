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
import com.lfkdsk.justel.context.JustContext;

import java.util.List;

/**
 * Ast Program
 * Program is just a wrapper of Ast.
 *
 * @author liufengkai
 *         Created by liufengkai on 2017/7/26.
 */
public class AstProgram extends AstList {
    public AstProgram(List<AstNode> children) {
        super(children, PROGRAM);
    }

    private AstNode program() {
        return child(0);
    }

    @Override
    public Object eval(JustContext env) {
        return program().eval(env);
    }
}
