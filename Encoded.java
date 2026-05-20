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
                String userInput = inputField.getText();
                
                // Create the Encoded object
                Encoded encoder = new Encoded();
                
                // Use Maliska's method to check if it's valid
                if (encoder.checkStringValidity(userInput) == false) {
                    // Show a simple pop-up error
                    JOptionPane.showMessageDialog(null, "Invalid input! Use lowercase letters, digits, and spaces only.");
                } else {
                    // If it is valid, use Thiveya and Zhi Jie's methods
                    int count = encoder.countCharacters(userInput);
                    int shift = encoder.generateShift();
                    String result = encoder.applyCipher(userInput, shift);
                    
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
        Encoded myWindow = new Encoded();
        myWindow.setVisible(true);
    }
}
