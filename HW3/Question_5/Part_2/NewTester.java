import java.awt.*;
import javax.swing.*;

public class NewTester{
	
   public static void main(String[] args){
      final Invoice invoice = new Invoice();
      final InvoiceFormatter formatter = new HTMLPage();
      JTextPane textArea = new JTextPane();
      textArea.setContentType("text/html");

      invoice.addChangeListener(event -> textArea.setText(invoice.format(formatter)));

      final JComboBox combo = new JComboBox();
      Product hammer = new Product("Hammer", 19.95, 0);
      Product nails = new Product("Assorted nails", 9.95, 0);
      combo.addItem(hammer);
      Bundle bundle = new Bundle();
      bundle.add(hammer);
      bundle.add(nails);
      combo.addItem(new DiscountedItem(bundle, 10));

      JButton addButton = new JButton("Add");
      addButton.addActionListener(event ->
         {
            LineItem item = (LineItem) combo.getSelectedItem();
            invoice.addItem(item);
         });

      JPanel panel = new JPanel();
      panel.add(combo);
      panel.add(addButton);

      JFrame frame = new JFrame();
      frame.add(new JScrollPane(textArea),
         BorderLayout.CENTER);
      frame.add(panel, BorderLayout.SOUTH);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
