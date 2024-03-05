import java.util.ArrayList;

public class BinarySearchTree<E extends KeyedElementInterface<K>,K extends Comparable<? super K>> implements BinarySearchTreeInterface<E,K> {

	private TreeNode<E,K> root;
	private ArrayList<K> array = new ArrayList<K>();
	public ArrayList<E> box = new ArrayList<E>();
	
	public BinarySearchTree() {
		 this.root = null;
	}
	
	public BinarySearchTree(TreeNode<E,K> root) {
		this.root = root;
	}
	
	@Override
	public TreeNode<E, K> getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	@Override
	public void setRoot(TreeNode<E, K> root) {
		// TODO Auto-generated method stub
		if(this.root.getRightChild() != null) {
			this.root.getRightChild().setParent(root);
		}
		if(this.root.getLeftChild() != null) {
			this.root.getLeftChild().setParent(root);
		}
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.root == null;
	}

	@Override
	public void makeEmpty() {
		// TODO Auto-generated method stub
		this.root = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BinarySearchTreeInterface<E, K> copy() {
		// TODO Auto-generated method stub
		BinarySearchTree<E,K> copyTree = new BinarySearchTree<E,K>();
		preOrder(root);
		
		E newNode;
		
		for(int i = 0; i < this.box.size(); ++i) {
			newNode = (E) this.box.get(i).copy();
			copyTree.insert(newNode);
		}
		this.box.removeAll(box);
		return copyTree;
	}
	
	public void preOrder(TreeNode<E,K> node) {
		if(node == null) {
			return;
		}
		this.box.add(node.getElement());
		preOrder(node.getLeftChild());
		preOrder(node.getRightChild());
	}

	@Override
	public E find(K key) {
		// TODO Auto-generated method stub
		TreeNode<E,K> currNode = this.root;
		while(currNode != null) {
			if(currNode.getElement().getKey().compareTo(key) < 0) {
				currNode = currNode.getRightChild();
			}
			else if(currNode.getElement().getKey().compareTo(key) > 0) {
				currNode = currNode.getLeftChild();
			}
			else {
				return currNode.getElement();
			}
		}
		return null;
	}

	@Override
	public void insert(E element) {
		// TODO Auto-generated method stub
		TreeNode<E,K> newNode = new TreeNode<E,K>(element);
		if(this.root == null) {
			this.root = newNode;
		}
		else {
			insertRecursive(this.root, newNode);
		}
		
	}
	
	private void insertRecursive(TreeNode<E,K> currNode, TreeNode<E,K> newNode) {
		K key1 = currNode.getElement().getKey();
		K key2 = newNode.getElement().getKey();
		
		if(key1.compareTo(key2) >= 0) {
			if(currNode.getLeftChild() == null) {
				newNode.setParent(currNode);
				currNode.setLeftChild(newNode);
			}
			else {
				insertRecursive(currNode.getLeftChild(), newNode);
			}
		}else {
			if(currNode.getRightChild() == null) {
				newNode.setParent(currNode);
				currNode.setRightChild(newNode);
			}
			else {
				insertRecursive(currNode.getRightChild(), newNode);
			}
		}
	}

	@Override
	public void delete(K key) throws TreeException {
		// TODO Auto-generated method stub
		if(key == null) {
			throw new TreeException("Null");
		}
		else {
			if(find(key) == null) {
				return;
			}
			TreeNode<E,K> delNode = findNode(key);
			if(delNode.getElement().getKey().compareTo(this.root.getElement().getKey()) == 0) {
				
				if(this.root.getRightChild() != null) {
					
					if(this.root.getLeftChild() != null) {
						
						this.root.getLeftChild().setParent(this.root.getRightChild());
					}
					this.root = this.root.getRightChild();
					this.root.setParent(null);
				}
				else if(this.root.getLeftChild() != null) {
					
					 this.root = this.root.getLeftChild();
					 this.root.setParent(null);
				}
				else {
					this.root = null;
				}
			}
			else {
				int childCheck = Children(delNode);
				if(childCheck == 2) {
					if(delNode.getElement().getKey().compareTo(delNode.getParent().getElement().getKey()) >= 0) {
						TreeNode<E,K> children = delNode.getRightChild();
						while(children.getLeftChild() != null) {
							children = children.getLeftChild();
						}
						children.getParent().setLeftChild(null);
						delNode.getParent().setRightChild(children);
						children.setParent(delNode.getParent());
						children.setRightChild(delNode.getRightChild());
						children.setLeftChild(delNode.getLeftChild());
						delNode = children;
					}
					else {
						TreeNode<E,K> children = delNode.getRightChild();
						while(children.getLeftChild() != null) {
							children = children.getLeftChild();
						}
						children.getParent().setLeftChild(null);
						delNode.getParent().setLeftChild(children);
						children.setParent(delNode.getParent());
						children.setRightChild(delNode.getRightChild());
						children.setLeftChild(delNode.getLeftChild());
						delNode = children;
					}
				}
				else if(childCheck == 1) {
					
					if(delNode.getElement().getKey().compareTo(delNode.getParent().getElement().getKey()) >= 0) {
						if(delNode.getRightChild() != null) {
							delNode.getParent().setRightChild(delNode.getRightChild());
							delNode.getRightChild().setParent(delNode.getParent());
							delNode.setParent(null);
							delNode.setRightChild(null);
						}
						else {
							delNode.getParent().setRightChild(delNode.getLeftChild());
							delNode.getLeftChild().setParent(delNode.getParent());
							delNode.setParent(null);
							delNode.setLeftChild(null);
						}
					}
					else {
						
						if(delNode.getRightChild() != null) {
							delNode.getParent().setRightChild(delNode.getRightChild());
							delNode.getRightChild().setParent(delNode.getParent());
							delNode.setParent(null);
							delNode.setRightChild(null);
						}
						else {
							delNode.getParent().setRightChild(delNode.getLeftChild());
							delNode.getLeftChild().setParent(delNode.getParent());
							delNode.setParent(null);
							delNode.setLeftChild(null);
						}
					}
				}
				else {
					if(delNode.getElement().getKey().compareTo(delNode.getParent().getElement().getKey()) >= 0) {
						delNode.getParent().setRightChild(null);
						delNode.setParent(null);
					}
					else {
						delNode.getParent().setLeftChild(null);
						delNode.setParent(null);
					}
				}
			}
		}
	}
	
	private int Children(TreeNode<E,K> node) {
		if(node.getLeftChild() != null && node.getRightChild() != null) {
			return 2;
		}
		else if(node.getRightChild() != null || node.getLeftChild() != null) {
			return 1;
		}
		return 0;
	}
	
	
	private TreeNode<E,K> findNode(K key){
		TreeNode<E,K> currNode = this.root;
		while(currNode.getElement().getKey().compareTo(key) != 0) {
			if(currNode.getElement().getKey().compareTo(key) < 0) {
				currNode = currNode.getRightChild();
			}
			else if(currNode.getElement().getKey().compareTo(key) > 0) {
				currNode = currNode.getLeftChild();
			}
		}
		return currNode;
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		if(this.root == null || this.root.getLeftChild() == null || this.root.getRightChild() == null) {
			return 0;
		}
		else {
			return BSTgetHeight(this.root);
		}
	}

	private int BSTgetHeight(TreeNode<E,K> node) {
		if(node == null) {
			return 0;
		}
		int leftHeight = BSTgetHeight(node.getLeftChild());
		int rightHeight = BSTgetHeight(node.getRightChild());
		
			
		return 1 + Math.max(leftHeight, rightHeight);
	}
	
	@Override
	public boolean isBalanced() {
		// TODO Auto-generated method stub
		return Print();
	}
	
	public void Traverse(TreeNode<E,K> node) {
		if(node == null) {
			return;
		}
		this.Traverse(node.getLeftChild());
		array.add(node.getElement().getKey());
		this.Traverse(node.getRightChild());
	}
	
	private boolean Print() {
		Traverse(this.root);
		for(int j = 0; j < this.array.size(); j++) {
			System.out.println(this.array.get(j));
		}
		for(int i = 0; i < this.array.size(); ++i) {
			if(Children(findNode(array.get(i))) == 1) {
				this.array.removeAll(array);
				return false;
			}
		}
		this.array.removeAll(array);
		return true;
	}
	public void printCall() {
		PrintPost(this.root);
	}

	private void PrintPost(TreeNode<E,K> node) {
		if(node == null) {
			return;
		}
		PrintPost(node.getLeftChild());
		PrintPost(node.getRightChild());
		System.out.println(node.getElement().getKey());
	}
	
	@Override
	public void balance() {
		// TODO Auto-generated method stub
		preOrder(this.root);
		makeEmpty();
		for(int i = 0; i < this.box.size(); ++i) {
			insert(this.box.get(i));
		}
		this.box.removeAll(box);
	}

}
