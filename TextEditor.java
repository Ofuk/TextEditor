package CODING;
import java.util.Scanner;

class CustomStack {
    private String[] stackArray;
    private int top;

    public CustomStack(int size) {
        stackArray = new String[size];
        top = -1;
    }

    public void push(String value) {
        if (top < stackArray.length - 1) {
            stackArray[++top] = value;
        } else {
            System.out.println("Stack penuh, cant push.");
        }
    }

    public String pop() {
        if (top >= 0) {
            return stackArray[top--];
        } else {
            return null;
        }
    }

    public String peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void clear() {
        top = -1;
    }
}

public class TextEditor {
    private StringBuilder text;
    private CustomStack undoStack;
    private CustomStack redoStack;

    public TextEditor(int stackSize) {
        text = new StringBuilder();
        undoStack = new CustomStack(stackSize);
        redoStack = new CustomStack(stackSize);
    }

    public void write(String newText) {
        undoStack.push(text.toString());
        text.append(newText);
        redoStack.clear();
    }

    public void show() {
        System.out.println("Current text : " + text.toString());
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(text.toString());
            text = new StringBuilder(undoStack.pop());
        } else {
            System.out.println("Tidak ada lagi aksi undo.");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(text.toString());
            text = new StringBuilder(redoStack.pop());
            System.out.println("Tidak ada lagi aksi redo.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor(100);

        while (true) {
            System.out.println("\nPilih perintah: 1) write  2) undo  3) redo  4) show  5) exit");
            System.out.print("Masukkan pilihan: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan teks yang ingin ditambahkan: ");
                    String newText = scanner.nextLine();
                    editor.write(newText);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.show();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
