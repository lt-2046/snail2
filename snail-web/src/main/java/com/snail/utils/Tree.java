package com.snail.utils;


import com.snail.model.m.entity.MPermission;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private StringBuffer html = new StringBuffer();
    private List<MPermission> nodes;

    public Tree(List<MPermission> nodes) {
        this.nodes = nodes;
    }

    public List<TreeNode> buildTree() {
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        for (MPermission node : nodes) {
            Long id = node.getId();
            if (node.getParentId() != null && node.getParentId().equals(node.getId())) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(node.getId().toString());
                treeNode.setText(node.getPermissionName());
                treeList.add(treeNode);
                build(node, treeNode);
            }
        }

        return treeList;
    }

    private void build(MPermission node, TreeNode treeNode) {
        List<TreeNode> children = getChildren(node);
        treeNode.setNodes(children);
    }

    private List<TreeNode> getChildren(MPermission node) {
        List<TreeNode> children = new ArrayList<TreeNode>();
        Long id = node.getId();
        for (MPermission child : nodes) {
            if (id.equals(child.getParentId()) && !id.equals(child.getId())) {
                TreeNode treeNode = new TreeNode();
                treeNode.setId(node.getId().toString());
                treeNode.setText(child.getPermissionName());
                treeNode.setNodes(getChildren(child));
                children.add(treeNode);
            }
        }
        return children;
    }
}
