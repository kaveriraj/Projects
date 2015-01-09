package BookCircle;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class bookPoll {
	static JButton submit = new JButton("Vote");
	public static JPanel getPoll()
	{
	
		JPanel pb=new JPanel();
		pb.setLayout(new BorderLayout());
    JPanel pb1 = new JPanel();
     pb1.setLayout(new GridBagLayout());
     GridBagConstraints cs = new GridBagConstraints();
     cs.fill = GridBagConstraints.VERTICAL;
    
    JLabel ln2 = new JLabel("Book Poll");
     
    cs.gridx = 2;
     cs.gridy =0;
     cs.gridwidth =2;
     cs.insets = new Insets(10,10,10,10);    
    pb1.add(ln2, cs);
    JLabel ln3 = new JLabel("Vote for the book to be reviewed this week");
    
    cs.gridx = 2;
     cs.gridy =2;
     cs.gridwidth =1;
     cs.insets = new Insets(10,10,10,10);    
    pb1.add(ln3, cs);
    
   JRadioButton answer1 = new JRadioButton("The Hobbit");
    JRadioButton answer2 = new JRadioButton("The Avenge");
    JRadioButton answer3 = new JRadioButton("Grey Manors");
    JRadioButton answer4 = new JRadioButton("Abandoned");
   ButtonGroup group = new ButtonGroup();
   group.add(answer1);
  cs.gridx = 0;
  cs.gridy = 4;
  cs.gridwidth = 1;
  pb1.add(answer1, cs);
  group.add(answer2);
  cs.gridx = 0;
  cs.gridy = 5;
  cs.gridwidth = 1;
  pb1.add(answer2, cs);
  group.add(answer3);
  cs.gridx = 0;
  cs.gridy = 6;
  cs.gridwidth = 1;
  pb1.add(answer3, cs);
  group.add(answer4);
  cs.gridx = 0;
  cs.gridy = 7;
  cs.gridwidth = 1;
  pb1.add(answer4, cs);
  cs.gridx = 2;
  cs.gridy = 8;
  cs.gridwidth = 1;
  pb1.add(submit,cs);
 /* submit.addActionListener(this);
	 submit.setActionCommand("Submit");*/
  pb.add(pb1,BorderLayout.NORTH);
  return pb;
	}

}
