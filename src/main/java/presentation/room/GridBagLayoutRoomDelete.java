package presentation.room;

import business.entities.Room;
import presentation.users.GridBagLayoutUsersDelete;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;
import static util.Utils.ALPHANUMERIC_REGEX;

public class GridBagLayoutRoomDelete extends JFrame {
    private JTextField fieldRoomId;

    private JButton saveButtonDeleteRoom;

    private JButton cancelButtonDeleteRoom;
    private RoomController roomController = new RoomController();

    public GridBagLayoutRoomDelete() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Room Id: "), 0, 0, GridBagConstraints.EAST);

        fieldRoomId = new JTextField(20);

        addItem(panel, fieldRoomId, 1, 0, GridBagConstraints.WEST);

        saveButtonDeleteRoom = new JButton("Delete room");
        saveButtonDeleteRoom.addActionListener(this::buttonDeleteRoom);
        cancelButtonDeleteRoom = new JButton("Cancel");
        cancelButtonDeleteRoom.addActionListener(e -> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonDeleteRoom);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonDeleteRoom);



        addItem(panel, buttonBox, 1, 2, GridBagConstraints.WEST);
        setLocationRelativeTo(null);


        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Delete room");
        this.setVisible(true);
    }

    private void buttonDeleteRoom(ActionEvent event) {

        boolean roomIdIsValid = Utils.containsOnlyNumbers(fieldRoomId.getText(), ALPHANUMERIC_REGEX);
        String invalidFields = checkInvalidFields(roomIdIsValid);
        if (fieldRoomId.getText().isEmpty()) {
            showMessageDialog(null, "Field must not be empty");
        } else if (!roomIdIsValid) {
            showMessageDialog(null, "Field is not valid:\n" + invalidFields);

        } else {
            String roomId = fieldRoomId.getText();
            roomController.handleClickButtonDeleteRoom(roomId);
            showMessageDialog(null, "Room deleted");
        }

    }

    private void addItem(JPanel panel, JComponent component, int x, int y, int align) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = align;
        panel.add(component, gbc);

    }
    private static String checkInvalidFields(boolean roomIdIsValid){
        String roomIdInvalid = "";
        if(!roomIdIsValid) {
            roomIdInvalid = "- room id is invalid\n";
        }
        String invalidFields = roomIdInvalid;
        return invalidFields;
    }

    public static void main(String[] args) {
        new GridBagLayoutRoomDelete();
    }
}
