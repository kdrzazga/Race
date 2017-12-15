package libs;

public class BooleanArray {

    public Boolean[] values;

    public BooleanArray(int size) {
        this.values = new Boolean[size];
    }

    public void setAllItems(boolean value) {
        for (int i = 0; i < values.length; i++) {
            values[i] = value;
        }
    }

    public boolean allAreTrue() {
        for (Boolean value : values) {
            if (!value) {
                return false;
            }
        }
        return true;
     }
    
    public boolean allAreFalse() {
        for (Boolean value : values) {
            if (value) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.values.length);

        for (Boolean bool : values) {
            if (bool) {
                result.append('1');
            } else {
                result.append('0');
            }
        }
        return result.toString();
    }
}
