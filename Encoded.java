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
    private String resultText = "";
    private final String GroupID = "G02/CS-G15";

    //Constructor - GUI setup
    //Contributor: Arif Amirul Aiman Bin Marzuki (83282)
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
        add(new JLabel("Enter text (lowercase, numbers & spaces only):"));
        add(inputField);
        add(encodeButton);
        add(new JScrollPane(displayArea));

        // 4. What happens when the button is clicked
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Get whatever the user typed
                inputText = inputField.getText();
                
                // Use Maliska's method to check if it's valid
                if (!checkStringValidity(inputText)) {
                    // Show a simple pop-up error
                    JOptionPane.showMessageDialog(null, "Invalid input! Use lowercase letters, digits, and spaces only.");
                    
                } else {
                    // If it is valid, use Thiveya and Zhi Jie's methods
                    int count = countCharacters(inputText);
                    int shift = generateShift();
                    String result = applyCipher(inputText, shift);
                    
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

    // Constructor with predefined input string
    //Contributor: Arif Amirul Aiman Bin Marzuki (83282)
    public Encoded(String inputText){
        this.inputText = inputText;
    }

    // Method for counting non-space characters
    // Contributor: Thiveya Shree a/p Baskaran (106564)
     public int countCharacters(String inputText) {
        // Resets the variable to original state
        charCount = 0;
        for (int i = 0; i < inputText.length(); i++) {
            if (inputText.charAt(i) != ' ') {
                charCount++;
            }
        }
        return charCount;
    }

    // Method for input validation
    // Contributor: Mohamad Ikhmal Iskandar bin Mohd Ibrahim  (105016)
    public boolean checkStringValidity(String inputText){
        boolean onlyValidAlphanumeric = inputText.matches("[a-z0-9\s]+"); // See if it matches any of the a to z, 0 to 9 and whitespace
        // If the text field is not an empty field
        if (!inputText.isEmpty()) {
            if (onlyValidAlphanumeric) {
                return true;
            } 
        // If the text field is empty or null
        } else if ((inputText == null) || inputText.isEmpty()) {
           return false;
        } 
        return false;
    }

    // Method for generating shift for encoding
    // Contributor: Seng Zhi Jie (106256)
    public int generateShift(){
        int groupShift = Math.abs((this.GroupID.hashCode() % 10)) + 1; // Generate hash between 1 to 10
        int finalShift = groupShift + charCount; // Calculate final shift
        return finalShift;
    }
   
    // Method for encoding input text
    // Contributor: Seng Zhi Jie (106256)
    public String applyCipher(String inputText, int shift){
        // Reset the result text
        resultText = "";
        for(int i=0; i<inputText.length(); i++){
            // Iterating through the string to apply encryption
            char c = inputText.charAt(i);
            
            // Letters encryption
            if(Character.isLowerCase(c)){
                char base = 'a';
                int shiftedPosition = (c - base + shift) % 26 + base; // Encryption formula for letters
                resultText += (char)(shiftedPosition); // Revert back to chart and add it into result
            }else if(Character.isDigit(c)){
                // Numbers encryption
                char base = '0';
                int shiftedPosition = (c - base + shift) % 10 + base; // Encryption formula for numbers
                resultText += (char)(shiftedPosition); // Revert back to char and add it into result 
            }else{
                // The char is a white space, no encryption needed
                resultText += c;
                continue;
            }
        }
        
        return resultText;
    }

    
}
