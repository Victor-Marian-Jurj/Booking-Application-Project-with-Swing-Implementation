package presentation.room;

import business.entities.Room;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.JOptionPane.showMessageDialog;

public class GridBagLayoutRoomUpdate extends JFrame {
    private JTextField fieldRoomId;
    private JTextField fieldRoomPrice;

    private JButton saveButtonRoomUpdate;

    private JButton cancelButtonRoomUpdate;
    private RoomController roomController = new RoomController();

    public GridBagLayoutRoomUpdate() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addItem(panel, new JLabel("Select room Id:"), 0, 0, GridBagConstraints.EAST);
        addItem(panel, new JLabel("New room price: "), 0, 1, GridBagConstraints.EAST);

        fieldRoomId = new JTextField(20);
        fieldRoomPrice = new JTextField(20);

        addItem(panel, fieldRoomId, 1, 0, GridBagConstraints.WEST);
        addItem(panel, fieldRoomPrice, 1, 1, GridBagConstraints.WEST);

        saveButtonRoomUpdate = new JButton("Save updates");
        saveButtonRoomUpdate.addActionListener(this::buttonUpdateRoom);
        cancelButtonRoomUpdate = new JButton("Cancel");
        cancelButtonRoomUpdate.addActionListener(e-> this.dispose());


        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(saveButtonRoomUpdate);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButtonRoomUpdate);



        addItem(panel, buttonBox, 1, 3, GridBagConstraints.WEST);
        setLocationRelativeTo(null);

        this.add(panel);
        this.pack();
        this.setSize(600, 300);
        this.setTitle("Update room data");
        this.setVisible(true);

    }

    private void buttonUpdateRoom(ActionEvent event) {

        boolean roomIdIsValid = Utils.containsOnlyNumbers(fieldRoomId.getText(), Utils.ALPHANUMERIC_REGEX);
        boolean roomPriceIsValid = Utils.containsOnlyNumbers(fieldRoomPrice.getText(), Utils.DECIMAL_DOUBLE_REGEX);

        String invalidFields = checkInvalidFields(roomIdIsValid, roomPriceIsValid);
        if (fieldRoomId.getText().isEmpty() || fieldRoomPrice.getText().isEmpty()){
            showMessageDialog(null, "Fields must not be empty");
    }else if(!roomIdIsValid ||!roomPriceIsValid){
    showMessageDialog(null,"Fields are not valid:\n" + invalidFields);
    }else{
        String roomId=fieldRoomId.getText();
        Integer roomPrice=Integer.parseInt(fieldRoomPrice.getText());
        roomController.handleClickButtonUpdateRoom(roomId,roomPrice);
        showMessageDialog(null,"Room updated");

        }
    }
private void addItem(JPanel panel,JComponent component,int x,int y,int align){
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=x;
        gbc.gridy=y;
        gbc.insets=new Insets(5,5,5,5);
        gbc.anchor=align;
        panel.add(component,gbc);
        }
    private static String checkInvalidFields(boolean roomIdIsValid, boolean roomPriceIsNumber){
        String roomIdInvalid = "";
        if(!roomIdIsValid) {
            roomIdInvalid = "- room id is invalid\n";
        }
        String roomPriceInvalid = "";
        if(!roomPriceIsNumber) {
            roomPriceInvalid = "- room price is invalid\n";
        }
        String invalidFields = roomIdInvalid + roomPriceInvalid;
        return  invalidFields;
    }

public static void main(String[]args){
        new GridBagLayoutRoomUpdate();
        }
}
