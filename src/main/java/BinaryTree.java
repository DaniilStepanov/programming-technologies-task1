import java.util.Objects;

/**Вариант 12 -- бинарное дерево поиска [Java]
 Хранит целые числа в виде бинарного дерева поиска. Дерево не может содержать
 одно и то же число более одного раза.
 Методы: добавление числа, удаление числа, поиск числа в дереве, определение
 соседей числа в дереве (предок, левый потомок, правый потомок).*/

// BinaryTree.add(5) - добавить в дерево узел со значением 5
// BinaryTree.remove(5) - удалить из дерева узел со значением 5
// BinaryTree.search(5) - узнать, есть ли в дереве узел со значением 5 (boolean)
// BinaryTree.parent(5) - определить предка узла со значением 5
// BinaryTree.leftChild(5) - определить левого потомка узла со значением 5
// BinaryTree.rightChild(5) - определить правого потомка узла со значением 5

public class BinaryTree {


    Node root;

    private Node addNode(Node current, int value) {
        if (current == null || current.value == -1) return new Node(value);
        if (value < current.value) current.left = addNode(current.left, value);
        else if (value > current.value) current.right = addNode(current.right, value);
        else return current;
        return current;
    }

    public void add(int value) {
        root = addNode(root, value);
    }

    private boolean searchNode(Node current, int value) {
        if (current == null) return false;
        if (value == current.value) return true;
        if (value < current.value) return searchNode(current.left, value);
        else return searchNode(current.right, value);
    }

    public boolean search(int value) {
        return searchNode(root, value);
    }

    private int findMaxOfLeft(Node start) {
        if (start.right == null) return start.value;
        else return findMaxOfLeft(start.right);
    }

    Node potentialParent = null;
    int leftOrRight = -1;    //0 - пошли к левому ребенку, 1 - пошли к правому

    private Node removeNode(Node current, int value) {
        if (current == null) return null;
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                if (potentialParent != null) {
                    if (leftOrRight == 1) potentialParent.right = null;
                    else potentialParent.left = null;
                }
                return null;
            }
            if (current.left == null) {
                if (potentialParent != null) {
                    if (leftOrRight == 1) potentialParent.right = current;
                    else potentialParent.left = current;
                }
                return current.right;
            }
            if (current.right == null) {
                if (potentialParent != null) {
                    if (leftOrRight == 1) potentialParent.right = current;
                    else potentialParent.left = current;
                }
                return current.left;
            }
            else {
                if (potentialParent != null) {
                    if (leftOrRight == 1) potentialParent.right = current;
                    else potentialParent.left = current;
                }
                current.value = findMaxOfLeft(current.left);
                current.left = removeNode(current.left, findMaxOfLeft(current.left));
                return current;
            }
        }
        if (value < current.value) {
            potentialParent = current;
            leftOrRight = 0;
            current.left = removeNode(current.left, value);
        }
        else {
            potentialParent = current;
            leftOrRight = 1;
            current.right = removeNode(current.right, value);
        }
        return current;
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    private int parentNode(Node current, int value) {
        if (!search(value) || current.value == value) return -1;
        if ((current.left != null && current.left.value == value)
                || (current.right != null && current.right.value == value)) return current.value;
        if (value < current.value) return parentNode(current.left, value);
        else return parentNode(current.right, value);
    }

    public int parent(int value) {
        return parentNode(root, value);
    }

    private int leftChildNode(Node current, int value) {
        if (!search(value)) return -1;
        if (current.value == value && current.left != null) return current.left.value;
        if (current.value > value && current.left != null) return leftChildNode(current.left, value);
        if (current.value < value && current.right != null) return leftChildNode(current.right, value);
        else return -1;
    }

    public int leftChild(int value) {
        return leftChildNode(root, value);
    }

    private int rightChildNode(Node current, int value) {
        if (!search(value)) return -1;
        if (current.value == value && current.right != null) return current.right.value;
        if (current.value > value && current.left != null) return rightChildNode(current.left, value);
        if (current.value < value && current.right != null) return rightChildNode(current.right, value);
        else return -1;
    }

    public int rightChild(int value) {
        return rightChildNode(root, value);
    }

    public static void main(String[] args) {
        BinaryTree a = new BinaryTree();
        a.add(10);
        a.add(300);
        a.add(150);
        a.add(75);
        a.add(225);
        a.add(50);
        a.add(100);
        a.add(200);
        a.add(250);
        System.out.println(a.leftChild(300));
        System.out.println(a.leftChild(150));
        System.out.println(a.rightChild(75));
        a.remove(150);
        System.out.println(a.leftChild(300));
        System.out.println(a.rightChild(75));
    }

    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value && Objects.equals(left, node.left) && Objects.equals(right, node.right);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, left, right);
        }
    }
}


