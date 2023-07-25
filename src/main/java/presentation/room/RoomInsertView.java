package presentation.room;

import business.entities.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.UUID;

import static javax.swing.JOptionPane.showMessageDialog;

public class RoomInsertView extends JPanel {
    private RoomController roomController = new RoomController();

    private JTextField fieldRoomNumber;
    private JTextField fieldRoomType;
    private JTextField fieldRoomFloor;
    private JTextField fieldRoomPrice;
    private JTextField fieldRoomHotelId;

    public RoomInsertView() {
//        super("Insert Room Panel");
//

//        JFrame frame = new JFrame("Insert room and hotel panel");
//        JPanel parentPanel = new JPanel(new GridBagLayout());
//
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.fill = GridBagConstraints.BOTH;
//        constraints.weightx = 1.0;
//
//        JPanel content = new JPanel();
//        content.add(createNewRoom());
//        content.setBackground(Color.LIGHT_GRAY);
//        constraints.weighty = 1.0;
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        parentPanel.add(content, constraints);
//
//        JPanel panel2 = new JPanel();
//        panel2.setBackground(Color.DARK_GRAY);
//        constraints.weighty = 1.0;
//        constraints.gridx = 1;
//        constraints.gridy = 0;
//        parentPanel.add(panel2, constraints);
//
//        frame.getContentPane().add(parentPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1280, 720);
//        frame.setVisible(true);


//        JPanel content = new JPanel();
//        content.add(createNewRoom());
//        this.setVisible(true);
//        setContentPane(content);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        this.pack();


//        this.setContentPane(content);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        this.pack();

//        setTitle("whatever");
//        setContentPane(createNewRoom());
//        setSize(500, 450);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
    }

    public void setRoomController(RoomController roomController) {
        this.roomController = roomController;
    }

    public void display() {
        setVisible(true);
    }

    public JPanel createNewRoom() {
        JPanel newRoom = new JPanel();

        JLabel roomTitle = new JLabel("Insert Room");
        roomTitle.setHorizontalAlignment(SwingConstants.CENTER);
        roomTitle.setForeground(Color.BLACK);
        roomTitle.setFont(new Font("Arial", Font.BOLD, 15));
        roomTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

        newRoom.add(roomTitle);
        newRoom.setBorder(new EmptyBorder(10, 10, 10, 10));
        newRoom.setLayout(new GridLayout(8, 1, 10, 10));
        newRoom.add(createRoomNumber());
        newRoom.add(createRoomType());
        newRoom.add(createRoomPrice());
        newRoom.add(createRoomFloor());
        newRoom.add(createRoomHotelId());
        newRoom.add(insertRoomButtonPanel());

        return newRoom;
    }

    private JPanel createRoomNumber() {
        JPanel roomNumber = new JPanel();
        roomNumber.add(new JLabel("Room Number"));
        fieldRoomNumber = new JTextField(50);
        roomNumber.add(fieldRoomNumber);
        return roomNumber;
    }

    private JPanel createRoomType() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Room Type"));
        fieldRoomType = new JTextField(50);
        usernamePanel.add(fieldRoomType);
        return usernamePanel;
    }

    private JPanel createRoomFloor() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Room Floor"));
        fieldRoomFloor = new JTextField(50);
        panel.add(fieldRoomFloor);
        return panel;
    }

    private JPanel createRoomPrice() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Room Price"));
        fieldRoomPrice = new JTextField(50);
        usernamePanel.add(fieldRoomPrice);
        return usernamePanel;
    }

    private JPanel createRoomHotelId() {
        JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Room Hotel Id"));
        fieldRoomHotelId = new JTextField(50);
        usernamePanel.add(fieldRoomHotelId);
        return usernamePanel;
    }

    private JPanel insertRoomButtonPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton buttonOk = new JButton("OK");
        buttonOk.addActionListener(this::buttonInsert);
        buttonsPanel.add(buttonOk);
        return buttonsPanel;
    }

    private void buttonInsert(ActionEvent event) {
        String roomId = UUID.randomUUID().toString().substring(0, 5);
        int roomNumber = Integer.parseInt(fieldRoomNumber.getText());
        String roomType = fieldRoomType.getText();
        int roomFloor = Integer.parseInt(fieldRoomFloor.getText());
        int roomPrice = Integer.parseInt(fieldRoomPrice.getText());
        String roomHotelId = fieldRoomHotelId.getText();
        boolean isAvailable = true;


        roomController.handleClickButtonInsert(new Room(roomId, roomNumber, roomType, roomFloor, roomPrice, roomHotelId, isAvailable));
        showMessageDialog(null, "Room added");

    }

    public static void main(String[] args) {
        RoomInsertView roomInsertView = new RoomInsertView();

        RoomController roomCon = new RoomController();

        roomInsertView.setRoomController(roomCon);

        roomCon.setRoomInsertView(roomInsertView);

        roomCon.start();

    }
}

