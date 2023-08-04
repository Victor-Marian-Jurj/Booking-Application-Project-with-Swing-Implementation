package util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationComboBox extends JFrame {

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getSelectedSubItem() {
        return selectedSubItem;
    }

    public void setSelectedSubItem(String selectedSubItem) {
        this.selectedSubItem = selectedSubItem;
    }

    public String getSelectedHotelId() {
        return selectedHotelId;
    }

    public void setSelectedHotelId(String selectedHotelId) {
        this.selectedHotelId = selectedHotelId;
    }

    public String getSelectedRoomId() {
        return selectedRoomId;
    }

    public void setSelectedRoomId(String selectedRoomId) {
        this.selectedRoomId = selectedRoomId;
    }

    private String selectedCategory;
    private String selectedItem;
    private String selectedSubItem;
    private String selectedHotelId;
    private String selectedRoomId;

    private JComboBox<String> categoryComboBox;
    private JComboBox<String> itemComboBox;
    private JComboBox<String> subItemComboBox;
    private List<String> categories;
    private List<String> items;
    private List<String> subItems;
    private JPanel cb1Panel = new JPanel();
    private JPanel cb2Panel = new JPanel();
    private JPanel cb3Panel = new JPanel();
    private Connection connection;

    public JPanel getCb1Panel() {
        return cb1Panel;
    }
    public JPanel getCb2Panel() {
        return cb2Panel;
    }
    public JPanel getCb3Panel() {
        return cb3Panel;
    }



    public ReservationComboBox() {
        categories = new ArrayList<>();
        items = new ArrayList<>();
        subItems = new ArrayList<>();

        try {
            connection = DBUtil.getConnection();
            loadCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        createUI();
    }

    private void createUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout(2));

        categoryComboBox = new JComboBox<>();
        categoryComboBox.setPreferredSize(new Dimension(250, 25));

        itemComboBox = new JComboBox<>();
        itemComboBox.setPreferredSize(new Dimension(250, 25));

        subItemComboBox = new JComboBox<>();
        subItemComboBox.setPreferredSize(new Dimension(250, 25));

        // Populate category combo box
        for (String category : categories) {
            categoryComboBox.addItem(category);
        }


        categoryComboBox.setSelectedIndex(-1);

        // Add action listener to category combo box
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedCategory(categoryComboBox.getSelectedItem().toString());
                    loadItems(selectedCategory);
                    itemComboBox.showPopup();
                    System.out.println(getSelectedCategory());
                } catch (Exception ex) {}
            }
        });

        itemComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedItem(itemComboBox.getSelectedItem().toString());

                    String query = "SELECT * FROM hotel JOIN room ON hotel.hotel_id = room.hotel_id WHERE room.is_available = 'true' AND location = ? AND name = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, getSelectedCategory());
                        stmt.setString(2, getSelectedItem());
                        ResultSet resultSet = stmt.executeQuery();
                        while (resultSet.next()) {
                            setSelectedHotelId(resultSet.getString("hotel_id"));
                        }
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }


                    loadSubItems(categoryComboBox.getSelectedItem().toString(), itemComboBox.getSelectedItem().toString());
                    subItemComboBox.showPopup();
                    System.out.println(getSelectedHotelId());
                    System.out.println(getSelectedItem());
                } catch (Exception ex) {}
            }
        });

        subItemComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setSelectedSubItem(subItemComboBox.getSelectedItem().toString());

                    String query = "SELECT * FROM hotel JOIN room ON hotel.hotel_id = room.hotel_id WHERE room.is_available = 'true' AND location = ? AND name = ? AND room_type = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, getSelectedCategory());
                        stmt.setString(2, getSelectedItem());
                        stmt.setString(3, getSelectedSubItem());

                        ResultSet resultSet = stmt.executeQuery();
                        while (resultSet.next()) {
                            setSelectedRoomId(resultSet.getString("room_id"));
                        }
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }

                    System.out.println(getSelectedRoomId());
                    System.out.println(getSelectedSubItem());
                } catch (Exception ex) {

                }
            }
        });

        cb1Panel.add(categoryComboBox);
        cb2Panel.add(itemComboBox);
        cb3Panel.add(subItemComboBox);
    }

    private void loadCategories() {
        String query = "SELECT DISTINCT location FROM hotel JOIN room ON hotel.hotel_id = room.hotel_id WHERE room.is_available = 'true'";
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                categories.add(resultSet.getString("location"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadItems(String category) {
        items.clear();
        String query = "SELECT DISTINCT name FROM hotel JOIN room ON hotel.hotel_id = room.hotel_id WHERE room.is_available = 'true' AND location = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, category);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                items.add(resultSet.getString("name"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        itemComboBox.removeAllItems();
        for (String item : items) {
            itemComboBox.addItem(item);
        }

        itemComboBox.setSelectedIndex(-1);
    }

    private void loadSubItems(String location, String name) {
        subItems.clear();
        String query = "SELECT DISTINCT room_type FROM hotel JOIN room ON hotel.hotel_id = room.hotel_id WHERE room.is_available = 'true' AND location = ? AND name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, location);
            stmt.setString(2, name);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                subItems.add(resultSet.getString("room_type"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        subItemComboBox.removeAllItems();
        for (String subItem : subItems) {
            subItemComboBox.addItem(subItem);
        }

        subItemComboBox.setSelectedIndex(-1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReservationComboBox();
            }
        });
    }
}
