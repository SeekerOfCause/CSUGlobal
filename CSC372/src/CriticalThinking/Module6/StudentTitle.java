package CriticalThinking.Module6;

class StudentTitle extends Student {
    private String numTitle;
    private String nameTitle;
    private String ageTitle;
    private String addressTitle;

    public StudentTitle(String numTitle, String nameTitle, String ageTitle, String addressTitle) {
        super(0, "", 0, "");  // Call the super constructor with placeholder values
        this.numTitle = numTitle;
        this.nameTitle = nameTitle;
        this.ageTitle = ageTitle;
        this.addressTitle = addressTitle;
    }

    @Override
    public String toString() {
        return String.format("\n%-5s\t%-15s\t%-5s\t%-20s", numTitle, nameTitle, ageTitle, addressTitle);
    }
}
