package day.five;

import java.util.ArrayList;
import java.util.List;

public class Node<Data, Label> {
    private Data data = null;
    private Label label = null;
    private List<Node<Data, Label>> children = new ArrayList<>();
    private Node<Data, Label> parent = null;

    public Node(Data data, Label label) {
        this.data = data;
        this.label = label;
    }

    public Node<Data, Label> addChild(Node<Data, Label> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<Data, Label>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<Data, Label>> getChildren() {
        return children;
    }

    public Data getData() {
        return data;
    }

    public Label getLabel() {
        return label;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private void setParent(Node<Data, Label> parent) {
        this.parent = parent;
    }

    public Node<Data, Label> getParent() {
        return parent;
    }

}
