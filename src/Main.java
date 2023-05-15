public class Main {
    public static void main(String[] args) {
        StartingWindow startingWindow = new StartingWindow();
        if(startingWindow.isReadingFile()){
            World world = new World(1,1);
            world.readFromFile();
            GUILayout guiLayout = new GUILayout(world.getRows(), world.getColumns(), world);
            guiLayout.drawWorld();
            guiLayout.waitForButtonPress();
            world.WholeGame(guiLayout);

        } else{
            int rows = startingWindow.getRows(), columns = startingWindow.getColumns();
            World world = new World(rows, columns);
            GUILayout guiLayout = new GUILayout(rows, columns, world);
            world.FillBoardWithOrganisms();
            world.WholeGame(guiLayout);
        }
        System.exit(0);
    }
}