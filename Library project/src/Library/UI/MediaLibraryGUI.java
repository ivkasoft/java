package Library.UI;

import javax.swing.*;
import java.awt.*;

import Library.model.*;
import Library.factory.*;
import Library.adapter.*;
import Library.iterator.*;
import Library.library.*;
import java.util.List;

public class MediaLibraryGUI extends JFrame{
    private MediaLibrary library = new MediaLibrary();
    private RatingAdapter ratingAdapter = new RatingAdapter();

    private JTextField titleField = new JTextField(15);
    private JTextField genreField = new JTextField(10);
    private JTextField extraField = new JTextField(10);
    private JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Book", "Movie", "Music"});
    private JTextField ratingField = new JTextField(5);
    private JTextField filterField = new JTextField(10);
    private JTextArea outputArea = new JTextArea(15, 40);

    public MediaLibraryGUI(){
        super("🎞 Media Library Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeCombo);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Genre:"));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Author/Director/Artist:"));
        inputPanel.add(extraField);

        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton filterButton = new JButton("Filter");
        JButton showAllButton = new JButton("Show All");
        JButton rateButton = new JButton("Rate");

        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(new JLabel("Rate (0–10):"));
        inputPanel.add(ratingField);
        inputPanel.add(rateButton);
        inputPanel.add(new JLabel("Filter by genre:"));
        inputPanel.add(filterField);
        inputPanel.add(filterButton);
        inputPanel.add(showAllButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        addButton.addActionListener(e -> addMedia());
        removeButton.addActionListener(e -> removeMedia());
        filterButton.addActionListener(e -> filterByGenre());
        rateButton.addActionListener(e -> rateMedia());
        showAllButton.addActionListener(e -> displayAll());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMedia(){
        try {
            String type = (String) typeCombo.getSelectedItem();
            Media media = MediaFactory.createMedia(type, titleField.getText(), genreField.getText(), extraField.getText());
            library.addMedia(media);
            outputArea.append("✅ Added: " + media.getInfo() + "\n");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding media: " + ex.getMessage());
        }
    }

    private void removeMedia(){
        library.removeMedia(titleField.getText());
        outputArea.append("🗑 Removed: " + titleField.getText() + "\n");
    }

    private void rateMedia(){
        String title = titleField.getText();
        try {
            int rating = Integer.parseInt(ratingField.getText());
            if (rating < 0 || rating > 10) {
                JOptionPane.showMessageDialog(this, "Please enter a number between 0 and 10.");
                return;
            }
            for (Media m : library.getAll()) {
                if (m.getTitle().equalsIgnoreCase(title)) {
                    ratingAdapter.setRating(m, rating);
                    outputArea.append("⭐ Rated '" + m.getTitle() + "' with " + m.getRating() + " /5 stars (input: " + rating + "/10)\n");
                    return;
                }
            }
            outputArea.append("⚠️ Media not found: " + title + "\n");
        }catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid rating! Enter a number 0–10.");
        }
    }

    private void filterByGenre() {
        String genre = filterField.getText();
        List<Media> filtered = library.filterByGenre(genre);
        outputArea.append("\n🎧 Media with genre '" + genre + "':\n");
        for (Media m : filtered) outputArea.append(m.getInfo() + "\n");
    }

    private void displayAll() {
        outputArea.append("\n📚 All Media in Library:\n");
        MediaIterator iterator = library.iterator();
        while (iterator.hasNext()) {
            outputArea.append(iterator.next().getInfo() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MediaLibraryGUI::new);
    }
}
