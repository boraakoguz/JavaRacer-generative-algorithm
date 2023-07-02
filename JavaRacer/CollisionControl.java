package JavaRacer;

public class CollisionControl {
    GameWindow gameWindow;

    public CollisionControl(GameWindow gw){
        this.gameWindow = gw;
    }
    public double checkCollision(Agent agent){
        int leftX = agent.agentX + agent.solidArea.x;
        int rightX = leftX + agent.solidArea.width;
        int upperY = agent.agentY + agent.solidArea.y;
        int lowerY = upperY + agent.solidArea.height;

        int leftColNum = leftX / gameWindow.tileSize;
        int rightColNum = rightX / gameWindow.tileSize;
        int upperRowNum = upperY / gameWindow.tileSize;
        int lowerRowNum = lowerY / gameWindow.tileSize;

        if(leftColNum>=0&&rightColNum<=gameWindow.collumn-1&&upperRowNum>=0&&lowerRowNum<=gameWindow.row-1){
            agent.onFinishLine = false;
            if(gameWindow.tileManager.tiles[gameWindow.tileManager.map[upperRowNum][leftColNum]].isFinishLine||gameWindow.tileManager.tiles[gameWindow.tileManager.map[lowerRowNum][rightColNum]].isFinishLine){
                agent.onFinishLine = true;
            }
            double friction = Math.min(gameWindow.tileManager.tiles[gameWindow.tileManager.map[upperRowNum][leftColNum]].friction, gameWindow.tileManager.tiles[gameWindow.tileManager.map[lowerRowNum][rightColNum]].friction);
            return friction;
        }
        return 0.1;
    }
}
