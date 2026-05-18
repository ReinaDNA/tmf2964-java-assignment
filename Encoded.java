import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String GroupID = "G02/CS-G15"; // Test case string
    
    //Constructor
    public Encoded(){
        // where the GUI will go
        JFrame frame = new JFrame("Encoding GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(400,300);

        JTextField inputField = new JTextField("Please insert string to be encoded: ",20);
        JButton encodeButton = new JButton("Encode Now!");
        
        frame.setLayout(new BorderLayout());

        frame.add(inputField, BorderLayout.CENTER);
        frame.add(encodeButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public Encoded(String inputText){
        this.inputText = inputText;
    }

    public void countCharacters(String inputText){
        int count = 0;
        for(int i=0; i<inputText.length(); i++){
            char ch = inputText.charAt(i);
            if(Character.isWhitespace(ch)){
                continue;
            }else{
                count++;
            }
        }
        this.charCount = count;
    }

    public boolean checkStringValidity(String inputText){
        for(char c:inputText.toCharArray()){
            if(!Character.isWhitespace(c) && !Character.isLowerCase(c) && !Character.isDigit(c)){
                System.out.println("Invalid string, please try again.");
                return false;
            }
        }
        return true;
    }

    public int generateShift(){
        this.countCharacters(inputText);
        int groupShift = Math.abs((this.GroupID.hashCode() % 10)) + 1;
        int finalShift = groupShift + this.charCount;
        return finalShift;
    }

    public String applyCipher(String inputText, int shift){
        String encryptedText = "";
        StringBuilder result = new StringBuilder();

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

    public int getCharCount(){
        return this.charCount;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Encoded());
    }
}
