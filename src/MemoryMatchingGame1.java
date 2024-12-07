import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList; 
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer; 

public class MemoryMatchingGame1 extends JFrame{ 
    int values[]=new int[16];
    JPanel panel=new JPanel();
    JTextField scorefield=new JTextField("YOUR SCORE:");
    JButton[] buttons=new JButton[16];
	boolean[] revealed=new boolean[16];
    int num1,num1index;
    int score=0;
    JFrame f=new JFrame();
    Timer countdown;
    JLabel timeLabel = new JLabel("Time Left: 30");
    int counttime = 30;
	public MemoryMatchingGame1(){
        ArrayList<Integer> nums= new ArrayList<Integer>();
        nums.add(1); nums.add(1);
	    nums.add(2); nums.add(2);
	    nums.add(3); nums.add(3);
	    nums.add(4); nums.add(4);
	    nums.add(5); nums.add(5);
	    nums.add(6); nums.add(6);
	    nums.add(7); nums.add(7);
	    nums.add(8); nums.add(8);
        Collections.shuffle(nums);

        for(int k=0;k<16;k++){
            revealed[k]=false;
            values[k]=nums.get(k);
        }

		
		f.setSize(500,500);
		f.setTitle("Memory Matching Game");
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        
		panel.setLayout(new GridLayout(4,4, 10,10));
		panel.setBackground(Color.pink);
		
		scorefield.setFont(new Font("Arial",Font.BOLD,30));
		scorefield.setEditable(false);
		scorefield.setSize(120,30);
        
		for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setSize(10, 10);
            buttons[i].setBackground(Color.ORANGE);
			buttons[i].setFont(new Font("Arial",Font.BOLD,20));
            final int index = i;
            buttons[i].addActionListener(new Buttonclick(index));
            panel.add(buttons[i]);
        }
		f.add(scorefield,BorderLayout.NORTH);
		f.add(panel,BorderLayout.CENTER);
        f.add(timeLabel, BorderLayout.SOUTH);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
		f.setVisible(true);
        JOptionPane.showMessageDialog(f, "Match all Pairs before time runs out");
        countdown=new Timer(1000,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counttime--;
                timeLabel.setText("Time Left: " + counttime);
                if (counttime == 0) {
                    countdown.stop();
                    JOptionPane.showMessageDialog(f, "Sorry Time is up. You Lost.");
                    System.exit(0);
                }
            }
        });
        countdown.start();
	}

    int i=0;

    public class Buttonclick implements ActionListener {
        int index;
        public Buttonclick(int index){
            this.index=index;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if(revealed[index])return;

            buttons[index].setText(String.valueOf(values[index]));
            revealed[index]=true;
            i++;
            if(i==1){
                num1index=index;
                num1=values[index];
            }
            if(i==2){
                if(num1==values[index]){
                    score++;
                    scorefield.setText("YOUR SCORE:"+String.valueOf(score));
                    num1=0;
                    i=0;
                    buttons[num1index].setEnabled(false);
                    buttons[index].setEnabled(false);
                }
                else{
                    i=0;         
                    Timer timer = new Timer(500, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            buttons[num1index].setText("");
                            buttons[index].setText("");
                            revealed[num1index]=false;
                            revealed[index]=false;
                            num1=0;
                            num1index=-1;
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    
                }
            }
            if(score==8){
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        countdown.stop();
                        JOptionPane.showMessageDialog(f, "Congratulations! You've matched all pairs!");
                        System.exit(0);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                
            }
        }        
    }
	public static void main(String[] args) 
	{ 
        MemoryMatchingGame1 game=new MemoryMatchingGame1();
    }
    
}
