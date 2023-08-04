package util.DatePicker;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.*;

public class JDatePicker extends JFrame implements ActionListener {

    private JPanel dp = new JPanel();
    private JDatePickerImpl datePicker;
    private Date selectedDate;

    public JDatePicker() {
        super("Calendar");
        setLayout(new FlowLayout());

		SqlDateModel model = new SqlDateModel();
        model.setSelected(true);

        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);

        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.addActionListener(this);
        datePicker.setPreferredSize(new Dimension(250, 30));

        add(datePicker);

        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(this);

        add(buttonOK);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        dp.add(datePicker);
    }

    public JPanel getJDatePicker(){
        return dp;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        setSelectedDate((java.sql.Date) datePicker.getModel().getValue());
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
