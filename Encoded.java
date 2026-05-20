import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Encoded extends JFrame {
    private JTextField inputField;
    private JButton encodeButton;
    private JTextArea displayArea;
    
    private String inputText;
    private int charCount;
    private String resultText;
    private final String GroupID = "G02/CS-G15";

    //Constructor
    public Encoded(){
        // Setup the Swing Window
        setTitle("Group G02/CS-G15 Cipher Encoder");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Initialize UI Components
        inputField = new JTextField(25);
        encodeButton = new JButton("Encode String");
        displayArea = new JTextArea(8, 35);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        // Add components to the frame
        add(new JLabel("Enter text (lowercase letters, numbers, spaces):"));
        add(inputField);
        add(encodeButton);
        add(new JScrollPane(displayArea));
    }

    public Encoded(String inputText){
        this.inputText = inputText;
    }

    public int countCharacters(String inputText){
        return 0;
    }

    public boolean checkStringValidity(String inputText){
        return false;
    }

    public int generateShift(){
        this.countCharacters(inputText);
        int groupShift = Math.abs((this.GroupID.hashCode() % 10)) + 1;
        int finalShift = groupShift + this.charCount;
        return finalShift;
    }

    public String applyCipher(String inputText, int shift){
        String encryptedText = "";

        for(int i=0; i<inputText.length(); i++){
            
            char c = inputText.charAt(i);
            
            if(Character.isLowerCase(c)){
                char base = 'a';
                int shiftedPosition = (c - base + shift) % 26 + base;
                encryptedText += (char)(shiftedPosition);
            }else if(Character.isDigit(c)){
                char base = '0';
                int shiftedPosition = (c - base + shift) % 10 + base;
                encryptedText += (char)(shiftedPosition);
            }else{
                // The char is a white space, no encryption needed
                encryptedText += c;
                continue;
            }
        }
        
        this.resultText = encryptedText;
        return encryptedText;
    }

    public static void main(String[] args) {
        
    }
}
