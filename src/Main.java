public class Main {
    public static void setLogs(String string) {
        guiLayout.setTextArea(string);
    }

    static GUILayout guiLayout;
    public static void main(String[] args) {
        StartingWindow startingWindow = new StartingWindow();
        if(startingWindow.isReadingFile()){
            World world = new World(1,1);
            world.readFromFile();
            guiLayout = new GUILayout(world.getRows(), world.getColumns(), world);
            guiLayout.setTextArea(world.getTextArea());
            guiLayout.setCoolDownText(world.getCoolDownText());
            guiLayout.drawWorld();
            guiLayout.waitForButtonPress();
            world.WholeGame(guiLayout);

        } else{
            int rows = startingWindow.getRows(), columns = startingWindow.getColumns();
            World world = new World(rows, columns);
            guiLayout = new GUILayout(rows, columns, world);
            world.FillBoardWithOrganisms();
            world.WholeGame(guiLayout);
        }
        System.exit(0);
    }
}