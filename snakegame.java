import javax.swing.*;

public class snakegame extends JFrame {

    snakegame(){
        super("divyansh");

        add(new board());
        pack();

       // setSize(300,300);
        setLocationRelativeTo(null);
        setResizable(false);


    }

    public static void main(String[]args){
    new snakegame().setVisible(true);
}
}

