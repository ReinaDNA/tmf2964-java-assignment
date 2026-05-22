import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class Encoded extends JFrame {
    private JTextField inputField;
    private JButton encodeButton;
    private JTextArea displayArea;
    
    private String inputText;
    private int charCount;
    private String resultText = "";
    private final String GroupID = "G02/CS-G15";

    //Constructor - GUI setup
    //Contributor: Arif Amirul Aiman Bin Marzuki
    public Encoded(){
        // Setup the Swing Window
        setTitle("Group G02/CS-G15 Cipher Encoder");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 2. Create the components
        inputField = new JTextField(20);
        encodeButton = new JButton("Encode");

        displayArea = new JTextArea(8, 30);
        displayArea.setEditable(false); // So the user can't type in the result box
        
        // 3. Add them to the window
        add(new JLabel("Enter text (lowercase & numbers):"));
        add(inputField);
        add(encodeButton);
        add(new JScrollPane(displayArea));

        // 4. What happens when the button is clicked
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Get whatever the user typed
                inputText = inputField.getText();
                
                // Create the Encoded object
                Encoded encoder = new Encoded();
                
                // Use Maliska's method to check if it's valid
                if (encoder.checkStringValidity(inputText) == false) {
                    // Show a simple pop-up error
                    JOptionPane.showMessageDialog(null, "Invalid input! Use lowercase letters, digits, and spaces only.");
                } else {
                    // If it is valid, use Thiveya and Zhi Jie's methods
                    int count = encoder.countCharacters(inputText);
                    int shift = encoder.generateShift();
                    String result = encoder.applyCipher(inputText, shift);
                    
                    // Put the final text into the display area
                    displayArea.setText(
                        "Non-Space Characters: " + count + "\n" +
                        "Final Shift: " + shift + "\n" +
                        "Result: " + result
                    );
                }
            }
        });
    }

    public Encoded(String inputText){
        this.inputText = inputText;
    }

     public int countCharacters(String inputText) {
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) != ' ') {
                charCount++;
            }
        }
        return charCount;
    }

    public boolean checkStringValidity(String inputText){
        boolean onlyValidAlphanumeric = inputText.matches("[a-z0-9\s]+"); // See if it matches any of the a to z, 0 to 9 and whitespace
        // If the text field is not an empty field
        if (!inputText.isEmpty()) {
            if (onlyValidAlphanumeric) {
                return true;
            } 
        // If the text field is empty or null
        } else if (inputText.isEmpty() || (inputText == null)) {
           return false;
        } 
        return false;
    }

    public int generateShift(){
        int groupShift = Math.abs((this.GroupID.hashCode() % 10)) + 1;
        int finalShift = groupShift + charCount;
        return finalShift;
    }
   
    public String applyCipher(String inputText, int shift){

        for(int i=0; i<inputText.length(); i++){
            
            char c = inputText.charAt(i);
            
            if(Character.isLowerCase(c)){
                char base = 'a';
                int shiftedPosition = (c - base + shift) % 26 + base;
                resultText += (char)(shiftedPosition);
            }else if(Character.isDigit(c)){
                char base = '0';
                int shiftedPosition = (c - base + shift) % 10 + base;
                resultText += (char)(shiftedPosition);
            }else{
                // The char is a white space, no encryption needed
                resultText += c;
                continue;
            }
        }
        
        return resultText;
    }

    public static void main(String[] args) {
        Encoded myWindow = new Encoded();
        myWindow.setVisible(true);
    }
}
