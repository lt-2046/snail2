package com.snail.utils.tree.impl;


import com.snail.model.m.entity.MPermission;
import com.snail.utils.tree.Node;
import com.snail.utils.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树
 */
public class MenuTreeImpl implements Tree<String> {
    private StringBuilder menuHtml = new StringBuilder("");
    private List<MPermission> nodes;


    /**
     * 是否被选中
     *
     * @param nodes
     */
    public MenuTreeImpl(List<MPermission> nodes) {
        this.nodes = nodes;
    }

    public String buildTree() {
        List<Node> treeList = new ArrayList<Node>();
        for (MPermission node : nodes) {
            Long id = node.getId();
            if (node.getParentId() != null && node.getParentId().equals(node.getId())) {
                Node treeNode = new Node();
                treeNode.setId(node.getId().toString());
                treeNode.setText(node.getPermissionName());
                treeNode.setUrl(node.getUrl());
                treeList.add(treeNode);
                build(node, treeNode);
            }
        }

        return tree2String(treeList);
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
                treeNode.setParentId(child.getParentId().toString());
                treeNode.setUrl(child.getUrl());
                treeNode.setNodes(getChildren(child));
                children.add(treeNode);
            }
        }
        return children;
    }

    private String tree2String(List<Node> treeList) {

        if (!treeList.isEmpty()) {

            for (Node node : treeList) {
                menuHtml.append("<li><a class='ajax-link' href='"+node.getUrl()+"'><i class='glyphicon glyphicon-home'></i><span>"+node.getText()+"</span></a></li>\n");
                List<Node> children = node.getNodes();
                menuHtml.append(tree2Stringbuild(node));
            }
        } else {
            return null;
        }

        return menuHtml.toString();
    }

    private String tree2Stringbuild(Node node){
        StringBuilder html = new StringBuilder();
        List<Node> children = node.getNodes();
        if (children!=null && !children.isEmpty()) {
            for (Node child : children) {
                html.append("<li class='accordion'>\n");
                html.append("<a href='"+child.getUrl()+"'><i class='glyphicon glyphicon-user'></i><span>"+child.getText()+"</span></a>\n");
                html.append("<ul class='nav nav-pills nav-stacked'>\n");
                if (isChild(child)) {
                    html.append(tree2Stringbuild(child))  ;
                }else{
                    html.append(tree2StringGetChildren(child.getNodes()));
                }

                html.append("</ul>\n");
                html.append("</li>\n");
            }
        }
        return html.toString();
    }

    /**
     * 判断当前菜单节点是否包含下两层菜单
     * @param child 包含返回true,不包含返回false
     * @return
     */
    private boolean isChild(Node child){
        boolean bool = false;
        if (child.getNodes()!=null && !child.getNodes().isEmpty()) {
            List<Node> temp = child.getNodes();
            for(Node nodeTemp : temp){
                if (nodeTemp.getNodes()!=null && !nodeTemp.getNodes().isEmpty()) {
                    bool = true;
                }
            }

        }
        return bool;
    }

    private String tree2StringGetChildren(List<Node> children) {
        StringBuilder html = new StringBuilder();
        for (Node child : children) {
            html.append("<li><a class='ajax-link' href='"+child.getUrl()+"'><i class='glyphicon glyphicon-user'></i><span>"+child.getText()+"</span></a></li>\n");
        }
        return html.toString();
    }

}
