package GameObject;

import gameEngine.gameObject.Sprite;

import java.awt.*;

public class MapGenerator
{
    int width;
    int height;
    public MapGenerator(int width, int height)
    {
        this.height = height;
        this.width = width;
    }

    public void MakeMap(int block)
    {
        short i = 0;
        int x, y;

//        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
//            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
//
//                g2d.setColor(mazeColor);
//                g2d.setStroke(new BasicStroke(2));
//
//                if ((screenData[i] & 1) != 0) {
//                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
//                }
//
//                if ((screenData[i] & 2) != 0) {
//                    //g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
//                }
//
//                if ((screenData[i] & 4) != 0) {
//                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
//                            y + BLOCK_SIZE - 1);
//                }
//
//                if ((screenData[i] & 8) != 0) {
//                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
//                            y + BLOCK_SIZE - 1);
//                }
//
//                if ((screenData[i] & 16) != 0) {
//                    g2d.setColor(dotColor);
//                    g2d.fillRect(x + 11, y + 11, 2, 2);
//                }
//
//                i++;
//            }
//        }
    }

}
