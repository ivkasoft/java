import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Editor {
    private StringBuilder buffer;

    public Editor() {
        this.buffer = new StringBuilder();
    }

    public void read(String filePath) {
        try {
            String content = Files.readString(Paths.get(filePath));
            buffer.setLength(0); // Clear the buffer
            buffer.append(content);
        } catch (IOException e) {
            buffer.setLength(0); // Initialize an empty buffer if file doesn't exist or can't be read
        }
    }

    public void insert(int offset, String str) {
        if (offset < 0 || offset > buffer.length()) {
            throw new IndexOutOfBoundsException("Invalid offset");
        }
        buffer.insert(offset, str);
    }

    public void delete(int start, int end) {
        if (start < 0 || end > buffer.length() || start > end) {
            throw new IndexOutOfBoundsException("Invalid range");
        }
        buffer.delete(start, end);
    }

    public void write(String filePath) {
        try {
            Files.writeString(Paths.get(filePath), buffer.toString());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public String getBufferContent() {
        return buffer.toString();
    }

    public static void main(String[] args) {
        Editor editor = new Editor();

        // Пример за тестване
        editor.read("example.txt");
        System.out.println("Initial Content: " + editor.getBufferContent());

        editor.insert(5, "new text ");
        System.out.println("After Insert: " + editor.getBufferContent());

        editor.delete(5, 14);
        System.out.println("After Delete: " + editor.getBufferContent());

        editor.write("output.txt");
    }
}
