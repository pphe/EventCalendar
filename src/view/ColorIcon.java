package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * The color icon for the bar chart.
 * 
 * @author Travis Arriola
 *
 */
public class ColorIcon implements Icon {
    
    /** Size of the icon. */
    private static final int SIZE = 15;
    
    /** Color of the icon. */
    private Color myColor;
    
    /**
     * Constructor initializes field.
     * 
     * @param theColor
     */    
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Paints the icon.
     * 
     * @param Component theC
     * @param Graphics theGraphics
     * @param int the X
     * @param int the Y     
     */
    @Override
    public void paintIcon(final Component theC, final Graphics theGraphics,
                                           final int theX, final int theY) {        
        theGraphics.setColor(myColor);               
        theGraphics.fillRect(theX, theY, getIconWidth(), getIconWidth());
        
    }
    
    /**
     * Returns the width.
     */
    @Override
    public int getIconWidth() {        
        return SIZE;
    }

    /**
     * Returns the height.
     */
    @Override
    public int getIconHeight() {        
        return SIZE;
    }
    
    /**
     * Sets the current color.
     * 
     * @param theColor is a color.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;        
    }

}

