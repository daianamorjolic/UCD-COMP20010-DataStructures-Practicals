package projectCode20280;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Concrete implementation of a binary tree using a node-based, linked structure.
 */

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> 
{
  protected static class Node<E> implements Position<E> 
  {
	  private E element;
	  private Node<E> parent;
	  private Node<E> leftChild;
	  private Node<E> rightChild;
	  
	  public Node(E e , Node<E> parent , Node<E> leftChild , Node<E> rightChild)
	  {
		  element = e;
		  parent = parent;
		  leftChild = leftChild;
		  rightChild = rightChild;
	  }
	  
	  public E getElement() { return element; }
	  
	  public void setElement(E e) { element = e; }
	  
	  public Node<E> getParent() { return parent; }
	  
	  public void setParent(Node<E> parent) { parent = parent; }
	  
	  public Node<E> getLeftChild() { return leftChild; }
	  
	  public void setLeftChild(Node<E> leftChild) { leftChild = leftChild; }
	  
	  public Node<E> getRightChild() { return rightChild; }
	  
	  public void setRightChild(Node<E> rightChild) { rightChild = rightChild; }
	  
	  public String toString()
	  {
		  StringBuilder sb = new StringBuilder().append(element);
		  
		  //sb.append();
		  return sb.toString();
	  }
  } 

  /** Factory function to create a new node storing element e. */
  protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) 
  {
    return new Node<E>(e, parent, left, right);
  }

  // LinkedBinaryTree instance variables
  /** The root of the binary tree */
  protected Node<E> root = null;     // root of the tree

  /** The number of nodes in the binary tree */
  private int size = 0;              // number of nodes in the tree

  // constructor
  /** Construts an empty binary tree. */
  public LinkedBinaryTree() { }      // constructs an empty binary tree

  // nonpublic utility
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this tree)
   * @return    the underlying Node instance for the position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  protected Node<E> validate(Position<E> p) throws IllegalArgumentException 
  {
    if (!(p instanceof Node))
      throw new IllegalArgumentException("Not valid position type");
    
    Node<E> node = (Node<E>) p;       // safe cast
    
    if (node.getParent() == node)     // our convention for defunct node
      throw new IllegalArgumentException("p is no longer in the tree");
    
    return node;
  }

  // accessor methods (not already implemented in AbstractBinaryTree)
  /**
   * Returns the number of nodes in the tree.
   * @return number of nodes in the tree
   */
  @Override
  public int size() { return size; }

  /**
   * Returns the root Position of the tree (or null if tree is empty).
   * @return root Position of the tree (or null if tree is empty)
   */
  @Override
  public Position<E> root() { return root; }

  /**
   * Returns the Position of p's parent (or null if p is root).
   *
   * @param p    A valid Position within the tree
   * @return Position of p's parent (or null if p is root)
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  @Override
  public Position<E> parent(Position<E> p) throws IllegalArgumentException 
  {
	  return ((Node<E>)p).parent;
  }

  /**
   * Returns the Position of p's left child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the left child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> left(Position<E> p) throws IllegalArgumentException 
  {
	  return ((Node<E>)p).leftChild;
  }

  /**
   * Returns the Position of p's right child (or null if no child exists).
   *
   * @param p A valid Position within the tree
   * @return the Position of the right child (or null if no child exists)
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   */
  @Override
  public Position<E> right(Position<E> p) throws IllegalArgumentException 
  {
	  return ((Node<E>)p).rightChild;
  }

  // update methods supported by this class
  /**
   * Places element e at the root of an empty tree and returns its new Position.
   *
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalStateException if the tree is not empty
   */
  public Position<E> addRoot(E e) throws IllegalStateException 
  {
	  if(root != null) { throw new IllegalStateException("Tree is not empty ."); }
	  
	  root = createNode(e, null, null, null);
	  size = 1;
	  
	  return root;
  }

  public void insert(E e)
  {
      //recursively add from root
      root = addRecursive(root, e);
      ++size;
  }
  
  //recursively add Nodes to binary tree in proper position
  private Node<E> addRecursive(Node<E> p, E e)
  {	  
	  return null;
  }

  
  /**
   * Creates a new left child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the left of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p already has a left child
   */
  public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException 
  {
	  Node<E> parentNode = (Node<E>) p;
	  
	  if(parentNode.getLeftChild() != null) { throw new IllegalArgumentException("Parent already has a left child ."); }
	  
	  Node<E> childNode = createNode(e, parentNode, null, null);
	  
	  parentNode.leftChild = childNode;
	  size++;
	  
	  return childNode;
  }

  /**
   * Creates a new right child of Position p storing element e and returns its Position.
   *
   * @param p   the Position to the right of which the new element is inserted
   * @param e   the new element
   * @return the Position of the new element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p already has a right child
   */
  public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException 
  {
	  Node<E> parentNode = (Node<E>) p;
	  
	  if(parentNode.getRightChild() != null) { throw new IllegalArgumentException("Parent already has a right child ."); }
	  
	  Node<E> childNode = createNode(e, parentNode, null, null);
	  
	  parentNode.rightChild = childNode;
	  size++;
	  
	  return childNode;
  }

  /**
   * Replaces the element at Position p with element e and returns the replaced element.
   *
   * @param p   the relevant Position
   * @param e   the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   */
  public E set(Position<E> p, E e) throws IllegalArgumentException 
  {
	  Node<E> node = validate(p);
	  E tempNode = node.getElement();
	  node.setElement(e);
	  
	  return tempNode;
  }

  /**
   * Attaches trees t1 and t2, respectively, as the left and right subtree of the
   * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
   *
   * @param p   a leaf of the tree
   * @param t1  an independent tree whose structure becomes the left child of p
   * @param t2  an independent tree whose structure becomes the right child of p
   * @throws IllegalArgumentException if p is not a valid Position for this tree
   * @throws IllegalArgumentException if p is not a leaf
   */
  public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) 
		  throws IllegalArgumentException 
  {
	  Node<E> node = validate(p);
	  
	  if(isInternal(p)) { throw new IllegalArgumentException(); }
	  
	  size += t1.size() + t2.size();
	  
	  if(!t1.isEmpty())
	  {
		  t1.root.setParent(node);
		  node.setLeftChild(t2.root);
		  t1.root = null;
		  t1.size = 0;
	  }
	  
	  if(!t2.isEmpty())
	  {
		  t2.root.setParent(node);
		  node.setLeftChild(t2.root);
		  t2.root = null;
		  t2.size = 0;
	  }
  }

  /**
   * Removes the node at Position p and replaces it with its child, if any.
   *
   * @param p   the relevant Position
   * @return element that was removed
   * @throws IllegalArgumentException if p is not a valid Position for this tree.
   * @throws IllegalArgumentException if p has two children.
   */
  public E remove(Position<E> p) throws IllegalArgumentException 
  {
	  Node<E> node = validate(p);
	  
	  if(numChildren(p) == 2) { throw new IllegalArgumentException("Parent already has two children ."); }
	  
	  Node<E> childNode = (node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild());
	  
	  if(childNode != null) { childNode.setParent(node.getParent()); }
	  
	  if(node == root) { root = childNode; }
	  
	  else
	  {
		  Node<E> parentNode = node.getParent();
		  
		  if(node == parentNode.getLeftChild()) { parentNode.setLeftChild(childNode); }
		  
		  else { parentNode.setRightChild(childNode); }
	  }
	  
	  size--;
	  E tempNode = node.getElement();
	  node.setElement(null);
	  node.setLeftChild(null);
	  node.setRightChild(null);
	  node.setParent(node);
	  
	  return tempNode;
  }
  
  public String toString() 
  {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	  
	  for(Position<E> p : positions()) 
	  {
		  if(sb.length() != 1)
		  {
			  sb.append(", ");
		  }
		  
		  sb.append(p.getElement());
	  }
	  
	  sb.append("]");
	  
	  return sb.toString();
  }
  
  public void createLevelOrder(java.util.ArrayList<E> arr)
  {
	  root = createLevelOrderHelper(arr, root, 0);
  }
  
  private Node<E> createLevelOrderHelper(java.util.ArrayList<E> arr , Node<E> p , int i)
  {
	  if(i < arr.size())
	  {
		  Node<E> n = createNode(arr.get(i), p, null, null);
		  
		  n.leftChild = createLevelOrderHelper(arr, n.leftChild, 2 * i + 1);
		  n.rightChild = createLevelOrderHelper(arr, n.rightChild, 2 * i + 2);
		  ++size;
		  
		  return n;
	  }
	  
	  return p;
  }
  
  public void createLevelOrder(E[] arr)
  {
	  root = createLevelOrderHelper(arr, root, 0);
  }
  
  private Node<E> createLevelOrderHelper(E[] arr , Node<E> p , int i)
  {
	  if(i < arr.length)
	  {
		  Node<E> n = createNode(arr[i], p, null, null);
		  
		  n.leftChild = createLevelOrderHelper(arr, n.leftChild, 2 * i + 1);
		  n.rightChild = createLevelOrderHelper(arr, n.rightChild, 2 * i + 2);
		  ++size;
		  
		  return n;
	  }
	  
	  return p;
  }
  
  public static void main(String [] args) 
  {
	  LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
	  
//	  Position<Integer> root = bt.addRoot(12);
//	  Position<Integer> p1 = bt.addLeft(root, 25);
//	  Position<Integer> p2 = bt.addRight(root, 31);
//	  
//	  Position<Integer> p3 = bt.addLeft(p1, 58);
//	  bt.addRight(p1, 36);
//	  
//	  Position<Integer> p5 = bt.addLeft(p2, 42);
//	  bt.addRight(p2, 90);
//	  
//	  Position<Integer> p4 = bt.addLeft(p3, 62);
//	  bt.addRight(p3, 75);
	  
	  Integer [] arr = {12, 25, 31, 58, 36, 42, 90, 62, 75};
	  bt.createLevelOrder(arr);
	  
	  System.out.println("bt inorder : " + bt.size() + " " + bt.inorder());
	  System.out.println("bt preorder : " + bt.size() + " " + bt.preorder());
	  System.out.println("bt postorder : " + bt.size() + " " + bt.postorder());
	  
	  System.out.println("bt height : " + bt.height(bt.root()));
	  System.out.println("bt depth : " + bt.depth(bt.root()));
	  
	
	  
//	  System.out.println("bt depth 62 : " + bt.depth(p4));
//	  System.out.println("bt depth 42 : " + bt.depth(p5));
	  
	  
//	  for(int i : arr) 
//	  {
//		  bt.insert(i);
//	  }
	  
  }
} 

