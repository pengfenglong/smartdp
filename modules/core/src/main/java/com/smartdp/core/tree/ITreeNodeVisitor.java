package com.smartdp.core.tree;

/**
 * 在使用ITreeBuilder构造节点前进行访问.可以通过ITreeNodeVisitor
 * 设置ITreeNode的属性，过滤节点.当访问一个节点返回false时，
 * 该节点和他所有儿子节点不会传递个ITreeBuilder
 * 节点访问.
 * @author pengfenglong
 * 
 */
public interface ITreeNodeVisitor {
  public boolean visit(ITreeNode pNode);
}
