package au.edu.swin.sdmd.shortcell;

import android.graphics.Bitmap;
import android.graphics.Color;


/**
 * This object draws an elementary cellular automata:
 * see https://en.wikipedia.org/wiki/Elementary_cellular_automaton for an overview.
 */
public class ElemCA {
    int code = 0;
    int rules[] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    Bitmap ca;
    int width;
    int height;

    /**
     * Initialise the bitmap and set the width and height.
     * @param width
     * @param height
     */
    public ElemCA(int width, int height) {
        ca = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        this.width = width;
        this.height = height;
    }


    /**
     * Given a rule number, break it down into the rules.
     * @param code
     */
    public void setNumber(int code) {
        this.code = code;
        String binary = Integer.toBinaryString(code);
        int offset = rules.length - binary.length();
        rules = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = binary.length()-1; i >= 0; i--) {
            if(binary.charAt(i) == '1') {
                rules[binary.length() - i - 1] = 1;
            } else {
                rules[binary.length() - i - 1] = 0;
            }
        }

    }

    /**
     * This method does most of the work. The first row in the bitmap is coloured white with
     * one black cell in the centre. For the remaining rows, each cell is coloured white or black
     * depending on the cell above it and to the left and right about it. The initial rule number
     * provided to the object determines the pattern of whether a cell is on (black) or off (white).
     */
    public void drawCA() {
        int color2 = 0, color1 = 0, color0 = 0;
        for (int j = 0; j < width; j++) {
            ca.setPixel(j, 0, Color.WHITE);
        }
        ca.setPixel(Math.round(width/2),0, Color.BLACK);
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (j > 0) {
                    color2 = ca.getPixel(j - 1, i - 1);
                } else {
                    color2 = 0;
                }
                color1 = ca.getPixel(j , i-1);
                if (j < width - 1) {
                    color0 = ca.getPixel(j + 1, i - 1);
                } else {
                    color0 = 0;
                }

                int number = 0;
                if (color2 == Color.BLACK) number += 4;
                if (color1 == Color.BLACK) number += 2;
                if (color0 == Color.BLACK) number += 1;

                if (rules[number] == 1) {
                    ca.setPixel(j, i, Color.BLACK);
                } else {
                    ca.setPixel(j, i, Color.WHITE);
                }
            }
        }
    }

    /**
     * @return the generated bitmap
     */
    public Bitmap getCA() {
        return ca;
    }
}
