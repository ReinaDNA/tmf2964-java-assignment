public class Encoded {
    private String inputText;
    private int charCount;
    private String resultText;
    private final String GroupID;

    //Constructor
    public Encoded(){

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
