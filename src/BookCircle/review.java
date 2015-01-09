package BookCircle;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class review implements ActionListener{

	static JButton submit2;
	static JTextField bName;
	static JComboBox ratings;
	static JTextArea rev;
	
public JPanel getReview()
{
	JPanel pb=new JPanel();
	pb.setLayout(new BorderLayout());
	
    JPanel pb1 = new JPanel();
     pb1.setLayout(new GridBagLayout());
     GridBagConstraints cs = new GridBagConstraints();
     cs.fill = GridBagConstraints.VERTICAL;
     JLabel ln1 = new JLabel("Write A Review");
     
    cs.gridx = 0;
     cs.gridy =0;
     cs.gridwidth =2;
      
    pb1.add(ln1, cs);
    JLabel ln2 = new JLabel("Book Name :");
     
    cs.gridx = 0;
     cs.gridy =2;
     cs.gridwidth =1;
     cs.insets = new Insets(10,10,10,10);    
    pb1.add(ln2, cs);
    bName=new JTextField(30);
    cs.gridx = 1;
     cs.gridy = 2;
     cs.gridwidth =1;
     
     cs.insets = new Insets(10,10,10,10);  
     
   pb1.add(bName, cs);
    pb.add(pb1,BorderLayout.PAGE_START);
    JLabel ln3 = new JLabel("Rating :");
     
    cs.gridx = 0;
     cs.gridy =3;
     cs.gridwidth =1;
     cs.insets = new Insets(0,0,5,5);  
    pb1.add(ln3, cs);
    String[] rate = { "1", "2", "3","4", "5" };

  //Create the combo box, select item at index 4.
  //Indices start at 0, so 4 specifies the pig.
  ratings = new JComboBox(rate);
  ratings.setSelectedIndex(0);
    cs.gridx = 1;
     cs.gridy = 3;
     cs.gridwidth =1;
     
     cs.insets = new Insets(10,10,10,10);    
     
   pb1.add(ratings, cs);
   
   JLabel ln4 = new JLabel("Review :");
     
    cs.gridx = 0;
     cs.gridy =4;
     cs.gridwidth =1;
     
    pb1.add(ln4, cs);
     rev = new JTextArea();
    rev.setColumns(32);
    rev.setRows(5);
     
    JScrollPane areaScrollPane = new JScrollPane(rev);
    areaScrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    areaScrollPane.setPreferredSize(new Dimension(750, 250));
    rev.setEditable(true);
    cs.gridx = -1;
     cs.gridy = 4;
     cs.gridwidth =1;
     cs.insets = new Insets(10,10,10,10);  
    pb1.add(rev, cs);
    submit2 =new JButton("Submit");
   
     
     cs.gridx = 1;
     cs.gridy = 5;
     cs.gridwidth =1;
     cs.insets = new Insets(10,10,10,10);
     pb1.add(submit2,cs);
     submit2.addActionListener(this);
	 submit2.setActionCommand("Submit");
     
     
    pb.add(pb1,BorderLayout.NORTH);
    return pb;
}


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==submit2)
	{
		book newBook = new book();
		newBook.setName(bName.getText());
		newBook.setRating(ratings.getSelectedItem().toString());
		newBook.setReview(rev.getText());
		dbInit initDb = new dbInit();
		initDb.addReview(newBook);
		bName.setText("");
		ratings.setSelectedIndex(0);
		rev.setText("");
	}
}
}
