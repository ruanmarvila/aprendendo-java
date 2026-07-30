package poo.encapsulamento;

public class Main {
    public static void main(String[] args) {
    
    Produto notebook = new Produto("Notebook Intel", 3400, 10);
    notebook.setEstoque(8);
    System.out.println(notebook.getNome()); // Notebook Intel
    System.out.println(notebook.getEstoque()); // 8
 }
}
