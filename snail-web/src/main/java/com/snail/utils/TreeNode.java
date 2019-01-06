package com.snail.utils;

import java.util.List;

/**
 * Created by liutao on 2019/1/6.
 */
public class TreeNode {
    private String id;
    private String text;
    private Boolean selectable = Boolean.TRUE;
    private List<TreeNode> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public Boolean getSelectable() {
        return selectable;
    }

    public void setSelectable(Boolean selectable) {
        this.selectable = selectable;
    }
}