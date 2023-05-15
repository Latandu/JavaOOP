import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Human extends Animal {
    private static final char humanSymbol = '@';
    public Human(){
        this.setStrength(5);
        this.setInitiative(4);
        this.setSymbol(humanSymbol);
        this.setAnimalName("Human");
    }

    @Override
    protected void Action() {
        boolean[] isNotFilled = {false, false, false, false};
        if(super.organismHandler.CheckForFilling(isNotFilled, 1, '"', point, world) != 0)
            return;
        int positionReturnCode;
        int direction = world.getSetDirection();
        if(world.isPowerUP()){
            if(getCoolDown() == 0){
                this.setStrength(10);
                world.guiLayout.setTextArea("Activated SuperPower. Added 10 strength \n");
                world.setPowerUP(false);
            }
        }
        switch(direction){
            case 1 -> {
                if(isNotFilled[0]){
                    positionReturnCode = PositionAndCollision(  -1, 0);
                    if(positionReturnCode == 0 ) return;
                    world.AddOrganism(this, point.getX(),point.getY());
                    world.setSetDirection(0);
                    return;
                }
                break;
            }
            case 2 -> {
                if(isNotFilled[1]){
                    positionReturnCode = PositionAndCollision(  1, 0);
                    if(positionReturnCode == 0 ) return;
                    world.AddOrganism(this, point.getX(),point.getY());
                    world.setSetDirection(0);
                    return;
                }
                break;
            }
            case 3 -> {
                if(isNotFilled[2]){
                    positionReturnCode = PositionAndCollision(  0, -1);
                    if(positionReturnCode == 0 ) return;
                    world.AddOrganism(this, point.getX(),point.getY());
                    world.setSetDirection(0);
                    return;
                }
                break;
            }
            case 4 -> {
                if(isNotFilled[3]){
                    positionReturnCode = PositionAndCollision(  0, 1);
                    if(positionReturnCode == 0 ) return;
                    world.AddOrganism(this, point.getX(),point.getY());
                    world.setSetDirection(0);
                    return;
                }
                break;
            }
            default -> {
            }
        }

    }
}
