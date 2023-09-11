/**
 * The BinarySearchTreeFunctions interface specifies all the functionality expected of our BinarySearchTree class.
 */ 
interface BinarySearchTreeFunctions
{
    /**
     * Getter for the root of the entire BST.
     * 
     * @return the root Node
     */ 
    public Node getRoot();

    /**
     * Setter for the root of the entire BST.
     * 
     * @param Node root - specifies the new root Node of the BST.
     */ 
    public void setRoot(Node root);

    /**
     * Inserts a Node into the BST.
     * If a Node exists in the BST with the same key as Node z, then we should not add z.
     * 
     * @param Node z - specifies the new Node to be added to the BST.
     */ 
    public void insertNode(Node z);

    /**
     * Updates an existing Node in the BST.
     * The specified Node should replace the node in the BST which has the same key as Node z.
     * If there is no such node in the tree, then this update should be ignored.
     * 
     * Note: This is used primarly to update the held data within the node being updated. The key value remains the same.
     * 
     * @param Node z - specifies the updated state of some Node that has already been inserted to the BST previously.
     */ 
    public void updateNode(Node z);

    /**
     * Traverses and prints the BST, performing a pre-order walk.
     * 
     * Recall a pre-order walk involves visiting:
     *      1. Ourselves (x)
     *      2. Our left subtree 
     *      3. Our right subtree
     * 
     * @param Node x - specifies which Node we are currently viewing as the relative root as we perform the pre-order traversal.
     */ 
    public void preOrderWalk(Node x);

    /**
     * Traverses the BST, performing a pre-order walk.
     * When the pre-order traversal is complete, the list passed in will be fully populated with information pertaining 
     * to the order the in which the Nodes were visited.
     * 
     * @param Node                        x     - specifies which Node we are currently visiting 
     *                                            as the relative root as we perform the pre-order traversal.
     * @param java.util.ArrayList<String> list  - a list of Strings that will be populated based upon
     *                                            the order in which the Nodes are visited.
     */ 
    public void preOrderWalk(Node x, java.util.ArrayList<String> list);

    /**
     * Traverses and prints the BST, performing an in-order walk.
     * 
     * Recall an in-order walk involves visiting:
     *      1. Our left subtree
     *      2. Ourselves (x)
     *      3. Our right subtree
     * 
     * @param Node x - specifies which Node we are currently viewing as the relative root as we perform the in-order traversal.
     */ 
    public void inOrderWalk(Node x);

    /**
     * Traverses the BST, performing an in-order walk.
     * When the in-order traversal is complete, the list passed in will be fully populated with information pertaining 
     * to the order the in which the Nodes were visited.
     * 
     * @param Node                        x     - specifies which Node we are currently visiting 
     *                                            as the relative root as we perform the in-order traversal.
     * @param java.util.ArrayList<String> list  - a list of Strings that will be populated based upon
     *                                            the order in which the Nodes are visited.
     */ 
    public void inOrderWalk(Node x, java.util.ArrayList<String> list);

    /**
     * Traverses and prints the BST, performing a post-order walk.
     * 
     * Recall a post-order walk involves visiting:
     *      1. Our left subtree
     *      2. Our right subtree
     *      3. Ourselves (x)
     * 
     * @param Node x - specifies which Node we are currently viewing as the relative root as we perform the post-order traversal.
     */ 
    public void postOrderWalk(Node x);

    /**
     * Traverses the BST, performing a post-order walk.
     * When the post-order traversal is complete, the list passed in will be fully populated with information pertaining 
     * to the order the in which the Nodes were visited.
     * 
     * @param Node                        x     - specifies which Node we are currently visiting 
     *                                            as the relative root as we perform the post-order traversal.
     * @param java.util.ArrayList<String> list  - a list of Strings that will be populated based upon
     *                                            the order in which the Nodes are visited.
     */ 
    public void postOrderWalk(Node x, java.util.ArrayList<String> list);

    /**
     * Returns the Node which is the maximum descendant of the specified Node.
     * By maximum descendant, we mean some Node which:
     *      1. has the largest possible key value 
     *      2. is contained within a subtree (in particular the right subtree) of the specified Node
     * 
     * If the specified Node has no descendant with a larger key, return x itself.
     * 
     * @param Node x - specifies which Node we wish to start from while searching for his maximum descendant
     * 
     * @return the maximum descendant of the specified Node (which may be itself)
     */ 
    public Node getMax(Node x);

    /**
     * Returns the Node which is the minimum descendant of the specified Node.
     * By minimum descendant, we mean some Node which:
     *      1. has the smallest possible key value 
     *      2. is contained within a subtree (in particular the left subtree) of the specified Node
     * 
     * If the specified Node has no descendant with a smaller key, return x itself.
     * 
     * @param Node x - specifies which Node we wish to start from while searching for his minimum descendant
     * 
     * @return the minimum descendant of the specified Node (which may be itself)
     */ 
    public Node getMin(Node x);

    /**
     * Returns the Node which is the successor of the specified Node.
     * By successor, we mean some Node whose key value is the least upper bound of the specified Node's key.
     * 
     * If the specified Node has no successor, return null.
     * 
     * @param Node x - specifies which Node we wish to start from while searching for his successor.
     * 
     * @return the successor of the specified Node (which may be null)
     */ 
    public Node getSuccessor(Node x);

    /**
     * Returns the Node which is the predecessor of the specified Node.
     * By predecessor, we mean some Node whose key value is the greatest lower bound of the specified Node's key.
     * 
     * If the specified Node has no predecessor, return null.
     * 
     * @param Node x - specifies which Node we wish to start from while searching for his predecessor.
     * 
     * @return the predecessor of the specified Node (which may be null)
     */ 
    public Node getPredecessor(Node x);

    /**
     * Attempts to find and return the Node, starting from x, which holds the specified key.
     * 
     * If the specified key doesn't correspond to some Node that is a descendant of x, return null.
     * 
     * @param Node x   - specifies which Node we wish to start from while searching for the node
     * @param int  key - the key value indicating which Node we are attempting to find
     * 
     * @return the Node whose key value matches the key we specified (may be null if not found)
     */ 
    public Node getNode(Node x, int key);

    /**
     * Determines and returns the height of some subtree of the BST, starting from x as the relative root Node.
     * 
     * Note: To determine the height of the entire BST, we would pass the actual root of the entire BST to this method.
     * 
     * @param Node x - specifies which Node we wish to start from while determing the height.
     * 
     * @return the height of the subtree which starts from the relative root
     */ 
    public int getHeight(Node x);

    /**
     * Helper method which replaces one Node, namely u, with another Node, namely v.
     * This method is only used by the deleteNode function.
     * 
     * By "replace", we mean that Node v is treated as if it resided where u was all along. In other words:
     *      1. Parent: v's parent will be updated to point at what u's parent was
     *      2. Child:  u's parent will treat v as its child, pointing to v where it used to point to u. 
     */ 
    public void shiftNode(Node u, Node v);

    /**
     * Attempts to find and delete a Node from the BST.
     * If the specified Node cannot be found in the BST, then this function should not alter the BST.
     * Upon deleting the Node, is it required that the BST maintains the search property and is connected after the deletion.
     * 
     * By search property, we mean that for any given Node in the BST:
     *      1. all his left descendants (those in his left subtree) have strictly smaller key values
     *      2. all his right descendants (those in his right subtree) have strictly greater key values
     * 
     * @param Node z - the node to be found and then removed from the BST.
     */ 
    public void deleteNode(Node z);
}