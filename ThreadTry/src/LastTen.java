public class LastTen {
    public int lastTen[] = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int index = 0;

    public void add(int newValue) {
        this.lastTen[index] = newValue;
        this.index += 1;
    }

    public int[] getLastTen() {
        return this.lastTen;
    }
}
