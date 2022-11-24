package lvd.zk;

import java.util.List;

public class Node {

    private String nodeName;

    private List<Node> children;

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Node() {
    }

    public Node(String nodeName, List<Node> children) {
        this.nodeName = nodeName;
        this.children = children;
    }
}
