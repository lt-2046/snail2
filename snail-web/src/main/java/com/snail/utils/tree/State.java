package com.snail.utils.tree;

/**
 * Created by liutao on 2019/1/8.
 */
public class State {
    Boolean checked,//指示一个节点是否处于checked状态，用一个checkbox图标表示。
            disabled,//指示一个节点是否处于disabled状态。
            expanded,//指示一个节点是否处于展开状态。
            selected;//指示一个节点是否可以被选择。

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}