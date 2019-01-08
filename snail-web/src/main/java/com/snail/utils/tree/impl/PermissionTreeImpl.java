package com.snail.utils.tree.impl;


import com.snail.model.m.entity.MPermission;
import com.snail.utils.tree.Node;
import com.snail.utils.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限树
 */
public class PermissionTreeImpl implements Tree<List<Node>> {
    private List<MPermission> nodes;
    private Boolean isChecked = Boolean.FALSE;

    /**
     * 是否被选中
     * @param nodes
     * @param isChecked
     */
    public PermissionTreeImpl(List<MPermission> nodes,Boolean isChecked) {
        this.nodes = nodes;
        this.isChecked = isChecked;
    }

    public List<Node> buildTree() {
        List<Node> treeList = new ArrayList<Node>();
        for (MPermission node : nodes) {
            Long id = node.getId();
            if (node.getParentId() != null && node.getParentId().equals(node.getId())) {
                Node treeNode = new Node();
                treeNode.setId(node.getId().toString());
                treeNode.setText(node.getPermissionName());
                treeList.add(treeNode);
                build(node, treeNode);
            }
        }
        return treeList;
    }

    private void build(MPermission node, Node treeNode) {
        List<Node> children = getChildren(node);
        treeNode.setNodes(children);
    }

    private List<Node> getChildren(MPermission node) {
        List<Node> children = new ArrayList<Node>();
        Long id = node.getId();
        for (MPermission child : nodes) {
            if (id.equals(child.getParentId()) && !id.equals(child.getId())) {
                Node treeNode = new Node();
                treeNode.setId(node.getId().toString());
                treeNode.setText(child.getPermissionName());
                treeNode.setNodes(getChildren(child));
                children.add(treeNode);
            }
        }
        return children;
    }
}
