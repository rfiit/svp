package arseny.bochkarev.syntaxtree;

import arseny.bochkarev.simpletree.Tree;
import arseny.bochkarev.stacks.IStack;
import arseny.bochkarev.stacks.Stack2;

import java.util.ArrayList;

public class SyntaxTree {

    private enum ops {plus, minus, mult, divis};

    Tree<Exp> tree;

    private ArrayList<Exp> poland;

    public boolean parseExpression(String exp) {
        poland = new ArrayList<Exp>();
        Parser parser = new Parser(exp);

        IStack<Exp> stack = new Stack2<Exp>();

        Exp ne = parser.nextToken();

        while (ne != null) {
            if (ne.op == null) {
                poland.add(ne);
                ne = parser.nextToken();
                continue;
            }
            while (!stack.empty() && stack.show().pr >= ne.pr)
                poland.add(stack.peek());
            stack.push(ne);
            ne = parser.nextToken();
        }
        while (!stack.empty())
            poland.add(stack.peek());
        return true;
    }

    public String getPoland() {
        String result = "";
        String ns;
        for(int i = 0; i < poland.size(); i++) {
            ns = "";
            if (poland.get(i).op == ops.divis) ns = "/";
            if (poland.get(i).op == ops.minus) ns = "-";
            if (poland.get(i).op == ops.plus) ns = "+";
            if (poland.get(i).op == ops.mult) ns = "*";
            if (poland.get(i).op == null) ns = "" + poland.get(i).val;
            result += ns;
        }
        return result;
    }

    public Tree createTree() {
        if(poland == null || poland.size() == 0) return null;
        tree = new Tree<Exp>();
        tree.setRoot(poland.get(poland.size() - 1));
        ArrayList<Exp> poland2 = new ArrayList<Exp>(poland);
        recursiveCreateTree(tree.getConductor());
        poland = poland2;
        return tree;
    }

    private void recursiveCreateTree(Tree.Conductor tc) {
        if(((Exp)tc.get()).op == null) return;
        tc.addRight(poland.remove(poland.size()-1));
        Tree.Conductor ntc = tc.clone();
        ntc.goRight();
        recursiveCreateTree(ntc);
        tc.addLeft(poland.remove(poland.size()-1));
        ntc = tc.clone();
        ntc.goLeft();
        recursiveCreateTree(ntc);
    }

    private class Parser {
        private String po;
        int i = -1;

        public Exp nextToken() {
            i++;

            if(i >= po.length()) return null;

//            while (Character.isLetter(po.charAt(i))) {
//                i++;
//            }

            if(po.charAt(i) == '+') return new Exp(ops.plus);
            if(po.charAt(i) == '-') return new Exp(ops.minus);
            if(po.charAt(i) == '*') return new Exp(ops.mult);
            if(po.charAt(i) == '/') return new Exp(ops.divis);

            int res = 0;
//            System.out.println(po.charAt(i));
            while(i < po.length() && Character.isDigit(po.charAt(i))) {
                res = res * 10 + Integer.parseInt(String.valueOf(po.charAt(i)));
                i++;
            }

            i--;
            return new Exp(res);
        }

        public Parser(String s) {
            po = s;
        }
    }

    private static class Exp {
        final int val;
        final ops op;
        final int pr;
        public Exp(ops o) {
            this.val = 0;
            this.op = o;
            if (o == ops.minus || o == ops.plus) this.pr = 1; else this.pr = 2;
        }

        public Exp(int v) {
            this.val = v;
            this.op = null;
            this.pr = 0;
        }
    }
}
