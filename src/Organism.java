public abstract class Organism {
    private int strength = 0, initiative = 0, age = 0, coolDown = 0;
    private boolean roundDone = false;
    private char symbol = '*';
    private String animalName;

    public int getStrength() {
        return strength;
    }
    protected  abstract void Action();
    protected abstract int Collision(Point point);
    protected boolean SpecialAttack(Organism organism){
        return false;

    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean isRoundDone() {
        return roundDone;
    }

    public void setRoundDone(boolean roundDone) {
        this.roundDone = roundDone;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(int x, int y) {
        this.point.setX(x);
        this.point.setY(y);
    }

    protected World world;
    Point point  = new Point();


}
