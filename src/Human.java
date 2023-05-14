import javax.sound.midi.SysexMessage;
import java.awt.event.KeyEvent;

public class Human extends Animal{
    private static final char humanSymbol = '@';
    private KeyEvent event;
    public Human(){
        this.setStrength(5);
        this.setInitiative(4);
        this.setSymbol(humanSymbol);
        this.setAnimalName("Human");
    }

    @Override
    protected void Action() {
        boolean[] isNotFilled = {false, false, false, false};
        if(super.CheckForFilling(isNotFilled, 1, '"', point) != 0)
            return;
        int positionReturnCode;
        while(true){
            int c = event.getKeyCode();
            switch(c){
                case KeyEvent.VK_UP -> {
                    if(isNotFilled[0]){
                        positionReturnCode = PositionAndCollision(  -1, 0);
                        if(positionReturnCode == 0 ) return;
                        world.AddOrganism(this, point.getX(),point.getY());
                        return;
                    }
                    break;
                }
                case KeyEvent.VK_DOWN -> {
                    if(isNotFilled[1]){
                        positionReturnCode = PositionAndCollision(  1, 0);
                        if(positionReturnCode == 0 ) return;
                        world.AddOrganism(this, point.getX(),point.getY());
                        return;
                    }
                    break;
                }
                case KeyEvent.VK_LEFT -> {
                    if(isNotFilled[2]){
                        positionReturnCode = PositionAndCollision(  0, -1);
                        if(positionReturnCode == 0 ) return;
                        world.AddOrganism(this, point.getX(),point.getY());
                        return;
                    }
                    break;
                }
                case KeyEvent.VK_RIGHT -> {
                    if(isNotFilled[3]){
                        positionReturnCode = PositionAndCollision(  0, 1);
                        if(positionReturnCode == 0 ) return;
                        world.AddOrganism(this, point.getX(),point.getY());
                        return;
                    }
                    break;
                }
                case KeyEvent.VK_P ->{
                    if(this.getCoolDown() == 0 ){
                        this.setStrength(10);
                    }
                    break;
                }
                case KeyEvent.VK_ESCAPE -> {
                    System.exit(0);
                }
                default -> {}
            }
        }

    }
}
